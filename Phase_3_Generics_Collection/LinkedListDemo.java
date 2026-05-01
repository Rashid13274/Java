package Phase_3_Generics_Collection;

// ─────────────────────────────────────────────────────────────
// PHASE 3 — L3: LinkedList, Stack & Queue
//
// LinkedList is a doubly-linked list internally.
// Each element is a Node with: data + prev pointer + next pointer.
//
// It implements BOTH List AND Deque (double-ended queue).
// So it can work as: List, Queue, Stack, Deque.
//
// When to choose LinkedList over ArrayList?
//   ArrayList  → fast random access O(1), slow insert/delete in middle
//   LinkedList → slow random access O(n), fast insert/delete at ends O(1)
//
// In real projects: ArrayList is almost always preferred.
// LinkedList shines for: frequent add/remove at head, implementing queues.
//
// ArrayDeque is preferred over LinkedList for Stack/Queue use cases
// because it has NO null elements and better cache performance.
// ─────────────────────────────────────────────────────────────

import java.util.*;

public class LinkedListDemo {
    public static void main(String[] args) {

        // ── LINKEDLIST AS A LIST ──────────────────────────────
        LinkedList<String> tasks = new LinkedList<>();

        tasks.add("Write code");       // add to end
        tasks.add("Write tests");
        tasks.add("Code review");

        tasks.addFirst("Setup project");  // add to front — O(1), fast!
        tasks.addLast("Deploy");          // add to end

        System.out.println(tasks);
        // [Setup project, Write code, Write tests, Code review, Deploy]

        System.out.println(tasks.getFirst()); // Setup project — peek at head
        System.out.println(tasks.getLast());  // Deploy         — peek at tail

        tasks.removeFirst();   // remove from head O(1)
        tasks.removeLast();    // remove from tail O(1)
        System.out.println(tasks); // [Write code, Write tests, Code review]

        // ── LINKEDLIST AS A QUEUE (FIFO) ──────────────────────
        // Queue: First In, First Out — like a ticket counter line
        // offer() → add to rear | poll() → remove from front | peek() → see front

        Queue<String> requestQueue = new LinkedList<>();

        // Simulating incoming API requests
        requestQueue.offer("GET /users");       // enter queue
        requestQueue.offer("POST /products");
        requestQueue.offer("DELETE /orders/5");

        System.out.println("\n--- Processing Queue ---");
        while (!requestQueue.isEmpty()) {
            String req = requestQueue.poll();   // remove & process from front
            System.out.println("Processing: " + req);
        }
        // GET first, then POST, then DELETE — FIFO order

        // ── ARRAYDEQUE AS A STACK (LIFO) ──────────────────────
        // Stack: Last In, First Out — like browser back history
        // push() → add to top | pop() → remove from top | peek() → see top
        // ⚠️ Use ArrayDeque instead of Stack class (Stack is legacy/slow)

        Deque<String> browserHistory = new ArrayDeque<>();

        browserHistory.push("google.com");       // visit pages
        browserHistory.push("github.com");
        browserHistory.push("stackoverflow.com");

        System.out.println("\n--- Browser Back Navigation ---");
        System.out.println("Current: " + browserHistory.peek()); // stackoverflow.com
        browserHistory.pop();   // go back
        System.out.println("Back to: " + browserHistory.peek()); // github.com
        browserHistory.pop();   // go back again
        System.out.println("Back to: " + browserHistory.peek()); // google.com

        // ── PRIORITYQUEUE ─────────────────────────────────────
        // Not FIFO — orders elements by PRIORITY (natural order by default)
        // Internally uses a min-heap.
        // Smallest element always comes out first.

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(50);
        pq.offer(10);
        pq.offer(30);
        pq.offer(5);
        pq.offer(20);

        System.out.println("\n--- Priority Queue (Min-Heap) ---");
        while (!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");  // 5 10 20 30 50 — sorted!
        }

        // Max-heap — reverse the comparator
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());
        maxPQ.offer(50);
        maxPQ.offer(10);
        maxPQ.offer(30);

        System.out.println("\n--- Max Priority Queue ---");
        while (!maxPQ.isEmpty()) {
            System.out.print(maxPQ.poll() + " ");  // 50 30 10
        }

        // Custom priority — sort tasks by urgency
        PriorityQueue<String[]> taskPQ = new PriorityQueue<>(
                Comparator.comparingInt(t -> Integer.parseInt(t[1]))
        );
        taskPQ.offer(new String[]{"Write tests", "3"});    // urgency 3
        taskPQ.offer(new String[]{"Fix prod bug", "1"});   // urgency 1 — highest!
        taskPQ.offer(new String[]{"Update docs", "5"});    // urgency 5

        System.out.println("\n--- Task Priority ---");
        while (!taskPQ.isEmpty()) {
            String[] t = taskPQ.poll();
            System.out.println("Priority " + t[1] + ": " + t[0]);
        }
        // Fix prod bug (1) comes first!
    }
    
}
