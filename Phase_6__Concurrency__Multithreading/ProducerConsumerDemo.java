package Phase_6__Concurrency__Multithreading;

 // ─────────────────────────────────────────────────────────────
// PHASE 6 — L3: wait(), notify() & Inter-Thread Communication
//
// Sometimes threads need to COORDINATE — one thread produces data,
// another consumes it. Without coordination: busy waiting (CPU waste).
//
// wait()       → releases lock + pauses thread until notify() called
// notify()     → wakes up ONE waiting thread (randomly)
// notifyAll()  → wakes up ALL waiting threads
//
// RULES:
//   - Must be called INSIDE a synchronized block/method
//   - Must hold the lock of the object you call wait/notify on
//   - Always call wait() in a WHILE loop (not if) — spurious wakeups!
//
// PRODUCER-CONSUMER PATTERN:
//   Producer → generates data → puts into shared buffer
//   Consumer → takes data from buffer → processes it
//   Buffer full?  → Producer waits
//   Buffer empty? → Consumer waits
//
// This pattern is the foundation of:
//   - Message queues (Kafka, RabbitMQ concepts)
//   - Thread pools
//   - Async task processing in Spring Boot
// ─────────────────────────────────────────────────────────────

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerDemo {

    // ── SHARED BUFFER (thread-safe) ────────────────────────────
    static class SharedBuffer {

        private final Queue<Integer> buffer = new LinkedList<>();
        private final int            capacity;

        public SharedBuffer(int capacity) {
            this.capacity = capacity;
        }

        // PRODUCER calls this — puts item into buffer
        // synchronized on 'this' — only one thread can produce or consume at a time
        public synchronized void produce(int item) throws InterruptedException {
            // ⚠️ WHILE loop — not if! Guards against spurious wakeups.
            // Spurious wakeup = thread wakes up even though nobody called notify()
            while (buffer.size() == capacity) {
                System.out.println("[PRODUCER] Buffer full (" + capacity
                                   + "). Waiting...");
                wait();   // releases lock + waits — Consumer will notify when space opens
            }

            buffer.add(item);
            System.out.println("[PRODUCER] Produced: " + item
                               + " | Buffer: " + buffer);

            // Wake up Consumer if it was waiting for data
            notifyAll();   // use notifyAll() when multiple threads may be waiting
        }

        // CONSUMER calls this — gets item from buffer
        public synchronized int consume() throws InterruptedException {
            while (buffer.isEmpty()) {
                System.out.println("[CONSUMER] Buffer empty. Waiting...");
                wait();   // releases lock + waits — Producer will notify when item added
            }

            int item = buffer.poll();
            System.out.println("[CONSUMER] Consumed: " + item
                               + " | Buffer: " + buffer);

            // Wake up Producer if it was waiting for space
            notifyAll();
            return item;
        }

        public synchronized int size() { return buffer.size(); }
    }

    // ── PRODUCER THREAD ───────────────────────────────────────
    static class Producer implements Runnable {

        private final SharedBuffer buffer;
        private final int          itemCount;

        public Producer(SharedBuffer buffer, int itemCount) {
            this.buffer    = buffer;
            this.itemCount = itemCount;
        }

        @Override
        public void run() {
            try {
                for (int i = 1; i <= itemCount; i++) {
                    buffer.produce(i);
                    Thread.sleep(200);   // simulate production time
                }
                System.out.println("[PRODUCER] All items produced.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // ── CONSUMER THREAD ───────────────────────────────────────
    static class Consumer implements Runnable {

        private final SharedBuffer buffer;
        private final int          itemCount;

        public Consumer(SharedBuffer buffer, int itemCount) {
            this.buffer    = buffer;
            this.itemCount = itemCount;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < itemCount; i++) {
                    int item = buffer.consume();
                    Thread.sleep(500);   // consumer is slower than producer
                }
                System.out.println("[CONSUMER] All items consumed.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        SharedBuffer buffer = new SharedBuffer(3);  // max 3 items at a time

        Thread producer = new Thread(new Producer(buffer, 8), "Producer");
        Thread consumer = new Thread(new Consumer(buffer, 8), "Consumer");

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("\n=== Producer-Consumer complete ===");

        // ── MULTIPLE PRODUCERS AND CONSUMERS ──────────────────
        System.out.println("\n=== Multiple Producers & Consumers ===");
        SharedBuffer sharedBuffer = new SharedBuffer(5);

        Thread p1 = new Thread(new Producer(sharedBuffer, 4), "Producer-1");
        Thread p2 = new Thread(new Producer(sharedBuffer, 4), "Producer-2");
        Thread c1 = new Thread(new Consumer(sharedBuffer, 4), "Consumer-1");
        Thread c2 = new Thread(new Consumer(sharedBuffer, 4), "Consumer-2");

        p1.start(); p2.start();
        c1.start(); c2.start();

        p1.join(); p2.join();
        c1.join(); c2.join();

        System.out.println("All producers and consumers finished.");
    }
    
}
