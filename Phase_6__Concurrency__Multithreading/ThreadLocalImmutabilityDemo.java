package Phase_6__Concurrency__Multithreading;

 // ─────────────────────────────────────────────────────────────
// PHASE 6 — L8: ThreadLocal & Immutability
//
// TWO elegant ways to avoid synchronization entirely:
//
// 1. IMMUTABILITY — objects that CANNOT be modified after creation
//    No shared mutable state = no race conditions = no synchronization needed
//    The best concurrency strategy: avoid sharing mutable state at all!
//    String, Integer, all primitive wrappers are immutable in Java.
//
// 2. THREADLOCAL — each thread has its OWN copy of a variable
//    No sharing between threads = no race condition = no synchronization
//    Used for: per-thread database connections, user sessions,
//              transaction contexts, Spring Security's SecurityContextHolder
// ─────────────────────────────────────────────────────────────

import java.util.concurrent.*;
// import java.util.*;

public class ThreadLocalImmutabilityDemo {

    // ── IMMUTABLE CLASS ───────────────────────────────────────
    // Rules for immutability:
    //   1. Class declared 'final' — cannot be subclassed
    //   2. All fields 'private final' — no reassignment
    //   3. No setters — no way to change fields after construction
    //   4. Deep copy mutable objects in constructor (defensive copy)
    //   5. Return copies of mutable fields in getters

    public static final class Money {  // final class

        private final double  amount;   // private + final
        private final String  currency;

        // Constructor validates and stores — no way to change after
        public Money(double amount, String currency) {
            if (amount < 0) throw new IllegalArgumentException("Amount cannot be negative");
            if (currency == null || currency.isBlank()) throw new IllegalArgumentException("Currency required");
            this.amount   = amount;
            this.currency = currency.toUpperCase();
        }

        // Getters only — no setters
        public double getAmount()   { return amount; }
        public String getCurrency() { return currency; }

        // Operations return NEW Money objects — don't modify this
        public Money add(Money other) {
            if (!this.currency.equals(other.currency))
                throw new IllegalArgumentException("Currency mismatch");
            return new Money(this.amount + other.amount, this.currency);  // new object!
        }

        public Money multiply(double factor) {
            return new Money(this.amount * factor, this.currency);  // new object!
        }

        @Override
        public String toString() {
            return String.format("%s %.2f", currency, amount);
        }
    }

    // ── THREADLOCAL — per-thread variable storage ─────────────
    // Each thread that accesses a ThreadLocal has its OWN INDEPENDENT copy.
    // Changes in Thread A do NOT affect Thread B's value.
    // Spring Boot uses this for:
    //   SecurityContextHolder   → current user per request thread
    //   TransactionSynchronizationManager → DB transaction per thread
    //   RequestContextHolder    → HTTP request per thread

    // Shared ThreadLocal — but each thread has its OWN value
    private static ThreadLocal<String> currentUser = new ThreadLocal<>();

    // ThreadLocal with initial value — use withInitial() (Java 8+)
    private static ThreadLocal<Integer> requestId = ThreadLocal.withInitial(
            () -> (int)(Math.random() * 1000)
    );

    // ── SIMULATE REQUEST HANDLING ─────────────────────────────
    // Each HTTP request = a separate thread in Spring Boot
    // Each thread sets its own "current user" via ThreadLocal
    static void handleRequest(String username, String operation) {
        try {
            currentUser.set(username);   // set for THIS thread only
            System.out.printf("[Thread: %-15s | User: %-10s | ReqID: %d]%n",
                    Thread.currentThread().getName(),
                    currentUser.get(),   // gets THIS thread's value
                    requestId.get());

            // Simulate processing
            Thread.sleep(100);

            // Other methods in same thread can access currentUser
            performOperation(operation);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            // ⚠️ CRITICAL: Always remove ThreadLocal when done!
            // In thread pools, threads are REUSED — old data leaks to next request!
            currentUser.remove();
        }
    }

    static void performOperation(String op) {
        // Can access ThreadLocal without passing it as parameter
        System.out.printf("[Thread: %-15s] %s performed by: %s%n",
                Thread.currentThread().getName(), op, currentUser.get());
    }

    // ── SIMULATE DATABASE CONNECTION PER THREAD ───────────────
    // Classic use case: one DB connection per request thread
    static ThreadLocal<String> dbConnection = ThreadLocal.withInitial(() -> {
        // Each thread gets its own connection from pool
        String connId = "CONN-" + Thread.currentThread().getId();
        System.out.println("[" + Thread.currentThread().getName()
                           + "] Opening DB connection: " + connId);
        return connId;
    });

    static void queryDatabase(String query) {
        String conn = dbConnection.get();   // get THIS thread's connection
        System.out.printf("[%s | %s] Executing: %s%n",
                Thread.currentThread().getName(), conn, query);
    }

    public static void main(String[] args) throws InterruptedException {

        // ── IMMUTABILITY DEMO ─────────────────────────────────
        System.out.println("=== Immutability Demo ===");
        Money price    = new Money(100.0, "INR");
        Money tax      = new Money(18.0,  "INR");
        Money total    = price.add(tax);          // new object — price unchanged
        Money discounted = total.multiply(0.9);   // another new object

        System.out.println("Price:      " + price);      // INR 100.00
        System.out.println("Tax:        " + tax);        // INR 18.00
        System.out.println("Total:      " + total);      // INR 118.00
        System.out.println("Discounted: " + discounted); // INR 106.20

        // Immutable objects are INHERENTLY thread-safe
        // Multiple threads can use 'price' simultaneously — no sync needed!
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            pool.submit(() -> {
                Money discount = price.multiply(0.95);   // thread-safe — new object each time
                System.out.println("Discounted for thread: " + discount);
            });
        }
        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);

        // ── THREADLOCAL DEMO ───────────────────────────────────
        System.out.println("\n=== ThreadLocal — Per-Thread User Context ===");

        Thread req1 = new Thread(() -> handleRequest("Rashid", "VIEW_DASHBOARD"),  "RequestThread-1");
        Thread req2 = new Thread(() -> handleRequest("Ali",    "PLACE_ORDER"),     "RequestThread-2");
        Thread req3 = new Thread(() -> handleRequest("Sara",   "VIEW_PROFILE"),    "RequestThread-3");

        req1.start(); req2.start(); req3.start();
        req1.join();  req2.join();  req3.join();
        // Each thread sees ONLY its own user — no interference!

        // ── DB CONNECTION PER THREAD ───────────────────────────
        System.out.println("\n=== ThreadLocal DB Connection ===");
        ExecutorService dbPool = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 3; i++) {
            final int reqNum = i;
            dbPool.submit(() -> {
                // First access creates connection for this thread
                queryDatabase("SELECT * FROM orders WHERE req=" + reqNum);
                queryDatabase("SELECT * FROM users WHERE req=" + reqNum);
                dbConnection.remove();  // clean up when done with thread
            });
        }
        dbPool.shutdown();
        dbPool.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("\n=== ThreadLocal Isolation Verified ===");
        System.out.println("Each thread had its own connection and user context.");
    }
    
}
