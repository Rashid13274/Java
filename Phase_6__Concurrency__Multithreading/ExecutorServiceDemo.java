package Phase_6__Concurrency__Multithreading;

 // ─────────────────────────────────────────────────────────────
// PHASE 6 — L4: ExecutorService & Thread Pools
//
// Creating raw threads is expensive — each thread needs ~1MB stack.
// Creating a new Thread for every task = bad performance.
//
// THREAD POOL: reuse a fixed set of threads — tasks are queued
// and picked up by available threads. Far more efficient.
//
// ExecutorService is the standard Java API for thread pools.
// Spring Boot's @Async and request handling use thread pools internally.
//
// EXECUTOR TYPES (Executors factory methods):
//   newFixedThreadPool(n)       → fixed n threads, queue extra tasks
//   newCachedThreadPool()       → creates threads as needed, reuses idle ones
//   newSingleThreadExecutor()   → exactly 1 thread, tasks run sequentially
//   newScheduledThreadPool(n)   → runs tasks at fixed rate/delay (like setInterval)
//
// ALWAYS shutdown() the pool when done — else JVM won't exit!
//
// Node.js parallel:
//   ExecutorService ≈ worker_threads pool (Node.js 12+)
//   submit()        ≈ posting a task to the worker pool
//   Future          ≈ Promise (but blocking, not async)
// ─────────────────────────────────────────────────────────────

import java.util.concurrent.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorServiceDemo {

    // ── SIMULATE A TASK ───────────────────────────────────────
    static String processRequest(String requestId) throws InterruptedException {
        System.out.printf("[%s] Processing %s...%n",
                Thread.currentThread().getName(), requestId);
        Thread.sleep((long)(Math.random() * 500 + 200));  // 200-700ms
        System.out.printf("[%s] Completed %s%n",
                Thread.currentThread().getName(), requestId);
        return "Result-" + requestId;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // ── FIXED THREAD POOL ─────────────────────────────────
        // Like Spring Boot's server thread pool — handles requests concurrently
        System.out.println("=== Fixed Thread Pool (4 threads, 10 tasks) ===");

        ExecutorService pool = Executors.newFixedThreadPool(4);
        // Only 4 threads run at a time — remaining 6 tasks wait in queue

        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            pool.submit(() -> {    // submit() adds task to queue — returns Future
                try {
                    processRequest("REQ-" + taskId);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        pool.shutdown();           // no more new tasks accepted
        pool.awaitTermination(30, TimeUnit.SECONDS);  // wait for all to finish
        System.out.println("All tasks done\n");

        // ── CALLABLE + FUTURE ─────────────────────────────────
        // Runnable has no return value.
        // Callable<V> is like Runnable but RETURNS a result.
        // submit(Callable) returns a Future<V> — get the result later.
        // Future.get() BLOCKS until result is ready (like await in JS).

        System.out.println("=== Callable & Future ===");
        ExecutorService callablePool = Executors.newFixedThreadPool(3);

        // Submit Callable tasks — get Future handles
        Future<String> f1 = callablePool.submit(() -> processRequest("API-1"));
        Future<String> f2 = callablePool.submit(() -> processRequest("API-2"));
        Future<String> f3 = callablePool.submit(() -> processRequest("API-3"));
        // All 3 start running in parallel!

        // Do other work here while tasks run...
        System.out.println("Tasks submitted, doing other work...");

        // Now get results — blocks if not ready yet
        System.out.println("Result 1: " + f1.get());   // blocks until f1 done
        System.out.println("Result 2: " + f2.get());   // blocks until f2 done
        System.out.println("Result 3: " + f3.get());   // blocks until f3 done

        callablePool.shutdown();
        System.out.println();

        // ── FUTURE METHODS ────────────────────────────────────
        ExecutorService futurePool = Executors.newSingleThreadExecutor();
        Future<Integer> future = futurePool.submit(() -> {
            Thread.sleep(2000);
            return 42;
        });

        System.out.println("isDone: " + future.isDone());       // false — still running
        System.out.println("isCancelled: " + future.isCancelled()); // false

        // get with timeout — don't block forever
        try {
            Integer val = future.get(3, TimeUnit.SECONDS);  // max 3 sec wait
            System.out.println("Got: " + val);
        } catch (TimeoutException e) {
            System.out.println("Timed out — task still running");
            future.cancel(true);   // interrupt the task
        }

        futurePool.shutdown();

        // ── invokeAll() — submit batch, wait for ALL ─────────
        System.out.println("\n=== invokeAll() — batch tasks ===");
        ExecutorService batchPool = Executors.newFixedThreadPool(4);

        List<Callable<String>> tasks = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            final int id = i;
            tasks.add(() -> processRequest("BATCH-" + id));
        }

        // Blocks until ALL tasks complete — returns list of Futures
        List<Future<String>> results = batchPool.invokeAll(tasks);
        System.out.println("\nAll batch results:");
        for (Future<String> result : results) {
            System.out.println("  " + result.get());  // won't block — all done
        }
        batchPool.shutdown();

        // ── invokeAny() — submit batch, return FIRST completed ─
        System.out.println("\n=== invokeAny() — first result wins ===");
        ExecutorService anyPool = Executors.newFixedThreadPool(3);

        List<Callable<String>> raceTasks = new ArrayList<>();
        raceTasks.add(() -> { Thread.sleep(300); return "Server-1 responded"; });
        raceTasks.add(() -> { Thread.sleep(100); return "Server-2 responded"; });  // fastest!
        raceTasks.add(() -> { Thread.sleep(500); return "Server-3 responded"; });

        // Returns the first task to complete — others are cancelled
        String firstResult = anyPool.invokeAny(raceTasks);
        System.out.println("First result: " + firstResult);  // Server-2
        anyPool.shutdown();

        // ── SCHEDULED EXECUTOR — run periodically ─────────────
        System.out.println("\n=== ScheduledExecutorService ===");
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        // Run once after 1 second delay (like setTimeout in JS)
        scheduler.schedule(
                () -> System.out.println("Delayed task ran after 1s"),
                1, TimeUnit.SECONDS
        );

        // Run every 500ms (like setInterval in JS)
        AtomicInteger runCount = new AtomicInteger(0);
        ScheduledFuture<?> periodicTask = scheduler.scheduleAtFixedRate(
                () -> System.out.println("Periodic task #" + runCount.incrementAndGet()),
                0,   // initial delay
                500, // period
                TimeUnit.MILLISECONDS
        );

        Thread.sleep(2500);        // let it run ~5 times
        periodicTask.cancel(false); // stop periodic task
        scheduler.shutdown();
        scheduler.awaitTermination(2, TimeUnit.SECONDS);
        System.out.println("Scheduler stopped");
    }
    
}
