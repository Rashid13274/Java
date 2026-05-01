package Phase_6__Concurrency__Multithreading;

 // ─────────────────────────────────────────────────────────────
// PHASE 6 — L1: Threads & Runnable
//
// A Thread = an independent path of execution inside a process.
// Your JVM runs multiple threads simultaneously.
//
// Node.js is SINGLE-THREADED (one event loop).
// Java is MULTI-THREADED — true parallel execution on multiple CPU cores.
//
// WHY CONCURRENCY MATTERS IN BACKEND:
//   Every incoming HTTP request in Spring Boot is handled by a
//   separate thread from a thread pool.
//   Understanding threads = understanding how your server handles load.
//
// TWO WAYS TO CREATE A THREAD:
//   1. Extend Thread class          (older, less flexible)
//   2. Implement Runnable interface (preferred — separates task from thread)
//   3. Implement Callable<V>        (like Runnable but RETURNS a value)
//
// THREAD LIFECYCLE:
//   NEW → RUNNABLE → RUNNING → BLOCKED/WAITING → TERMINATED
//
//   NEW       → Thread object created, not started yet
//   RUNNABLE  → start() called, waiting for CPU time
//   RUNNING   → CPU executing thread's run() method
//   BLOCKED   → waiting for a lock (synchronized block)
//   WAITING   → waiting indefinitely (wait(), join())
//   TIMED_WAIT→ waiting with timeout (sleep(), wait(ms))
//   TERMINATED→ run() completed or exception thrown
// ─────────────────────────────────────────────────────────────

public class ThreadBasics {

    // ── WAY 1: EXTEND Thread ──────────────────────────────────
    // Simple but NOT recommended — tightly couples task + thread.
    // Can't extend another class (Java = single inheritance).
    static class DownloadThread extends Thread {

        private String fileName;

        public DownloadThread(String fileName) {
            super("DownloadThread-" + fileName);  // give thread a name — helps debugging
            this.fileName = fileName;
        }

        // run() contains the code that the thread executes
        // NEVER call run() directly — that runs in CURRENT thread!
        // ALWAYS call start() — that creates a NEW thread and calls run()
        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println("[" + threadName + "] Starting download: " + fileName);

            try {
                // Simulate download time — sleep() pauses current thread
                Thread.sleep(1000);   // 1 second
            } catch (InterruptedException e) {
                // Thread was interrupted — handle gracefully
                System.out.println("[" + threadName + "] Download interrupted!");
                Thread.currentThread().interrupt();  // restore interrupt flag
                return;
            }

            System.out.println("[" + threadName + "] Finished: " + fileName);
        }
    }

    // ── WAY 2: IMPLEMENT Runnable ────────────────────────────
    // PREFERRED — separates the TASK (Runnable) from the THREAD.
    // Can implement other interfaces too.
    // Works with thread pools (ExecutorService) — can't use Thread subclass there.
    static class ProcessingTask implements Runnable {

        private String taskName;
        private int    durationMs;

        public ProcessingTask(String taskName, int durationMs) {
            this.taskName   = taskName;
            this.durationMs = durationMs;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println("[" + name + "] Task started: " + taskName);

            try {
                Thread.sleep(durationMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            System.out.println("[" + name + "] Task done: " + taskName
                               + " (" + durationMs + "ms)");
        }
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Main thread: " + Thread.currentThread().getName());

        // ── STARTING THREADS ──────────────────────────────────

        // Way 1: Thread subclass
        DownloadThread t1 = new DownloadThread("report.pdf");
        DownloadThread t2 = new DownloadThread("data.csv");
        t1.start();   // ✅ creates new thread, calls run() in it
        t2.start();   // t1 and t2 run in PARALLEL
        // t1.run();  // ❌ WRONG — runs in main thread, no parallelism!

        // Way 2: Runnable + Thread wrapper
        Runnable task = new ProcessingTask("EmailProcessor", 800);
        Thread t3 = new Thread(task, "EmailThread");
        t3.start();

        // Way 3: Lambda as Runnable (most concise — Java 8+)
        // Runnable IS a functional interface — one abstract method: run()
        Thread t4 = new Thread(() -> {
            System.out.println("[" + Thread.currentThread().getName()
                               + "] Lambda thread running");
            try { Thread.sleep(500); } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("[" + Thread.currentThread().getName()
                               + "] Lambda thread done");
        }, "LambdaThread");
        t4.start();

        // ── THREAD METHODS ────────────────────────────────────

        // join() — main thread waits for t1 to finish before continuing
        // Like await in Node.js — blocks current thread until target finishes
        t1.join();    // wait for t1
        t2.join();    // wait for t2
        t3.join();
        t4.join();

        System.out.println("\n=== All threads finished ===");

        // ── THREAD INFO ───────────────────────────────────────
        Thread current = Thread.currentThread();
        System.out.println("Name:      " + current.getName());
        System.out.println("ID:        " + current.getId());
        System.out.println("Priority:  " + current.getPriority());  // 1-10, default 5
        System.out.println("Is daemon: " + current.isDaemon());     // daemon = background thread
        System.out.println("State:     " + current.getState());     // RUNNABLE

        // ── THREAD PRIORITY ───────────────────────────────────
        // Priority is a HINT to the OS scheduler — not a guarantee!
        Thread highPriority = new Thread(() -> System.out.println("High priority"));
        Thread lowPriority  = new Thread(() -> System.out.println("Low priority"));

        highPriority.setPriority(Thread.MAX_PRIORITY);  // 10
        lowPriority.setPriority(Thread.MIN_PRIORITY);   // 1

        highPriority.start();
        lowPriority.start();

        // ── DAEMON THREADS ────────────────────────────────────
        // Daemon threads run in background — JVM exits when only daemon threads remain.
        // Set BEFORE calling start().
        // Use for: background cleanup, logging, GC-related tasks.
        Thread daemon = new Thread(() -> {
            while (true) {
                System.out.println("Daemon heartbeat...");
                try { Thread.sleep(2000); } catch (InterruptedException e) { return; }
            }
        });
        daemon.setDaemon(true);   // must set before start()
        daemon.start();
        // When main finishes, JVM exits even though daemon is still running

        Thread.sleep(100);  // let threads print something
        System.out.println("\nMain thread ending — daemon will be killed by JVM");
    }
    
}
