package Phase_3_Generics_Collection;

// ─────────────────────────────────────────────────────────────
// PHASE 3 — L8: Iterator & ListIterator
//
// Iterator = design pattern for traversing a collection
// without exposing its internal structure.
//
// WHY Iterator over for-each?
//   for-each is syntactic sugar for Iterator internally.
//   BUT you can't safely REMOVE elements during for-each.
//   Iterator.remove() is the ONLY safe way to remove while iterating.
//
// Fail-fast vs Fail-safe:
//   Fail-fast   → throws ConcurrentModificationException if list
//                 is modified during iteration (ArrayList, HashMap)
//   Fail-safe   → works on a copy, no exception (CopyOnWriteArrayList)
// ─────────────────────────────────────────────────────────────

import java.util.*;

public class IteratorDemo {
    public static void main(String[] args) {

        List<String> names = new ArrayList<>(
                Arrays.asList("Rashid", "Ali", "Bot1", "Sara", "Bot2", "Zara")
        );

        // ── ITERATOR — forward only, can remove ───────────────
        Iterator<String> it = names.iterator();

        while (it.hasNext()) {           // hasNext() → is there a next element?
            String name = it.next();     // next() → move forward and return element

            if (name.startsWith("Bot")) {
                it.remove();             // ✅ SAFE removal during iteration
                // ❌ names.remove(name) here would throw ConcurrentModificationException
            }
        }
        System.out.println(names);  // [Rashid, Ali, Sara, Zara] — bots removed

        // ── LISTITERATOR — bidirectional, can add/set ─────────
        ListIterator<String> lit = names.listIterator();

        System.out.println("=== Forward pass ===");
        while (lit.hasNext()) {
            int idx  = lit.nextIndex();    // index of next element
            String s = lit.next();
            System.out.println(idx + ": " + s);

            if (s.equals("Ali")) {
                lit.set("ALI");            // replace current element
                lit.add("NewUser");        // insert AFTER current element
            }
        }
        System.out.println(names);  // [Rashid, ALI, NewUser, Sara, Zara]

        System.out.println("=== Backward pass ===");
        while (lit.hasPrevious()) {
            System.out.print(lit.previous() + " ");   // iterate in reverse
        }
        System.out.println();

        // ── ConcurrentModificationException demo ──────────────
        List<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        try {
            for (Integer n : nums) {         // enhanced for-each uses Iterator internally
                if (n == 3) {
                    nums.remove(n);          // ❌ modifying list during iteration
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Caught: " + e.getClass().getSimpleName());
            // ✅ Fix: use Iterator.remove() or removeIf()
        }

        // ✅ Correct way 1: Iterator.remove()
        Iterator<Integer> safeIt = nums.iterator();
        while (safeIt.hasNext()) {
            if (safeIt.next() % 2 == 0) safeIt.remove();
        }
        System.out.println("After safe remove: " + nums);

        // ✅ Correct way 2: removeIf (Java 8+ — most concise)
        List<Integer> nums2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        nums2.removeIf(n -> n % 2 == 0);   // removes all even numbers
        System.out.println("After removeIf: " + nums2);  // [1, 3, 5]
    }
}