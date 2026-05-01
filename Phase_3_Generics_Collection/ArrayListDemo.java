package Phase_3_Generics_Collection;

 // ─────────────────────────────────────────────────────────────
// PHASE 3 — L2: ArrayList
//
// ArrayList is the most used collection in Java.
// It's backed by a plain array internally — when it fills up,
// Java creates a new array (1.5x bigger) and copies elements.
// Default initial capacity = 10.
//
// Node.js parallel: it's basically a typed JS array with a rich API.
// const arr = []  →  ArrayList<String> list = new ArrayList<>()
//
// Time Complexity:
//   get(index)  → O(1) — direct index access
//   add(end)    → O(1) amortized (occasionally O(n) for resize)
//   add(middle) → O(n) — elements shift
//   remove(i)   → O(n) — elements shift
//   contains()  → O(n) — linear scan
// ─────────────────────────────────────────────────────────────

import java.util.*;
// import java.util.stream.Collectors;

public class ArrayListDemo {
    public static void main(String[] args) {

        // ── CREATING ──────────────────────────────────────────
        ArrayList<String> skills = new ArrayList<>();           // empty list
        ArrayList<Integer> nums  = new ArrayList<>(List.of(5, 2, 8, 1, 9)); // with initial data
        ArrayList<String> copy   = new ArrayList<>(skills);    // copy constructor

        // Pre-size if you know approximate size (avoids internal resizes)
        ArrayList<String> bigList = new ArrayList<>(1000);

        // ── ADDING ELEMENTS ───────────────────────────────────
        skills.add("Java");           // add to end — like arr.push() in JS
        skills.add("Spring Boot");
        skills.add("MySQL");
        skills.add(1, "Kafka");       // add at specific index — shifts others right
        System.out.println(skills);   // [Java, Kafka, Spring Boot, MySQL]

        // Add multiple at once
        skills.addAll(List.of("Docker", "Git"));  // like JS spread [...arr]
        skills.addAll(2, List.of("Redis"));        // insert collection at index

        // ── ACCESSING ─────────────────────────────────────────
        System.out.println(skills.get(0));         // Java — get by index
        System.out.println(skills.size());         // total count — like .length in JS
        System.out.println(skills.isEmpty());      // false
        System.out.println(skills.contains("Git"));// true — like .includes() in JS
        System.out.println(skills.indexOf("Java")); // 0 — first occurrence
        System.out.println(skills.lastIndexOf("Java")); // last occurrence

        // ── UPDATING ──────────────────────────────────────────
        skills.set(0, "Core Java");   // replace element at index 0 — like arr[0] = x in JS
        System.out.println(skills.get(0));  // Core Java

        // ── REMOVING ──────────────────────────────────────────
        skills.remove("Kafka");       // remove by VALUE (for String) — like .filter() in JS
        skills.remove(0);             // remove by INDEX — shifts elements left
                                      // ⚠️ remove(0) vs remove(Integer.valueOf(0)) — careful!

        // Remove all elements that match a condition (Java 8+)
        skills.removeIf(skill -> skill.startsWith("G"));  // removes "Git"

        // ── ITERATING — 4 ways ────────────────────────────────

        // 1. for-each (simplest — like for...of in JS)
        for (String skill : skills) {
            System.out.print(skill + " | ");
        }
        System.out.println();

        // 2. index-based for (when you need the index)
        for (int i = 0; i < skills.size(); i++) {
            System.out.printf("[%d] %s%n", i, skills.get(i));
        }

        // 3. Iterator (safe removal during iteration)
        Iterator<String> it = skills.iterator();
        while (it.hasNext()) {
            String s = it.next();
            if (s.contains("Boot")) {
                it.remove();   // ✅ safe removal — not skills.remove() inside loop!
            }
        }

        // 4. forEach with lambda (Java 8+ — like .forEach() in JS)
        skills.forEach(skill -> System.out.println("→ " + skill));

        // ── SORTING ───────────────────────────────────────────
        ArrayList<Integer> numbers = new ArrayList<>(List.of(5, 2, 8, 1, 9, 3));

        Collections.sort(numbers);                    // ascending — modifies list
        System.out.println(numbers);                  // [1, 2, 3, 5, 8, 9]

        Collections.sort(numbers, Collections.reverseOrder()); // descending
        System.out.println(numbers);                  // [9, 8, 5, 3, 2, 1]

        // Sort by custom comparator — sort strings by length
        ArrayList<String> words = new ArrayList<>(List.of("Java", "Go", "Python", "C"));
        words.sort(Comparator.comparingInt(String::length));  // ascending by length
        System.out.println(words);  // [C, Go, Java, Python]

        words.sort(Comparator.comparingInt(String::length).reversed());  // descending
        System.out.println(words);  // [Python, Java, Go, C]

        // ── SEARCHING ─────────────────────────────────────────
        Collections.sort(numbers);   // must sort first for binary search!
        int idx = Collections.binarySearch(numbers, 5);  // returns index
        System.out.println("Index of 5: " + idx);

        System.out.println("Max: " + Collections.max(numbers));  // 9
        System.out.println("Min: " + Collections.min(numbers));  // 1

        // ── USEFUL CONVERSIONS ────────────────────────────────
        // ArrayList → Array
        String[] arr = skills.toArray(new String[0]);

        // Array → ArrayList
        String[] techArr  = {"Node.js", "Express", "NestJS"};
        List<String> techList = new ArrayList<>(Arrays.asList(techArr));

        // ArrayList → String (join) — like .join() in JS
        String joined = String.join(", ", skills);
        System.out.println(joined);

        // ── SUBLIST ───────────────────────────────────────────
        // Like .slice(1, 4) in JS — returns a VIEW (not a copy!)
        List<Integer> slice = numbers.subList(1, 4);
        System.out.println(slice);   // [2, 3, 5]

        // ── SHUFFLE & FILL ────────────────────────────────────
        Collections.shuffle(numbers);          // random order
        Collections.fill(numbers, 0);          // set all to 0
        Collections.frequency(words, "Java");  // count occurrences
    }
    
}
