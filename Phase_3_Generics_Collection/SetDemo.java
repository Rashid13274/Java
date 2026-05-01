package Phase_3_Generics_Collection;

 // ─────────────────────────────────────────────────────────────
// PHASE 3 — L5: Set Implementations
//
// Set = Collection with NO duplicates.
// Adding an existing element → silently ignored (no error).
//
// HashSet    → backed by HashMap, unordered, O(1) ops
// LinkedHashSet → insertion order, O(1) ops (slightly slower)
// TreeSet    → sorted order (natural or custom), O(log n) ops
//
// When to use:
//   "Remove duplicates from a list"       → HashSet
//   "Unique items in insertion order"     → LinkedHashSet
//   "Unique items, always sorted"         → TreeSet
//   "Fast membership check (contains?)"   → HashSet
//
// How HashSet works internally:
//   It IS a HashMap internally!
//   add("Rashid") → map.put("Rashid", DUMMY_VALUE)
//   contains()    → map.containsKey()
//   So same O(1) average complexity as HashMap.
// ─────────────────────────────────────────────────────────────

import java.util.*;

public class SetDemo {
    public static void main(String[] args) {

        // ── HASHSET ───────────────────────────────────────────
        HashSet<String> skills = new HashSet<>();

        skills.add("Java");
        skills.add("Spring");
        skills.add("MySQL");
        skills.add("Java");       // duplicate — silently ignored
        skills.add("Spring");     // duplicate — silently ignored

        System.out.println(skills);         // [MySQL, Java, Spring] — order NOT guaranteed
        System.out.println(skills.size());  // 3 — not 5

        System.out.println(skills.contains("Java"));   // true  — O(1) lookup
        System.out.println(skills.contains("Python")); // false

        skills.remove("MySQL");
        System.out.println(skills);

        // Iterating a Set
        for (String skill : skills) {
            System.out.println("→ " + skill);
        }

        // ── PRACTICAL USE 1: Remove duplicates from a list ────
        List<Integer> withDupes = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5, 3);
        Set<Integer> unique = new HashSet<>(withDupes);  // auto-removes dupes
        System.out.println("Unique: " + unique);  // order not guaranteed

        // ── PRACTICAL USE 2: Set operations ───────────────────
        Set<String> setA = new HashSet<>(Arrays.asList("Java", "Python", "Go"));
        Set<String> setB = new HashSet<>(Arrays.asList("Python", "Rust", "Go"));

        // Union — like OR — all elements from both
        Set<String> union = new HashSet<>(setA);
        union.addAll(setB);
        System.out.println("Union: " + union);   // [Java, Python, Go, Rust]

        // Intersection — like AND — only common elements
        Set<String> intersection = new HashSet<>(setA);
        intersection.retainAll(setB);
        System.out.println("Intersection: " + intersection);  // [Python, Go]

        // Difference — in A but NOT in B
        Set<String> difference = new HashSet<>(setA);
        difference.removeAll(setB);
        System.out.println("Difference A-B: " + difference);  // [Java]

        // ── LINKEDHASHSET — preserves insertion order ──────────
        LinkedHashSet<String> lhs = new LinkedHashSet<>();
        lhs.add("First");
        lhs.add("Second");
        lhs.add("Third");
        lhs.add("First");   // duplicate — ignored, order of FIRST insertion kept

        System.out.println(lhs);  // [First, Second, Third] — always in this order

        // ── TREESET — natural sorted order ────────────────────
        TreeSet<Integer> sorted = new TreeSet<>();
        sorted.add(50);
        sorted.add(10);
        sorted.add(30);
        sorted.add(20);
        sorted.add(40);

        System.out.println(sorted);           // [10, 20, 30, 40, 50] — always sorted!
        System.out.println(sorted.first());   // 10 — smallest
        System.out.println(sorted.last());    // 50 — largest
        System.out.println(sorted.headSet(30));   // [10, 20] — elements < 30
        System.out.println(sorted.tailSet(30));   // [30, 40, 50] — elements >= 30
        System.out.println(sorted.subSet(20, 40));// [20, 30] — 20 to 39

        System.out.println(sorted.floor(35));  // 30 — largest element ≤ 35
        System.out.println(sorted.ceiling(35));// 40 — smallest element ≥ 35

        // TreeSet with custom comparator — sort Strings by length
        TreeSet<String> byLength = new TreeSet<>(
                Comparator.comparingInt(String::length)
                          .thenComparing(Comparator.naturalOrder()) // tie-break alphabetically
        );
        byLength.add("Python");
        byLength.add("Java");
        byLength.add("Go");
        byLength.add("C");
        System.out.println(byLength);  // [C, Go, Java, Python]
    }
    
}
