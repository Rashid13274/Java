package Phase_6__Concurrency__Multithreading;

 // ─────────────────────────────────────────────────────────────
// PHASE 6 — L9: Real-World Concurrency Patterns
//
// Patterns used in actual Spring Boot backend services:
//   1. Parallel API calls with CompletableFuture
//   2. Rate limiter with Semaphore
//   3. Cache with double-checked locking
//   4. Worker thread pool for background tasks
// ─────────────────────────────────────────────────────────────

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
// import java.util.stream.*;

public class RealWorldConcurrency {

    // ── PATTERN 1: PARALLEL API CALLS ─────────────────────────
    // Fetch user + orders + recommendations simultaneously
    // Instead of: 300ms + 400ms + 200ms = 900ms sequential
    // With parallel: max(300, 400, 200) = 400ms!
    static class DashboardService {

        private final ExecutorService pool = Executors.newFixedThreadPool(10);

        // Simulate external service calls
        private String fetchUserProfile(int userId) throws InterruptedException {
            Thread.sleep(300);
            return "Profile{id=" + userId + ", name=Rashid}";
        }

        private List<String> fetchOrders(int userId) throws InterruptedException {
            Thread.sleep(400);
            return List.of("Order-101", "Order-102", "Order-103");
        }

        private List<String> fetchRecommendations(int userId) throws InterruptedException {
            Thread.sleep(200);
            return List.of("Product-A", "Product-B");
        }

        // All three calls happen in PARALLEL
        public Map<String, Object> getDashboardData(int userId) throws Exception {
            long start = System.currentTimeMillis();

            CompletableFuture<String> profileFuture =
                    CompletableFuture.supplyAsync(() -> {
                        try { return fetchUserProfile(userId); }
                        catch (InterruptedException e) { throw new RuntimeException(e); }
                    }, pool);

            CompletableFuture<List<String>> ordersFuture =
                    CompletableFuture.supplyAsync(() -> {
                        try { return fetchOrders(userId); }
                        catch (InterruptedException e) { throw new RuntimeException(e); }
                    }, pool);

            CompletableFuture<List<String>> recsFuture =
                    CompletableFuture.supplyAsync(() -> {
                        try { return fetchRecommendations(userId); }
                        catch (InterruptedException e) { throw new RuntimeException(e); }
                    }, pool);

            // Wait for all three and build result
            CompletableFuture.allOf(profileFuture, ordersFuture, recsFuture).get();

            Map<String, Object> dashboard = new LinkedHashMap<>();
            dashboard.put("profile",         profileFuture.get());
            dashboard.put("orders",          ordersFuture.get());
            dashboard.put("recommendations", recsFuture.get());

            System.out.println("Dashboard built in: "
                    + (System.currentTimeMillis() - start) + "ms");
            return dashboard;
        }

        public void shutdown() { pool.shutdown(); }
    }

    // ── PATTERN 2: RATE LIMITER with Semaphore ─────────────────
    // Limit: max N concurrent requests to external service
    static class RateLimitedService {

        private final Semaphore semaphore;
        private final String    serviceName;
        private final AtomicInteger successCount = new AtomicInteger(0);
        private final AtomicInteger rejectedCount = new AtomicInteger(0);

        public RateLimitedService(String name, int maxConcurrent) {
            this.serviceName = name;
            this.semaphore   = new Semaphore(maxConcurrent);
        }

        public String callService(String request) {
            // tryAcquire() — don't block, return false if no permits
            if (!semaphore.tryAcquire()) {
                rejectedCount.incrementAndGet();
                return "RATE_LIMITED: " + request;   // reject gracefully
            }
            try {
                // Permit acquired — call the service
                Thread.sleep(200);  // simulate service call
                successCount.incrementAndGet();
                return "SUCCESS: " + request;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return "INTERRUPTED";
            } finally {
                semaphore.release();  // always release!
            }
        }

        public void printStats() {
            System.out.printf("[%s] Success: %d | Rejected: %d%n",
                    serviceName, successCount.get(), rejectedCount.get());
        }
    }

    // ── PATTERN 3: THREAD-SAFE SINGLETON with Double-Checked Locking ──
    // Ensures only one instance is created even with multiple threads
    static class AppConfig {

        // 'volatile' ensures visibility of the initialized instance
        private static volatile AppConfig instance;

        private final Map<String, String> config;

        private AppConfig() {
            // Expensive initialization — read from DB/file
            config = new ConcurrentHashMap<>();
            config.put("db.url",  "jdbc:mysql://localhost:3306/mydb");
            config.put("api.key", "secret-key-123");
            config.put("max.connections", "20");
            System.out.println("AppConfig initialized (once!)");
        }

        // Double-checked locking — thread-safe + lazy + fast
        public static AppConfig getInstance() {
            if (instance == null) {                    // check 1: avoid sync if already set
                synchronized (AppConfig.class) {
                    if (instance == null) {            // check 2: inside lock — safe creation
                        instance = new AppConfig();
                    }
                }
            }
            return instance;   // volatile read — always latest value
        }

        public String get(String key) { return config.getOrDefault(key, "not-found"); }
    }

    // ── PATTERN 4: BACKGROUND TASK QUEUE ──────────────────────
    // Process tasks asynchronously — like Spring's @Async
    static class BackgroundTaskProcessor {

        private final BlockingQueue<Runnable> taskQueue;
        private final ExecutorService         workers;
        private volatile boolean              running = true;

        public BackgroundTaskProcessor(int workerCount, int queueCapacity) {
            this.taskQueue = new ArrayBlockingQueue<>(queueCapacity);
            this.workers   = Executors.newFixedThreadPool(workerCount);

            // Start workers that drain the queue
            for (int i = 0; i < workerCount; i++) {
                workers.submit(this::processTasks);
            }
        }

        private void processTasks() {
            while (running || !taskQueue.isEmpty()) {
                try {
                    // poll with timeout so we can check 'running' flag
                    Runnable task = taskQueue.poll(100, TimeUnit.MILLISECONDS);
                    if (task != null) task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }

        public boolean submit(Runnable task) {
            // offer() — non-blocking, returns false if queue is full
            boolean accepted = taskQueue.offer(task);
            if (!accepted) System.out.println("⚠️  Queue full! Task rejected.");
            return accepted;
        }

        public void shutdown() throws InterruptedException {
            running = false;
            workers.shutdown();
            workers.awaitTermination(10, TimeUnit.SECONDS);
        }
    }

    public static void main(String[] args) throws Exception {

        // ── Pattern 1: Parallel API calls ─────────────────────
        System.out.println("=== Pattern 1: Parallel Dashboard Fetch ===");
        DashboardService dashboard = new DashboardService();
        Map<String, Object> data = dashboard.getDashboardData(1);
        data.forEach((k, v) -> System.out.println(k + ": " + v));
        dashboard.shutdown();

        // ── Pattern 2: Rate Limiter ────────────────────────────
        System.out.println("\n=== Pattern 2: Rate Limiter (max 3 concurrent) ===");
        RateLimitedService rls = new RateLimitedService("PaymentService", 3);
        ExecutorService testPool = Executors.newFixedThreadPool(8);

        for (int i = 1; i <= 10; i++) {
            final int id = i;
            testPool.submit(() -> {
                String result = rls.callService("REQ-" + id);
                System.out.println(result);
            });
        }
        testPool.shutdown();
        testPool.awaitTermination(5, TimeUnit.SECONDS);
        rls.printStats();

        // ── Pattern 3: Thread-Safe Singleton ──────────────────
        System.out.println("\n=== Pattern 3: Singleton ===");
        ExecutorService singletonPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            singletonPool.submit(() -> {
                AppConfig cfg = AppConfig.getInstance();
                System.out.println("[" + Thread.currentThread().getName()
                                   + "] db.url = " + cfg.get("db.url"));
            });
        }
        singletonPool.shutdown();
        singletonPool.awaitTermination(5, TimeUnit.SECONDS);

        // ── Pattern 4: Background Tasks ───────────────────────
        System.out.println("\n=== Pattern 4: Background Task Queue ===");
        BackgroundTaskProcessor processor = new BackgroundTaskProcessor(2, 5);

        for (int i = 1; i <= 7; i++) {
            final int id = i;
            processor.submit(() -> {
                try {
                    System.out.println("[" + Thread.currentThread().getName()
                                       + "] Processing task " + id);
                    Thread.sleep(300);
                    System.out.println("[" + Thread.currentThread().getName()
                                       + "] Task " + id + " done");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        Thread.sleep(3000);
        processor.shutdown();
        System.out.println("Background processor shut down");
    }
    
}
