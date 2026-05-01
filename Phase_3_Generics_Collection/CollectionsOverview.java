package Phase_3_Generics_Collection;

// ─────────────────────────────────────────────────────────────
// PHASE 3 — L1: Java Collections Framework Overview
//
// In Node.js: you use arrays [] and objects {} for everything.
// In Java   : there's a rich, type-safe Collections Framework.
//
// WHY Collections over plain arrays?
//   Arrays  → fixed size, no built-in add/remove/search helpers
//   Collections → dynamic size, rich API, type-safe with Generics
//
// THE COLLECTION HIERARCHY (simplified):
//
//   Iterable
//     └── Collection
//           ├── List       → ordered, allows duplicates
//           │     ├── ArrayList    (resizable array — most used)
//           │     ├── LinkedList   (doubly linked — good for queue/stack)
//           │     └── Vector       (legacy, thread-safe — avoid)
//           │
//           ├── Set        → NO duplicates
//           │     ├── HashSet      (unordered, fastest lookup)
//           │     ├── LinkedHashSet(insertion order preserved)
//           │     └── TreeSet      (sorted order)
//           │
//           └── Queue      → FIFO (first in, first out)
//                 ├── LinkedList   (also implements Queue)
//                 ├── PriorityQueue(sorted by priority)
//                 └── ArrayDeque   (double-ended queue — best Stack)
//
//   Map  (NOT part of Collection, but part of the framework)
//         ├── HashMap      (unordered, fastest, allows null key)
//         ├── LinkedHashMap(insertion order preserved)
//         ├── TreeMap      (sorted by key)
//         └── Hashtable    (legacy, thread-safe — avoid)
//
// QUICK PICK GUIDE:
//   Need ordered list with duplicates?   → ArrayList
//   Need unique elements?                → HashSet
//   Need key → value mapping?            → HashMap
//   Need sorted unique elements?         → TreeSet
//   Need sorted keys in map?             → TreeMap
//   Need FIFO queue?                     → LinkedList / ArrayDeque
//   Need priority-based ordering?        → PriorityQueue
// ─────────────────────────────────────────────────────────────

import java.util.*;    // imports all collection classes at once

public class CollectionsOverview {
    public static void main(String[] args) {

        // ── List: ordered, indexed, allows duplicates ─────────
        List<String> names = new ArrayList<>();
        names.add("Rashid");
        names.add("Ali");
        names.add("Rashid");   // duplicate — allowed in List
        System.out.println(names);   // [Rashid, Ali, Rashid]

        // ── Set: unordered, NO duplicates ─────────────────────
        Set<String> uniqueNames = new HashSet<>();
        uniqueNames.add("Rashid");
        uniqueNames.add("Ali");
        uniqueNames.add("Rashid");   // duplicate — IGNORED silently
        System.out.println(uniqueNames);  // [Ali, Rashid] — order not guaranteed

        // ── Map: key → value pairs ─────────────────────────────
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Rashid", 95);
        scores.put("Ali",    80);
        scores.put("Sara",   88);
        System.out.println(scores.get("Rashid"));  // 95

        // ── Queue: FIFO ────────────────────────────────────────
        Queue<String> taskQueue = new LinkedList<>();
        taskQueue.offer("Task 1");   // add to end
        taskQueue.offer("Task 2");
        taskQueue.offer("Task 3");
        System.out.println(taskQueue.poll());   // Task 1 — removes from front
        System.out.println(taskQueue.peek());   // Task 2 — sees front without removing
    }
    
}
