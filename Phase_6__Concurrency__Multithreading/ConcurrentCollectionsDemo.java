package Phase_6__Concurrency__Multithreading;

 // ─────────────────────────────────────────────────────────────
// PHASE 6 — L6: Concurrent Collections
//
// Standard collections (ArrayList, HashMap) are NOT thread-safe.
// Using them from multiple threads = data corruption, infinite loops.
//
// THREE OPTIONS for thread-safe collections:
//
//   1. Collections.synchronizedXxx() — wrap existing collection
//      synchronized(list) {} block still needed for iteration!
//      Coarse-grained lock — entire collection locked per operation.
//
//   2. java.util.concurrent package — designed for concurrency
//      ConcurrentHashMap     → thread-safe HashMap, segment locking
//      CopyOnWriteArrayList  → thread-safe List, great for read-heavy
//      BlockingQueue         → thread-safe Queue with wait/notify built-in
//      ConcurrentLinkedQueue → lock-free thread-safe Queue
//
//   3. Synchronized collections (legacy — avoid in new code)
//      Vector, Hashtable — entire object lock, poor performance
//
// ConcurrentHashMap vs synchronized HashMap:
//   synchronized HashMap → ONE lock for entire map
//   ConcurrentHashMap    → lock per SEGMENT (16 by default) — much faster!
//   ConcurrentHashMap    → allows concurrent READS without locking
// ─────────────────────────────────────────────────────────────

// import java.util.*;
import java.util.concurrent.*;
// import java.util.concurrent.atomic.*;

public class ConcurrentCollectionsDemo {

    public static void main(String[] args) throws InterruptedException {

        // ── ConcurrentHashMap ──────────────────────────────────
        // Most used in Spring Boot caches, session stores, counters
        ConcurrentHashMap<String, Integer> visitCount = new ConcurrentHashMap<>();

        // Multiple threads incrementing visit counts simultaneously
        ExecutorService pool = Executors.newFixedThreadPool(10);
        String[] pages = {"home", "about", "products", "home", "products", "home"};

        for (String page : pages) {
            pool.submit(() -> {
                // merge() is atomic in ConcurrentHashMap
                visitCount.merge(page, 1, Integer::sum);
            });
        }
        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("Visit counts: " + visitCount);
        // {home=3, about=1, products=2} — always correct!

        // ConcurrentHashMap atomic operations
        ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();
        cache.put("key1", "value1");

        // putIfAbsent — atomic check-then-act (no race condition)
        cache.putIfAbsent("key1", "value2");   // NOT updated — key1 exists
        cache.putIfAbsent("key2", "value2");   // updated — key2 doesn't exist
        System.out.println(cache);

        // computeIfAbsent — atomic, create value only if absent
        cache.computeIfAbsent("key3", k -> "computed-" + k);
        System.out.println(cache.get("key3"));  // computed-key3

        // ── CopyOnWriteArrayList ───────────────────────────────
        // Every WRITE creates a COPY of the array.
        // Reads are lock-free — great for read-heavy, rare-write scenarios.
        // Use for: listener lists, config caches, event handlers.

        CopyOnWriteArrayList<String> listeners = new CopyOnWriteArrayList<>();

        // Writer thread
        Thread writer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                listeners.add("Listener-" + i);
                try { Thread.sleep(100); }
                catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        });

        // Reader thread — safe to iterate even while writer modifies
        Thread reader = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                // Iterates on a SNAPSHOT — no ConcurrentModificationException
                System.out.println("Reading: " + listeners);
                try { Thread.sleep(150); }
                catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        });

        writer.start();
        reader.start();
        writer.join();
        reader.join();

        // ── BLOCKING QUEUES ────────────────────────────────────
        // BlockingQueue is the BEST tool for Producer-Consumer pattern.
        // put()  → adds item, BLOCKS if queue is full (waits for space)
        // take() → removes item, BLOCKS if queue is empty (waits for item)
        // No manual wait/notify needed — it's all built in!

        System.out.println("\n=== BlockingQueue Producer-Consumer ===");

        // ArrayBlockingQueue — fixed capacity, FIFO
        BlockingQueue<String> taskQueue = new ArrayBlockingQueue<>(5);

        // Producer — puts tasks in queue
        Thread producer = new Thread(() -> {
            String[] tasks = {"T1","T2","T3","T4","T5","T6","T7","T8"};
            for (String task : tasks) {
                try {
                    taskQueue.put(task);   // blocks if full — no explicit sync needed!
                    System.out.println("[PRODUCER] Queued: " + task
                                       + " | Size: " + taskQueue.size());
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }, "Producer");

        // Consumer — takes tasks from queue
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 8; i++) {
                try {
                    String task = taskQueue.take();  // blocks if empty!
                    System.out.println("[CONSUMER] Processing: " + task);
                    Thread.sleep(300);   // slower consumer
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }, "Consumer");

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();

        // ── OTHER BLOCKING QUEUE TYPES ────────────────────────
        // LinkedBlockingQueue  → optionally bounded, better throughput
        // PriorityBlockingQueue→ priority ordered, unbounded
        // DelayQueue           → elements available after delay (like setTimeout)
        // SynchronousQueue     → no capacity — direct handoff between threads

        // ── CountDownLatch — one-time gate ─────────────────────
        // Wait for N events before continuing.
        // Like Promise.all() but for thread coordination.
        System.out.println("\n=== CountDownLatch ===");

        int workerCount = 3;
        CountDownLatch latch = new CountDownLatch(workerCount);

        for (int i = 1; i <= workerCount; i++) {
            final int workerId = i;
            new Thread(() -> {
                try {
                    System.out.println("Worker " + workerId + " starting...");
                    Thread.sleep((long)(Math.random() * 500 + 200));
                    System.out.println("Worker " + workerId + " DONE");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    latch.countDown();  // decrement count by 1
                }
            }).start();
        }

        System.out.println("Main waiting for all workers...");
        latch.await();  // blocks until count reaches 0
        System.out.println("All workers done — proceeding!");

        // ── CyclicBarrier — reusable rendezvous point ──────────
        // All threads must REACH the barrier before any can PROCEED.
        // Like a starting gate — everyone lines up, then all go together.
        System.out.println("\n=== CyclicBarrier ===");

        CyclicBarrier barrier = new CyclicBarrier(3, () ->
                System.out.println("=== All threads at barrier — RELEASING ==="));

        for (int i = 1; i <= 3; i++) {
            final int id = i;
            new Thread(() -> {
                try {
                    System.out.println("Thread " + id + " doing prep work...");
                    Thread.sleep((long)(Math.random() * 500));
                    System.out.println("Thread " + id + " reached barrier");
                    barrier.await();  // wait for all threads
                    System.out.println("Thread " + id + " proceeding after barrier");
                } catch (Exception e) { Thread.currentThread().interrupt(); }
            }).start();
        }

        Thread.sleep(2000);

        // ── Semaphore — limit concurrent access ───────────────
        // Allows exactly N threads to access a resource simultaneously.
        // Like a parking lot with N spaces.
        System.out.println("\n=== Semaphore (3 permits) ===");

        Semaphore semaphore = new Semaphore(3);  // only 3 threads at a time

        for (int i = 1; i <= 6; i++) {
            final int id = i;
            new Thread(() -> {
                try {
                    semaphore.acquire();  // get a permit (blocks if 0 available)
                    System.out.println("Thread " + id + " acquired permit | "
                                       + semaphore.availablePermits() + " left");
                    Thread.sleep(500);   // use the resource
                    semaphore.release(); // release permit for others
                    System.out.println("Thread " + id + " released permit");
                } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }).start();
        }

        Thread.sleep(3000);
    }
    
}
