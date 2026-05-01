package Phase_6__Concurrency__Multithreading;

 // ─────────────────────────────────────────────────────────────
// PHASE 6 — L7: Deadlock, Livelock & Prevention
//
// DEADLOCK: Two or more threads are waiting for each other's locks
// FOREVER — none can proceed.
//
// DEADLOCK CONDITIONS (ALL 4 must hold simultaneously):
//   1. Mutual Exclusion  — resource held by one thread at a time
//   2. Hold and Wait     — thread holds one lock and waits for another
//   3. No Preemption     — locks can't be forcibly taken away
//   4. Circular Wait     — Thread A waits for B, B waits for A (cycle)
//
// DEADLOCK PREVENTION STRATEGIES:
//   1. Lock ordering        — always acquire locks in same order
//   2. tryLock() with timeout — don't wait forever
//   3. Lock-free algorithms — use Atomic classes
//   4. Reduce lock scope    — hold locks for shortest time possible
//
// LIVELOCK: Threads ARE running but making NO progress
//   (like two people dodging each other in a hallway)
// ─────────────────────────────────────────────────────────────

import java.util.concurrent.locks.*;
import java.util.concurrent.*;

public class DeadlockDemo {

    // ── DEADLOCK EXAMPLE ─────────────────────────────────────
    static final Object LOCK_A = new Object();
    static final Object LOCK_B = new Object();

    // Thread 1: acquires LOCK_A then wants LOCK_B
    static void thread1Task() {
        synchronized (LOCK_A) {
            System.out.println("Thread1: holding LOCK_A...");
            try { Thread.sleep(100); } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Thread1: waiting for LOCK_B...");
            synchronized (LOCK_B) {   // ← BLOCKS: Thread2 holds LOCK_B
                System.out.println("Thread1: holding both locks!");
            }
        }
    }

    // Thread 2: acquires LOCK_B then wants LOCK_A — OPPOSITE ORDER!
    static void thread2Task() {
        synchronized (LOCK_B) {
            System.out.println("Thread2: holding LOCK_B...");
            try { Thread.sleep(100); } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Thread2: waiting for LOCK_A...");
            synchronized (LOCK_A) {   // ← BLOCKS: Thread1 holds LOCK_A
                System.out.println("Thread2: holding both locks!");
            }
        }
    }

    // ── FIX 1: LOCK ORDERING ─────────────────────────────────
    // Always acquire locks in the SAME ORDER — eliminates circular wait
    static void safeThread1() {
        synchronized (LOCK_A) {         // always A first
            System.out.println("SafeT1: holding LOCK_A");
            synchronized (LOCK_B) {     // then B
                System.out.println("SafeT1: holding both ✅");
            }
        }
    }

    static void safeThread2() {
        synchronized (LOCK_A) {         // same order: A first
            System.out.println("SafeT2: holding LOCK_A");
            synchronized (LOCK_B) {     // then B
                System.out.println("SafeT2: holding both ✅");
            }
        }
    }

    // ── FIX 2: tryLock() with timeout using ReentrantLock ─────
    // ReentrantLock is more powerful than synchronized:
    //   - tryLock() — try to acquire, don't block if unavailable
    //   - tryLock(time, unit) — try with timeout
    //   - lockInterruptibly() — can be interrupted while waiting
    //   - Conditions (like wait/notify but more powerful)
    //   - Fair mode — threads get lock in order they requested it

    static ReentrantLock lockA = new ReentrantLock();
    static ReentrantLock lockB = new ReentrantLock();

    static void tryLockThread(String name) throws InterruptedException {
        while (true) {
            boolean gotA = false, gotB = false;
            try {
                gotA = lockA.tryLock(100, TimeUnit.MILLISECONDS);
                gotB = lockB.tryLock(100, TimeUnit.MILLISECONDS);

                if (gotA && gotB) {
                    // Got both locks — do the work
                    System.out.println(name + ": acquired both locks ✅");
                    Thread.sleep(100);
                    return;  // done
                } else {
                    // Couldn't get both — RELEASE what we have and retry
                    System.out.println(name + ": couldn't get both, retrying...");
                    Thread.sleep((long)(Math.random() * 50));  // random backoff
                }
            } finally {
                // ALWAYS release in finally — even if exception occurs
                if (gotB) lockB.unlock();
                if (gotA) lockA.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        // ── DEMONSTRATE DEADLOCK (will hang — use timeout) ────
        System.out.println("=== Deadlock Demo (will timeout after 2s) ===");
        Thread t1 = new Thread(DeadlockDemo::thread1Task, "Thread-1");
        Thread t2 = new Thread(DeadlockDemo::thread2Task, "Thread-2");
        t1.start();
        t2.start();

        t1.join(2000);   // wait max 2 seconds
        t2.join(2000);   // wait max 2 seconds

        if (t1.isAlive() || t2.isAlive()) {
            System.out.println("⚠️  DEADLOCK DETECTED — threads still running!");
            t1.interrupt();   // try to interrupt — may not work for synchronized
            t2.interrupt();
        }

        // ── SAFE WITH LOCK ORDERING ────────────────────────────
        System.out.println("\n=== Safe with Lock Ordering ===");
        Thread s1 = new Thread(DeadlockDemo::safeThread1, "Safe-1");
        Thread s2 = new Thread(DeadlockDemo::safeThread2, "Safe-2");
        s1.start();
        s2.start();
        s1.join();
        s2.join();
        System.out.println("Both safe threads completed ✅");

        // ── TRYLOCK SOLUTION ───────────────────────────────────
        System.out.println("\n=== tryLock with Timeout ===");
        Thread tl1 = new Thread(() -> {
            try { tryLockThread("TL-Thread-1"); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        });
        Thread tl2 = new Thread(() -> {
            try { tryLockThread("TL-Thread-2"); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        });
        tl1.start();
        tl2.start();
        tl1.join();
        tl2.join();
        System.out.println("tryLock threads completed ✅");

        // ── ReentrantLock FEATURES ─────────────────────────────
        System.out.println("\n=== ReentrantLock Features ===");
        ReentrantLock lock = new ReentrantLock();

        // Basic usage — must unlock in finally!
        lock.lock();
        try {
            System.out.println("Holding lock");
            System.out.println("Is locked: " + lock.isLocked());          // true
            System.out.println("Hold count: " + lock.getHoldCount());    // 1
            System.out.println("Is fair: " + lock.isFair());             // false (default)
        } finally {
            lock.unlock();  // ALWAYS in finally!
        }

        // Reentrant — same thread can acquire multiple times
        lock.lock();
        lock.lock();   // same thread — hold count = 2
        System.out.println("Hold count: " + lock.getHoldCount());  // 2
        lock.unlock();  // count = 1
        lock.unlock();  // count = 0 — released

        // Condition — like wait/notify but per-condition (more precise)
        ReentrantLock condLock = new ReentrantLock();
        Condition notFull  = condLock.newCondition();  // separate condition for "not full"
        Condition notEmpty = condLock.newCondition();  // separate condition for "not empty"
        // Used in BlockingQueue implementations — more precise than notifyAll()
        // Can signal ONLY the "notEmpty" waiters when item added
        // instead of waking ALL waiters (which includes "notFull" waiters too)
    }
    
}
