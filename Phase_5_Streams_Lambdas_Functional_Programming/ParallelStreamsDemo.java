package Phase_5_Streams_Lambdas_Functional_Programming;

 // ─────────────────────────────────────────────────────────────
// PHASE 5 — L8: Parallel Streams & Best Practices
//
// Parallel streams split data into chunks and process them
// simultaneously using the ForkJoinPool (all CPU cores).
//
// Just add .parallelStream() instead of .stream()
// OR add .parallel() in the middle of a stream pipeline.
//
// WHEN TO USE PARALLEL STREAMS:
//   ✅ Large datasets (tens of thousands+)
//   ✅ CPU-intensive operations (complex transformations)
//   ✅ Order doesn't matter for the final result
//   ❌ Small datasets — overhead of threading > benefit
//   ❌ Operations with side-effects (writing to shared state)
//   ❌ I/O-bound tasks (use CompletableFuture instead)
//   ❌ When you need encounter order preserved
// ─────────────────────────────────────────────────────────────

import java.util.*;
import java.util.stream.*;
import java.util.concurrent.atomic.*;
import java.util.function.Predicate;

public class ParallelStreamsDemo {
    public static void main(String[] args) {

        // ── SEQUENTIAL vs PARALLEL ────────────────────────────
        List<Integer> bigList = IntStream.rangeClosed(1, 1_000_000)
                .boxed()
                .collect(Collectors.toList());

        // Sequential
        long start = System.currentTimeMillis();
        long seqSum = bigList.stream()
                .mapToLong(Integer::longValue)
                .sum();
        long seqTime = System.currentTimeMillis() - start;

        // Parallel — just add .parallelStream()
        start = System.currentTimeMillis();
        long parSum = bigList.parallelStream()
                .mapToLong(Integer::longValue)
                .sum();
        long parTime = System.currentTimeMillis() - start;

        System.out.println("Sequential: " + seqSum + " in " + seqTime + "ms");
        System.out.println("Parallel:   " + parSum + " in " + parTime + "ms");
        // On multi-core machine, parallel is noticeably faster for large datasets

        // ── ORDER IN PARALLEL STREAMS ─────────────────────────
        // Parallel streams may process elements OUT OF ORDER
        System.out.println("\nSequential forEach order (predictable):");
        List.of(1, 2, 3, 4, 5).stream()
            .forEach(n -> System.out.print(n + " "));  // 1 2 3 4 5

        System.out.println("\nParallel forEach order (unpredictable):");
        List.of(1, 2, 3, 4, 5).parallelStream()
            .forEach(n -> System.out.print(n + " "));  // may be: 3 1 5 2 4

        // forEachOrdered() preserves order even in parallel (slower)
        System.out.println("\nParallel forEachOrdered (order preserved):");
        List.of(1, 2, 3, 4, 5).parallelStream()
            .forEachOrdered(n -> System.out.print(n + " ")); // always 1 2 3 4 5

        // ── THREAD SAFETY ISSUE — DON'T DO THIS ───────────────
        List<Integer> unsafeResult = new ArrayList<>();  // NOT thread-safe!

        List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).parallelStream()
            .filter(n -> n % 2 == 0)
            .forEach(n -> unsafeResult.add(n));  // ❌ race condition! list may be corrupt
        System.out.println("\nUnsafe result size: " + unsafeResult.size()); // may be wrong!

        // ✅ CORRECT: use collect() — thread-safe accumulation
        List<Integer> safeResult = List.of(1,2,3,4,5,6,7,8,9,10).parallelStream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());  // Collectors are thread-safe
        System.out.println("Safe result: " + safeResult);

        // ✅ Or use AtomicInteger for counting
        AtomicInteger atomicCount = new AtomicInteger(0);
        List.of(1,2,3,4,5,6,7,8,9,10).parallelStream()
            .filter(n -> n % 2 == 0)
            .forEach(n -> atomicCount.incrementAndGet());  // thread-safe increment
        System.out.println("Count: " + atomicCount.get());  // always 5

        // ── BEST PRACTICES SUMMARY ────────────────────────────
        System.out.println("\n=== Stream Best Practices ===");

        List<String> data = Arrays.asList("  Java  ", null, "  Spring  ",
                "", "  Boot  ", null, "  REST  ");

        // ✅ BP1: Handle nulls before streaming
        List<String> cleaned = data.stream()
                .filter(Objects::nonNull)         // remove nulls
                .map(String::trim)                // trim whitespace
                .filter(s -> !s.isBlank())        // remove blank strings
                .map(String::toLowerCase)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Cleaned: " + cleaned);

        // ✅ BP2: Use primitive streams to avoid boxing overhead
        // BAD:   stream().map(Integer::parseInt).mapToInt(n->n).sum()
        // GOOD:  stream().mapToInt(Integer::parseInt).sum()
        int sum = Arrays.asList("1","2","3","4","5").stream()
                .mapToInt(Integer::parseInt)   // directly to IntStream — no boxing!
                .sum();
        System.out.println("Sum: " + sum);

        // ✅ BP3: Don't modify source in stream operation
        // BAD — modifying the list during stream:
        // list.stream().forEach(list::remove);  ❌

        // ✅ BP4: Prefer method references over lambdas when possible
        // Verbose: .map(s -> s.toUpperCase())
        // Clean:   .map(String::toUpperCase)

        // ✅ BP5: Reuse logic with intermediate variables
        // When using the same stream operations in multiple places,
        // extract Predicate/Function to a variable for reuse
        Predicate<String> isValid = s -> s != null && !s.isBlank() && s.length() > 3;
        long validCount  = data.stream().filter(isValid).count();
        List<String> valid = data.stream().filter(isValid).collect(Collectors.toList());
    }
    
}
