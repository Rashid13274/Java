package Phase_3_Generics_Collection;

// ─────────────────────────────────────────────────────────────
// PHASE 3 — L4: HashMap
//
// HashMap stores key → value pairs.
// Like a plain JS object {} or Map in JS.
//
// Internals (important for interviews!):
//   - Uses an array of "buckets" internally
//   - key.hashCode() determines which bucket
//   - Multiple keys can land in same bucket (collision)
//   - Collision handled by LinkedList/Tree in the bucket
//   - Default capacity: 16 | Load factor: 0.75
//   - Resizes (doubles) when 75% full → rehashing
//
// Key rules:
//   - Keys are UNIQUE — putting same key overwrites value
//   - Allows ONE null key and multiple null values
//   - NOT thread-safe — use ConcurrentHashMap for concurrency
//   - Order NOT guaranteed — use LinkedHashMap for insertion order
//
// Time Complexity (average case):
//   put()         → O(1)
//   get()         → O(1)
//   containsKey() → O(1)
//   remove()      → O(1)
//   Worst case O(n) if many collisions (rare with good hashCode)
// ─────────────────────────────────────────────────────────────

import java.util.*;

public class HashMapDemo {
    public static void main(String[] args) {

        // ── CREATING ──────────────────────────────────────────
        HashMap<String, Integer> scores = new HashMap<>();
        HashMap<Integer, String> users  = new HashMap<>(Map.of(
                1, "Rashid", 2, "Ali", 3, "Sara"   // initial entries
        ));

        // ── PUT & GET ─────────────────────────────────────────
        scores.put("Rashid", 95);   // add key-value — like obj.key = val in JS
        scores.put("Ali",    80);
        scores.put("Sara",   88);
        scores.put("Rashid", 99);   // ⚠️ OVERWRITES existing key — no error!

        System.out.println(scores.get("Rashid"));       // 99
        System.out.println(scores.get("Unknown"));      // null — key not found

        // getOrDefault — safe get with fallback (like obj?.key ?? default in JS)
        System.out.println(scores.getOrDefault("Bob", 0));  // 0 (not null)

        // ── CHECKING ──────────────────────────────────────────
        System.out.println(scores.containsKey("Ali"));    // true
        System.out.println(scores.containsValue(88));     // true
        System.out.println(scores.size());                // 3
        System.out.println(scores.isEmpty());             // false

        // ── REMOVE ────────────────────────────────────────────
        scores.remove("Sara");                     // remove by key
        scores.remove("Ali", 80);                  // remove only if key=Ali AND value=80

        // ── ITERATING — 3 main ways ───────────────────────────

        // 1. entrySet() — iterate key + value together (most common)
        // Like Object.entries() in JS
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }

        // 2. keySet() — iterate only keys
        // Like Object.keys() in JS
        for (String key : scores.keySet()) {
            System.out.println("Key: " + key);
        }

        // 3. values() — iterate only values
        // Like Object.values() in JS
        for (int val : scores.values()) {
            System.out.println("Value: " + val);
        }

        // 4. forEach with lambda (Java 8+ — cleanest)
        scores.forEach((key, val) -> System.out.println(key + " scored " + val));

        // ── USEFUL METHODS ────────────────────────────────────

        // putIfAbsent — only adds if key doesn't exist yet
        scores.putIfAbsent("Rashid", 0);   // Rashid exists — NOT updated
        scores.putIfAbsent("Zara",   75);  // Zara doesn't exist — added

        // computeIfAbsent — create value lazily when key missing
        // Very useful for grouping! Explained below.
        HashMap<String, List<String>> groups = new HashMap<>();
        groups.computeIfAbsent("Backend", k -> new ArrayList<>()).add("Rashid");
        groups.computeIfAbsent("Backend", k -> new ArrayList<>()).add("Ali");
        groups.computeIfAbsent("Frontend", k -> new ArrayList<>()).add("Sara");
        System.out.println(groups);
        // {Backend=[Rashid, Ali], Frontend=[Sara]}

        // merge — combine existing value with new value
        HashMap<String, Integer> wordCount = new HashMap<>();
        String[] words = {"java", "is", "great", "java", "is", "fast"};
        for (String word : words) {
            // If key exists → add 1 to existing count
            // If key doesn't exist → put 1
            wordCount.merge(word, 1, Integer::sum);
        }
        System.out.println(wordCount);  // {java=2, is=2, great=1, fast=1}

        // replace — update only if key exists
        scores.replace("Rashid", 100);
        scores.replace("Unknown", 50);  // does nothing — key doesn't exist

        // ── LINKEDHASHMAP — maintains insertion order ──────────
        // Use when you need a map but also want predictable iteration order
        LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
        lhm.put("first",  "one");
        lhm.put("second", "two");
        lhm.put("third",  "three");
        System.out.println(lhm);  // {first=one, second=two, third=three} — ordered!

        // ── TREEMAP — sorted by key ────────────────────────────
        // Internally uses Red-Black Tree. All operations O(log n).
        TreeMap<String, Integer> tm = new TreeMap<>();
        tm.put("Banana", 3);
        tm.put("Apple",  1);
        tm.put("Cherry", 2);
        System.out.println(tm);           // {Apple=1, Banana=3, Cherry=2} — alphabetical!
        System.out.println(tm.firstKey()); // Apple
        System.out.println(tm.lastKey());  // Cherry
        System.out.println(tm.headMap("Cherry"));   // keys < Cherry: {Apple, Banana}
        System.out.println(tm.tailMap("Banana"));   // keys >= Banana: {Banana, Cherry}
    }
}