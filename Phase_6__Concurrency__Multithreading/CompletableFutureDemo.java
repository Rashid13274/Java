package Phase_6__Concurrency__Multithreading;
// ─────────────────────────────────────────────────────────────
// PHASE 6 — L5: CompletableFuture
//
// CompletableFuture = Java's answer to Promises in JavaScript.
// Introduced in Java 8 — supports true async, non-blocking chains.
//
// PROBLEMS with regular Future:
//   - get() BLOCKS the calling thread (no .then() chaining)
//   - Cannot combine multiple futures easily
//   - Cannot handle exceptions cleanly
//   - No callback support
//
// CompletableFuture SOLVES all of this:
//   - Non-blocking chaining: thenApply(), thenCompose(), thenAccept()
//   - Combining: allOf(), anyOf(), thenCombine()
//   - Exception handling: exceptionally(), handle(), whenComplete()
//
// Node.js parallel:
//   CompletableFuture.supplyAsync(fn) ≈ Promise.resolve(fn())
//   .thenApply(fn)                    ≈ .then(fn)
//   .thenCompose(fn)                  ≈ .then(fn)  (when fn returns Promise)
//   .exceptionally(fn)                ≈ .catch(fn)
//   .handle(fn)                       ≈ .then(val, err => ...)
//   CompletableFuture.allOf(...)      ≈ Promise.all([...])
//   CompletableFuture.anyOf(...)      ≈ Promise.race([...])
// ─────────────────────────────────────────────────────────────

import java.util.concurrent.*;
// import java.util.*;
// import java.util.stream.*;

public class CompletableFutureDemo {

    // ── SIMULATE ASYNC SERVICES ───────────────────────────────
    static String fetchUser(int userId) throws InterruptedException {
        Thread.sleep(300);
        if (userId <= 0) throw new RuntimeException("Invalid user ID: " + userId);
        return "User-" + userId;
    }

    static String fetchOrders(String user) throws InterruptedException {
        Thread.sleep(400);
        return "[Order-101, Order-102] for " + user;
    }

    static double fetchDiscount(String user) throws InterruptedException {
        Thread.sleep(200);
        return user.contains("1") ? 0.20 : 0.10;  // 20% for user 1, 10% others
    }

    public static void main(String[] args)
            throws InterruptedException, ExecutionException {

        // ── CREATING CompletableFuture ────────────────────────

        // supplyAsync — async task that RETURNS a value (like Supplier)
        // Runs in ForkJoinPool.commonPool() by default
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            try { return fetchUser(1); }
            catch (InterruptedException e) { throw new RuntimeException(e); }
        });

        // runAsync — async task with NO return value (like Runnable)
        CompletableFuture<Void> cf2 = CompletableFuture.runAsync(() ->
                System.out.println("Async log task running"));

        // Use custom thread pool (recommended in production)
        ExecutorService pool = Executors.newFixedThreadPool(4);
        CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(
                () -> "Using custom pool", pool
        );

        // ── CHAINING — thenApply, thenAccept, thenRun ─────────

        // thenApply() — transform result (like .map() / .then(val => ...))
        // Returns CompletableFuture<NewType>
        CompletableFuture<String> userFuture = CompletableFuture
                .supplyAsync(() -> {
                    try { return fetchUser(1); }
                    catch (InterruptedException e) { throw new RuntimeException(e); }
                })
                .thenApply(user -> user.toUpperCase())           // transform
                .thenApply(user -> "PROCESSED: " + user);        // chain another

        System.out.println(userFuture.get());  // PROCESSED: USER-1

        // thenAccept() — consume result, no return (like .forEach())
        CompletableFuture.supplyAsync(() -> "Rashid")
                .thenAccept(name -> System.out.println("Hello, " + name));
        // returns CompletableFuture<Void>

        // thenRun() — run action after completion, ignore result
        CompletableFuture.supplyAsync(() -> "Done with task")
                .thenRun(() -> System.out.println("Cleanup after task"));

        Thread.sleep(200);  // let async tasks run

        // ── thenCompose — chaining async → async ──────────────
        // thenApply: sync transform  → CF<CF<T>>  (DON'T use for async)
        // thenCompose: async transform → CF<T>    (use when next step is also async)
        // Like .flatMap() for Streams — or .then(val => Promise) in JS

        System.out.println("\n=== thenCompose (sequential async chain) ===");
        CompletableFuture<String> orderPipeline = CompletableFuture
                .supplyAsync(() -> {
                    try { return fetchUser(1); }
                    catch (InterruptedException e) { throw new RuntimeException(e); }
                })
                .thenCompose(user ->
                    // This step is also ASYNC — thenCompose flattens the result
                    CompletableFuture.supplyAsync(() -> {
                        try { return fetchOrders(user); }
                        catch (InterruptedException e) { throw new RuntimeException(e); }
                    })
                );

        System.out.println(orderPipeline.get());  // [Order-101, Order-102] for User-1

        // ── thenCombine — run TWO async tasks in PARALLEL, combine results ──
        // Like Promise.all([p1, p2]).then(([r1, r2]) => combine(r1, r2))
        System.out.println("\n=== thenCombine (parallel + combine) ===");

        long start = System.currentTimeMillis();

        CompletableFuture<String> userCF = CompletableFuture.supplyAsync(() -> {
            try { return fetchUser(1); }
            catch (InterruptedException e) { throw new RuntimeException(e); }
        });

        CompletableFuture<Double> discountCF = CompletableFuture.supplyAsync(() -> {
            try { return fetchDiscount("User-1"); }
            catch (InterruptedException e) { throw new RuntimeException(e); }
        });

        // Both run in PARALLEL — thenCombine waits for BOTH, then combines
        CompletableFuture<String> combined = userCF.thenCombine(
                discountCF,
                (user, discount) -> user + " gets " + (discount * 100) + "% discount"
        );

        System.out.println(combined.get());  // User-1 gets 20.0% discount
        System.out.println("Parallel time: " + (System.currentTimeMillis() - start) + "ms");
        // ~300ms (max of 300ms user + 200ms discount) NOT 500ms (sequential)

        // ── allOf — wait for ALL to complete ─────────────────
        // Like Promise.all([...]) in JS
        System.out.println("\n=== allOf (wait for all) ===");

        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(300); return "Task1-done"; }
            catch (InterruptedException e) { throw new RuntimeException(e); }
        });
        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(200); return "Task2-done"; }
            catch (InterruptedException e) { throw new RuntimeException(e); }
        });
        CompletableFuture<String> task3 = CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(400); return "Task3-done"; }
            catch (InterruptedException e) { throw new RuntimeException(e); }
        });

        // allOf returns CompletableFuture<Void> — doesn't collect results directly
        CompletableFuture<Void> allDone = CompletableFuture.allOf(task1, task2, task3);

        allDone.thenRun(() -> {
            try {
                // Get results after all done — get() won't block (already complete)
                System.out.println("All done: "
                        + task1.get() + ", " + task2.get() + ", " + task3.get());
            } catch (Exception e) { e.printStackTrace(); }
        });
        allDone.get();  // block main thread until all complete

        // ── anyOf — first one to complete wins ───────────────
        // Like Promise.race([...]) in JS
        System.out.println("\n=== anyOf (first wins) ===");
        CompletableFuture<Object> firstDone = CompletableFuture.anyOf(
                CompletableFuture.supplyAsync(() -> {
                    try { Thread.sleep(500); return "Slow server"; }
                    catch (InterruptedException e) { throw new RuntimeException(e); }
                }),
                CompletableFuture.supplyAsync(() -> {
                    try { Thread.sleep(100); return "Fast server"; }  // wins!
                    catch (InterruptedException e) { throw new RuntimeException(e); }
                }),
                CompletableFuture.supplyAsync(() -> {
                    try { Thread.sleep(300); return "Medium server"; }
                    catch (InterruptedException e) { throw new RuntimeException(e); }
                })
        );
        System.out.println("First: " + firstDone.get());  // Fast server

        // ── EXCEPTION HANDLING ────────────────────────────────
        System.out.println("\n=== Exception Handling ===");

        // exceptionally() — like .catch() in JS — provide fallback on error
        CompletableFuture<String> withFallback = CompletableFuture
                .supplyAsync(() -> {
                    try { return fetchUser(-1); }  // throws exception
                    catch (InterruptedException e) { throw new RuntimeException(e); }
                })
                .exceptionally(ex -> {
                    System.out.println("Error: " + ex.getCause().getMessage());
                    return "Guest User";  // fallback value
                });
        System.out.println("Result: " + withFallback.get());  // Guest User

        // handle() — runs for BOTH success and failure (like .then(ok, err))
        CompletableFuture<String> handled = CompletableFuture
                .supplyAsync(() -> {
                    try { return fetchUser(2); }
                    catch (InterruptedException e) { throw new RuntimeException(e); }
                })
                .handle((result, ex) -> {
                    if (ex != null) {
                        return "Error: " + ex.getMessage();  // failure path
                    }
                    return "Success: " + result;              // success path
                });
        System.out.println(handled.get());  // Success: User-2

        // whenComplete() — like finally — runs on both, doesn't change result
        CompletableFuture.supplyAsync(() -> "Final task")
                .whenComplete((result, ex) -> {
                    if (ex != null) System.out.println("Failed: " + ex);
                    else System.out.println("Completed: " + result);
                    // result flows through unchanged — good for logging
                });

        Thread.sleep(200);
        pool.shutdown();
    }
}