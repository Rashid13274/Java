package Phase_6__Concurrency__Multithreading;

 // ─────────────────────────────────────────────────────────────
// PHASE 6 — L2: Thread Safety & Synchronization
//
// RACE CONDITION: when multiple threads access shared data
// simultaneously and the result depends on execution order.
//
// EXAMPLE of race condition:
//   counter++ is NOT atomic! It's actually 3 steps:
//     1. READ  counter value from memory
//     2. ADD   1 to the value
//     3. WRITE new value back to memory
//   If two threads do this simultaneously, one update is LOST.
//
// SOLUTIONS:
//   1. synchronized keyword  → only one thread at a time
//   2. volatile keyword      → visibility guarantee, not atomicity
//   3. Atomic classes        → lock-free, hardware-level atomicity
//   4. java.util.concurrent  → higher-level thread-safe classes
//
// Node.js parallel: You don't have this problem in Node.js because
//   it's single-threaded. This is purely a Java/multi-thread concern.
// ─────────────────────────────────────────────────────────────

import java.util.concurrent.atomic.*;

public class ThreadSafetyDemo {

    // ── PROBLEM: RACE CONDITION ───────────────────────────────
    static class UnsafeCounter {
        private int count = 0;   // shared state — NOT thread-safe

        public void increment() {
            count++;   // ❌ NOT atomic — READ + INCREMENT + WRITE
        }

        public int getCount() { return count; }
    }

    // ── SOLUTION 1: synchronized METHOD ──────────────────────
    // 'synchronized' = only ONE thread can execute this method at a time.
    // Other threads BLOCK and wait until lock is released.
    // Each object has an intrinsic lock (monitor) — synchronized uses it.
    static class SynchronizedCounter {
        private int count = 0;

        // 'synchronized' acquires lock on 'this' object
        public synchronized void increment() {
            count++;   // ✅ now atomic — only one thread here at a time
        }

        public synchronized int getCount() {
            return count;   // also synchronized — ensures visibility
        }
    }

    // ── SOLUTION 2: synchronized BLOCK ───────────────────────
    // More fine-grained — only lock the critical section, not entire method.
    // Better performance than synchronizing the whole method.
    static class SynchronizedBlockCounter {
        private int count = 0;
        private final Object lock = new Object();   // dedicated lock object

        public void increment() {
            // Do non-critical work here (no lock needed)
            System.out.println("Thread entering..."); // parallel is fine

            synchronized (lock) {
                // CRITICAL SECTION — only one thread here at a time
                count++;
            }

            // More non-critical work here
        }

        public int getCount() {
            synchronized (lock) {
                return count;
            }
        }
    }

    // ── SOLUTION 3: AtomicInteger (BEST for simple counters) ──
    // Lock-free, uses hardware CAS (Compare-And-Swap) instruction.
    // Much faster than synchronized for simple increment/decrement.
    static class AtomicCounter {
        // AtomicInteger, AtomicLong, AtomicBoolean, AtomicReference
        private AtomicInteger count = new AtomicInteger(0);

        public void increment() {
            count.incrementAndGet();  // ✅ atomic — single hardware instruction
        }

        public int getCount() {
            return count.get();
        }
    }

    // ── DEMO: run 1000 threads incrementing a counter ─────────
    static int runWithCounter(Runnable incrementAction, Runnable getCount)
            throws InterruptedException {
        Thread[] threads = new Thread[1000];
        for (int i = 0; i < 1000; i++) {
            threads[i] = new Thread(incrementAction);
            threads[i].start();
        }
        for (Thread t : threads) t.join();  // wait for all
        return 0;
    }

    public static void main(String[] args) throws InterruptedException {

        // ── RACE CONDITION DEMO ───────────────────────────────
        UnsafeCounter unsafe = new UnsafeCounter();
        Thread[] threads = new Thread[1000];

        for (int i = 0; i < 1000; i++) {
            threads[i] = new Thread(unsafe::increment);
            threads[i].start();
        }
        for (Thread t : threads) t.join();

        System.out.println("Unsafe counter (expected 1000): " + unsafe.getCount());
        // RESULT: often NOT 1000 — race condition corrupts count!

        // ── SYNCHRONIZED COUNTER ──────────────────────────────
        SynchronizedCounter safe = new SynchronizedCounter();
        Thread[] safeThreads = new Thread[1000];

        for (int i = 0; i < 1000; i++) {
            safeThreads[i] = new Thread(safe::increment);
            safeThreads[i].start();
        }
        for (Thread t : safeThreads) t.join();

        System.out.println("Synchronized counter (expected 1000): " + safe.getCount());
        // RESULT: always 1000 ✅

        // ── ATOMIC COUNTER ─────────────────────────────────────
        AtomicCounter atomic = new AtomicCounter();
        Thread[] atomicThreads = new Thread[1000];

        for (int i = 0; i < 1000; i++) {
            atomicThreads[i] = new Thread(atomic::increment);
            atomicThreads[i].start();
        }
        for (Thread t : atomicThreads) t.join();

        System.out.println("Atomic counter (expected 1000):       " + atomic.getCount());
        // RESULT: always 1000 ✅ and FASTER than synchronized

        // ── VOLATILE keyword ──────────────────────────────────
        // volatile guarantees VISIBILITY — not atomicity.
        // Without volatile: each thread may cache variable in CPU register.
        // With volatile: reads/writes always go to MAIN MEMORY.
        // Use for: flags, stop signals between threads (not for counters).
        // volatile does NOT replace synchronized for compound operations.
        demonstrateVolatile();

        // ── ATOMIC OPERATIONS BEYOND INCREMENT ────────────────
        AtomicInteger ai = new AtomicInteger(10);

        System.out.println("\n=== AtomicInteger operations ===");
        System.out.println("get():              " + ai.get());          // 10
        System.out.println("getAndIncrement():  " + ai.getAndIncrement()); // 10, then 11
        System.out.println("incrementAndGet():  " + ai.incrementAndGet()); // 12 (increments first)
        System.out.println("addAndGet(5):       " + ai.addAndGet(5));    // 17
        System.out.println("getAndSet(0):       " + ai.getAndSet(0));    // 17 (sets to 0)

        // compareAndSet(expected, update) — CAS operation
        // Only updates if current value equals expected
        boolean updated = ai.compareAndSet(0, 100);  // current=0, expected=0 → update to 100
        System.out.println("CAS success: " + updated + " | Value: " + ai.get());  // true, 100

        boolean failed = ai.compareAndSet(0, 999);   // current=100, expected=0 → FAIL
        System.out.println("CAS success: " + failed  + " | Value: " + ai.get());  // false, 100
    }

    // ── VOLATILE FLAG EXAMPLE ─────────────────────────────────
    private static volatile boolean running = true;   // volatile flag

    static void demonstrateVolatile() throws InterruptedException {
        System.out.println("\n=== Volatile flag demo ===");

        Thread worker = new Thread(() -> {
            int iterations = 0;
            // Without volatile: worker may NEVER see running=false
            // because it caches the value in CPU register
            while (running) {
                iterations++;
                if (iterations % 100_000 == 0) {
                    System.out.println("Worker running... " + iterations);
                }
            }
            System.out.println("Worker stopped after " + iterations + " iterations");
        });

        worker.start();
        Thread.sleep(10);  // let worker run a bit

        running = false;   // volatile write — immediately visible to worker thread
        worker.join();
        System.out.println("Worker thread terminated");
    }
    
}
