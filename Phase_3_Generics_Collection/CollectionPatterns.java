package Phase_3_Generics_Collection;

// ─────────────────────────────────────────────────────────────
// PHASE 3 — L9: Real-World Patterns with Collections
//
// Patterns you'll use daily in Spring Boot backends:
//   - Grouping data (like SQL GROUP BY)
//   - Frequency counting
//   - Caching with LinkedHashMap (LRU cache)
//   - Graph adjacency list
//   - Inverting a map
// ─────────────────────────────────────────────────────────────

import java.util.*;
// import java.util.stream.Collectors;

public class CollectionPatterns {
    public static void main(String[] args) {

        // ── PATTERN 1: FREQUENCY COUNT ─────────────────────────
        // Count occurrences of each element — word count, tag frequency, etc.
        String[] words = {"java", "spring", "java", "boot", "java", "spring"};

        Map<String, Integer> freq = new HashMap<>();
        for (String word : words) {
            freq.merge(word, 1, Integer::sum);  // merge is cleaner than if-else
        }
        System.out.println("Frequency: " + freq);
        // {java=3, spring=2, boot=1}

        // Find the most frequent element
        String mostFrequent = freq.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("none");
        System.out.println("Most frequent: " + mostFrequent);  // java

        // ── PATTERN 2: GROUPING ────────────────────────────────
        // Group employees by department — like SQL GROUP BY department
        List<String[]> employees = Arrays.asList(
                new String[]{"Rashid", "Backend"},
                new String[]{"Ali",    "Frontend"},
                new String[]{"Sara",   "Backend"},
                new String[]{"Priya",  "DevOps"},
                new String[]{"Zara",   "Frontend"}
        );

        Map<String, List<String>> byDept = new HashMap<>();
        for (String[] emp : employees) {
            // computeIfAbsent: create new ArrayList only if key missing
            byDept.computeIfAbsent(emp[1], k -> new ArrayList<>())
                  .add(emp[0]);
        }
        System.out.println("\nGrouped by Dept: " + byDept);
        // {Backend=[Rashid, Sara], Frontend=[Ali, Zara], DevOps=[Priya]}

        // ── PATTERN 3: INVERT A MAP ────────────────────────────
        // Swap keys and values — useful for reverse lookups
        Map<String, String> codeToName = new HashMap<>();
        codeToName.put("IN", "India");
        codeToName.put("US", "United States");
        codeToName.put("GB", "Great Britain");

        Map<String, String> nameToCode = new HashMap<>();
        codeToName.forEach((code, name) -> nameToCode.put(name, code));
        System.out.println("\nInverted: " + nameToCode);

        // ── PATTERN 4: LRU CACHE with LinkedHashMap ────────────
        // LRU = Least Recently Used — evict the oldest accessed item
        // LinkedHashMap with accessOrder=true tracks access order
        final int CAPACITY = 3;

        LinkedHashMap<Integer, String> lruCache = new LinkedHashMap<>(
                CAPACITY, 0.75f, true   // true = access-order mode
        ) {
            // Override to auto-evict oldest entry when over capacity
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
                return size() > CAPACITY;
            }
        };

        lruCache.put(1, "Page A");
        lruCache.put(2, "Page B");
        lruCache.put(3, "Page C");
        System.out.println("\nCache: " + lruCache);   // {1=Page A, 2=Page B, 3=Page C}

        lruCache.get(1);   // access key 1 → moves to most recently used
        lruCache.put(4, "Page D");   // capacity exceeded → evicts LRU (key 2)
        System.out.println("After eviction: " + lruCache);  // {3, 1, 4} — 2 gone

        // ── PATTERN 5: GRAPH (Adjacency List) ─────────────────
        // Represent a graph as Map<Node, List<Neighbors>>
        // Used in social networks, routing, recommendation engines
        Map<String, List<String>> graph = new HashMap<>();
        graph.computeIfAbsent("Rashid", k -> new ArrayList<>()).add("Ali");
        graph.computeIfAbsent("Rashid", k -> new ArrayList<>()).add("Sara");
        graph.computeIfAbsent("Ali", k -> new ArrayList<>()).add("Rashid");
        graph.computeIfAbsent("Ali", k -> new ArrayList<>()).add("Priya");
        graph.computeIfAbsent("Sara", k -> new ArrayList<>()).add("Rashid");

        System.out.println("\nGraph (connections):");
        graph.forEach((person, friends) ->
                System.out.println(person + " → " + friends));

        // BFS using the graph + a Queue
        System.out.println("\nBFS from Rashid:");
        Set<String> visited = new LinkedHashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer("Rashid");
        visited.add("Rashid");

        while (!queue.isEmpty()) {
            String current = queue.poll();
            System.out.print(current + " → ");
            List<String> neighbors = graph.getOrDefault(current, Collections.emptyList());
            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }

        // ── PATTERN 6: DEDUPLICATION preserving order ──────────
        // Remove duplicates but keep insertion order
        List<String> raw = Arrays.asList("Java","Spring","Java","MySQL","Spring","Docker");
        List<String> deduped = new ArrayList<>(new LinkedHashSet<>(raw));
        System.out.println("\nDeduped ordered: " + deduped);
        // [Java, Spring, MySQL, Docker] — order preserved, dupes gone
    }
}