// ─────────────────────────────────────────────────────────────
// PHASE 6 — L10: Interview Questions (Concurrency)     
//L10 — Interview Questions (Concurrency)
// ─────────────────────────────────────────────────────────────

/*
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Q1. What is the difference between process and thread?
ANSWER:

Process:
  - Independent program in execution
  - Has its OWN memory space (heap, stack, code segment)
  - OS creates separate address space
  - Communication between processes = IPC (slow)
  - Crash of one process doesn't affect others

Thread:
  - Lightweight unit of execution WITHIN a process
  - Shares SAME memory/heap with other threads in same process
  - Has its OWN stack and program counter
  - Communication between threads = shared memory (fast, but risky)
  - Crash of one thread can crash the whole process

JAVA CONTEXT:
  JVM runs as a PROCESS.
  Inside JVM: main thread + GC threads + your threads.
  Threads share the same heap (objects) — hence thread safety matters.

INTERVIEW CRISP ANSWER:
  "A process has isolated memory; threads share memory within
   a process. Threads are lighter and faster to create, but need
   synchronization to safely access shared data."
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

   Q2. What is the difference between synchronized method and synchronized block?
   // ANSWER:

// Synchronized METHOD — locks on 'this' object (or Class for static)
// Entire method is locked — even parts that don't need synchronization
public synchronized void fullMethodLock() {
    // non-critical work — unnecessarily locked
    String log = buildLog();       // slow — but locked!
    // critical section
    sharedCounter++;
}

// Synchronized BLOCK — lock only the critical section
public void fineGrainedLock() {
    // non-critical work runs in PARALLEL — no lock
    String log = buildLog();       // runs concurrently ✅

    synchronized (this) {          // only lock when needed
        sharedCounter++;           // critical section only
    }
    // more non-critical work — no lock
}

// PERFORMANCE IMPACT:
//   synchronized method:  ALL threads blocked for entire method duration
//   synchronized block:   threads only blocked for the critical lines

// DIFFERENT LOCK OBJECTS:
//   synchronized (this)       → locks on current object
//   synchronized (MyClass.class) → class-level lock (for static context)
//   synchronized (customLock) → custom lock object (most flexible)

// BEST PRACTICE:
//   Use synchronized block with private final lock object
//   private final Object lock = new Object();
//   synchronized (lock) { ... }
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━


Q3. What is the difference between wait() and sleep()?

ANSWER:
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Feature          wait()                  sleep()
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Class            Object                  Thread
Lock             RELEASES the lock       HOLDS the lock
Wake up          notify()/notifyAll()    After timeout expires
Call from        synchronized block only Anywhere
Purpose          Inter-thread communication  Pause execution
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

KEY POINT:
  wait() RELEASES the lock — other threads CAN enter synchronized block
  sleep() HOLDS the lock  — other threads CANNOT enter synchronized block

// wait() — must be in synchronized block
synchronized(obj) {
    while (!condition) obj.wait();  // releases lock, pauses thread
}

// sleep() — anywhere, holds lock if inside synchronized
Thread.sleep(1000);   // holds lock if inside synchronized!
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Q4. What is volatile and when should you use it?
// ANSWER:
// volatile guarantees VISIBILITY of writes across threads.
// Without volatile, CPU may cache variable in register/L1 cache —
// other threads read stale value from cache, not main memory.

// volatile ensures:
//   1. Every READ goes to main memory (never cached)
//   2. Every WRITE goes to main memory immediately
//   3. Prevents instruction reordering around volatile variable

// WHEN TO USE volatile:
//   ✅ Simple flag/state visible across threads (stop signal)
//   ✅ Singleton double-checked locking pattern
//   ❌ NOT for compound operations (check-then-act, increment)
//      counter++ is NOT atomic even with volatile!

// GOOD USE — stop flag:
private volatile boolean running = true;
// Thread1 sets running = false — Thread2 sees it immediately

// BAD USE — still has race condition:
private volatile int counter = 0;
counter++;  // ❌ still 3 steps: read, add, write — not atomic!
// Use AtomicInteger instead!

// volatile vs synchronized:
//   volatile   → visibility only, no mutual exclusion, no atomicity
//   synchronized → visibility + atomicity + mutual exclusion (heavier)
//   AtomicXxx  → visibility + atomicity, lock-free (best for counters)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Q5. What is the difference between HashMap and ConcurrentHashMap?
ANSWER:

HashMap:
  - NOT thread-safe
  - Single thread only
  - Null keys and values allowed
  - Can cause infinite loop (HashMap resizing is not thread-safe in Java 7)

Collections.synchronizedMap(map):
  - Thread-safe wrapper around HashMap
  - Locks ENTIRE map for every get/put
  - Only one thread accesses map at a time
  - Must manually synchronize during iteration

ConcurrentHashMap:
  - Built for concurrency from the ground up
  - Java 8: uses CAS + synchronize only at bucket level (not whole map)
  - Multiple threads can read SIMULTANEOUSLY (no locking for reads)
  - Writes lock only the affected bucket (segment locking)
  - NO null keys or null values allowed (throws NullPointerException)
  - Atomic compound ops: putIfAbsent(), computeIfAbsent(), merge()

PERFORMANCE:
  HashMap            → fastest (single thread only)
  ConcurrentHashMap  → near HashMap speed for reads, much faster than
                       synchronizedMap for concurrent writes
  synchronizedMap    → slowest concurrent option (full lock)

VERDICT: In multi-threaded Spring Boot service → always ConcurrentHashMap

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Q6. What is a ThreadPool and why is it used?
ANSWER:

ThreadPool = a collection of pre-created, reusable worker threads.

PROBLEM without thread pool:
  For every request → new Thread() → handle → thread dies
  Creating a thread: allocate ~1MB stack, OS registration, setup
  For 10,000 requests → 10,000 thread creations → CPU/memory thrash

ThreadPool SOLUTION:
  Create N threads ONCE at startup.
  For each task → submit to queue → idle thread picks it up.
  Thread finishes task → returns to pool → picks up next task.
  N threads handle ALL requests efficiently.

BENEFITS:
  ✅ No thread creation overhead per request
  ✅ Bounded thread count — no resource exhaustion
  ✅ Task queuing — no requests dropped immediately
  ✅ Reuse — threads stay alive, no GC pressure

SPRING BOOT:
  Embedded Tomcat uses a thread pool for HTTP requests.
  @Async uses a ThreadPoolTaskExecutor.
  Default Tomcat pool: 10 min threads, 200 max threads.
  When all 200 busy → new requests queue → timeout if too long.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

  Q7. What is the difference between Callable and Runnable?

  // ANSWER:
// Both represent a task to run in a thread — but:

// Runnable:
//   - run() method — void return
//   - Cannot throw checked exceptions
//   - Used with Thread, ExecutorService.execute()

Runnable runnable = () -> {
    System.out.println("Running");
    // Cannot return a value
    // Can only throw RuntimeException (unchecked)
};

// Callable<V>:
//   - call() method — returns V
//   - CAN throw checked exceptions
//   - Used with ExecutorService.submit() → returns Future<V>

Callable<String> callable = () -> {
    Thread.sleep(100);   // ✅ can throw checked InterruptedException
    return "Result";     // ✅ returns a value
};

Future<String> future = executorService.submit(callable);
String result = future.get();  // blocks until callable finishes

// CHOICE GUIDE:
//   Need return value?  → Callable
//   Need to throw checked exception? → Callable
//   Just a side effect? → Runnable (simpler)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━


Q8. What is CompletableFuture and how does it improve on Future?

ANSWER:

Future (Java 5) — problems:
  1. get() BLOCKS the calling thread — no way to be notified on completion
  2. Cannot chain multiple futures (no .then() equivalent)
  3. No exception handling API
  4. Cannot combine multiple futures easily
  5. Cannot manually complete (no way to inject a value)

CompletableFuture (Java 8) — solves all problems:
  1. Non-blocking callbacks:   thenApply(), thenAccept(), thenRun()
  2. Chaining:                 thenCompose() for async → async chains
  3. Exception handling:       exceptionally(), handle(), whenComplete()
  4. Combining:                thenCombine(), allOf(), anyOf()
  5. Manual completion:        complete(value), completeExceptionally(ex)

ANALOGY:
  Future         ≈ a synchronous, blocking phone call — you wait
  CompletableFuture ≈ an SMS — you send it, continue your work,
                       your callback fires when reply arrives

IN SPRING BOOT:
  @Async methods return CompletableFuture<T>
  Spring wraps the method in a thread pool and returns a CF immediately
  Caller can chain .thenApply() without blocking

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

  Q9. What is a Deadlock? How do you detect and prevent it?

  ANSWER:

Deadlock = Thread A holds Lock-1 and waits for Lock-2
           Thread B holds Lock-2 and waits for Lock-1
           Neither can proceed — forever stuck

FOUR CONDITIONS (all must hold for deadlock):
  1. Mutual Exclusion  — only one thread can hold a resource
  2. Hold and Wait     — thread holds resource while waiting for another
  3. No Preemption     — resources can't be forcibly taken
  4. Circular Wait     — A waits for B, B waits for A (cycle)

DETECTION:
  - jstack <pid>        → dumps all thread states to console
  - JVisualVM / JConsole → GUI tool, shows "Deadlock Detected"
  - ThreadMXBean programmatically:
    long[] deadlocked = ManagementFactory.getThreadMXBean()
                            .findDeadlockedThreads();

PREVENTION STRATEGIES:
  1. Lock ordering     → always acquire locks in same global order
  2. tryLock(timeout) → don't wait indefinitely (ReentrantLock)
  3. Avoid nested locks → don't acquire lock while holding another
  4. Lock-free data    → use AtomicInteger, ConcurrentHashMap
  5. Reduce scope      → hold locks for minimal time
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

  Q10. What is the difference between CountDownLatch, CyclicBarrier, and Semaphore?


ANSWER:
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Feature        CountDownLatch  CyclicBarrier   Semaphore
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Purpose        Wait for N      All threads     Limit N concurrent
               events/threads  wait for each   accesses to resource
               to complete     other at gate
Reusable       ❌ One-time     ✅ Cyclic        ✅ Multiple times
Who waits      Main waits for  Threads wait    Threads wait for
               N workers       for each other  a permit slot
Counter        countdown()     await()         acquire()/release()
reaches 0      releases all    releases all    releases one slot
Use case       "Wait for all   "All start      "Connection pool,
               workers done"   together"       rate limiter"
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

EASY MEMORY AID:
  CountDownLatch  → starting pistol (fires once, can't reload)
  CyclicBarrier   → meeting point (everyone waits, then all proceed together)
  Semaphore       → parking lot (N spaces, acquire = enter, release = leave)

*/
