package Phase_5_Streams_Lambdas_Functional_Programming;

 // ─────────────────────────────────────────────────────────────
// PHASE 5 — L4: Stream API — Introduction & Pipelines
//
// Stream = a pipeline for processing sequences of elements.
// Think of it like a conveyor belt — data flows through operations.
//
// STREAM PIPELINE HAS 3 PARTS:
//   1. SOURCE      → where data comes from (Collection, array, file)
//   2. INTERMEDIATE ops → transform/filter lazily (return Stream)
//   3. TERMINAL op → triggers execution, produces result/side-effect
//
// KEY PROPERTIES:
//   ✅ Lazy   → intermediate ops don't execute until terminal is called
//   ✅ Once   → a stream can only be consumed ONCE (then it's closed)
//   ✅ No storage → stream doesn't store data — it processes on the fly
//   ✅ Fluent  → chain operations (like Promise chains in Node.js)
//
// Node.js parallel:
//   list.stream()
//       .filter(x -> x > 5)    →   arr.filter(x => x > 5)
//       .map(x -> x * 2)       →       .map(x => x * 2)
//       .collect(toList())     →       // (result already an array in JS)
//
// IMPORTANT: Streams don't MODIFY the source collection.
//   The original list is always untouched.
// ─────────────────────────────────────────────────────────────

import java.util.*;
import java.util.stream.*;
// import java.util.function.*;

public class StreamIntroDemo {
    public static void main(String[] args) {

        // ── CREATING STREAMS ──────────────────────────────────

        // From a Collection (most common)
        List<String> names = Arrays.asList("Rashid", "Ali", "Sara", "Zara", "Priya");
        Stream<String> stream1 = names.stream();

        // From an array
        int[] arr = {1, 2, 3, 4, 5};
        IntStream stream2 = Arrays.stream(arr);    // IntStream for primitives

        // From literal values — Stream.of()
        Stream<Integer> stream3 = Stream.of(10, 20, 30, 40, 50);

        // From a range (IntStream) — like range() in Python
        IntStream range   = IntStream.range(1, 6);     // 1,2,3,4,5 (end exclusive)
        IntStream rangeCl = IntStream.rangeClosed(1, 5);// 1,2,3,4,5 (end inclusive)

        // Infinite stream — generate values lazily
        Stream<Double> randoms = Stream.generate(Math::random);   // infinite!
        Stream<Integer> naturals = Stream.iterate(0, n -> n + 1); // 0,1,2,3...

        // ── THE PIPELINE PATTERN ───────────────────────────────
        // source → filter → map → sorted → collect
        // Each op returns a Stream → chain them (fluent API)

        List<String> result = names.stream()          // SOURCE
                .filter(n -> n.length() > 3)          // INTERMEDIATE: keep names > 3 chars
                .map(String::toUpperCase)             // INTERMEDIATE: uppercase each
                .sorted()                             // INTERMEDIATE: alphabetical sort
                .collect(Collectors.toList());         // TERMINAL: collect to List

        System.out.println("Pipeline result: " + result);
        // [PRIYA, RASHID, SARA, ZARA]  — "Ali" removed (len=3)
        System.out.println("Original untouched: " + names); // unchanged!

        // ── LAZY EVALUATION DEMO ──────────────────────────────
        // Intermediate operations are LAZY — they don't run until terminal
        System.out.println("\n=== Lazy Evaluation ===");

        Stream<Integer> lazyStream = Stream.of(1, 2, 3, 4, 5)
                .filter(n -> {
                    System.out.println("  filter: " + n);
                    return n % 2 == 0;
                })
                .map(n -> {
                    System.out.println("  map: " + n);
                    return n * 10;
                });

        System.out.println("Stream created — nothing ran yet!");
        // ↑ Nothing printed — lazy!

        System.out.println("Now calling findFirst()...");
        Optional<Integer> first = lazyStream.findFirst(); // TERMINAL — triggers execution
        System.out.println("Result: " + first.get());
        // Only processes elements until it finds the first match — stops early!
        // filter(1): false, filter(2): true, map(2): 20 → stops. 3,4,5 never processed.

        // ── SHORT-CIRCUIT OPERATIONS ──────────────────────────
        // Some terminal ops stop as soon as answer is found (lazy + efficient)
        List<Integer> nums = Arrays.asList(1, 5, 3, 8, 2, 9, 4);

        Optional<Integer> firstEven = nums.stream()
                .filter(n -> n % 2 == 0)
                .findFirst();                 // stops at first even number found
        System.out.println("\nFirst even: " + firstEven.get()); // 8

        boolean hasLarge = nums.stream()
                .anyMatch(n -> n > 7);        // stops at first n > 7
        System.out.println("Has number > 7: " + hasLarge);  // true

        // ── STREAM CAN ONLY BE USED ONCE ──────────────────────
        Stream<String> onceStream = names.stream().filter(n -> n.length() > 3);
        long count1 = onceStream.count();   // ✅ first terminal — ok
        try {
            long count2 = onceStream.count(); // ❌ second terminal — THROWS!
        } catch (IllegalStateException e) {
            System.out.println("\nStream already consumed: " + e.getMessage());
        }
        // FIX: call .stream() again from the source, or store as Collection
    }
    
}
