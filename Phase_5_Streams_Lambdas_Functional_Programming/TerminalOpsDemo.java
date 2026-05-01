package Phase_5_Streams_Lambdas_Functional_Programming;

 // ─────────────────────────────────────────────────────────────
// PHASE 5 — L6: Terminal Operations & Collectors
//
// Terminal operations TRIGGER the pipeline and produce a result.
// After a terminal op, the stream is CLOSED — cannot reuse.
//
// TERMINAL OPERATIONS:
//   collect()         → accumulate into collection/map/string
//   forEach()         → perform action on each (void)
//   count()           → count elements (long)
//   findFirst()       → first element (Optional<T>)
//   findAny()         → any element, better for parallel (Optional<T>)
//   min(Comparator)   → minimum element (Optional<T>)
//   max(Comparator)   → maximum element (Optional<T>)
//   anyMatch()        → true if ANY element matches (boolean)
//   allMatch()        → true if ALL elements match (boolean)
//   noneMatch()       → true if NO element matches (boolean)
//   reduce()          → combine elements into one value
//   toArray()         → convert to array
//   sum/avg/min/max   → on IntStream/DoubleStream/LongStream
// ─────────────────────────────────────────────────────────────

import java.util.*;
import java.util.stream.*;
// import java.util.function.*;

public class TerminalOpsDemo {

    record Product(String name, String category, double price, int stock) {}

    public static void main(String[] args) {

        List<Product> products = Arrays.asList(
                new Product("Java Book",       "Books",       499,  50),
                new Product("Spring Course",   "Courses",     1999, 100),
                new Product("Python Book",     "Books",       399,  30),
                new Product("Keyboard",        "Hardware",    2500, 15),
                new Product("MySQL Guide",     "Books",       549,  40),
                new Product("Docker Course",   "Courses",     1499, 80),
                new Product("Monitor",         "Hardware",    15000, 8),
                new Product("React Course",    "Courses",     1299, 120)
        );

        // ── COUNT ─────────────────────────────────────────────
        long bookCount = products.stream()
                .filter(p -> p.category().equals("Books"))
                .count();
        System.out.println("Books count: " + bookCount);  // 3

        // ── MIN / MAX ─────────────────────────────────────────
        Optional<Product> cheapest = products.stream()
                .min(Comparator.comparingDouble(Product::price));
        cheapest.ifPresent(p ->
                System.out.println("Cheapest: " + p.name() + " ₹" + p.price()));

        Optional<Product> priciest = products.stream()
                .max(Comparator.comparingDouble(Product::price));
        priciest.ifPresent(p ->
                System.out.println("Priciest: " + p.name() + " ₹" + p.price()));

        // ── MATCH OPERATIONS ──────────────────────────────────
        boolean anyExpensive = products.stream()
                .anyMatch(p -> p.price() > 10000);  // is there any product > 10k?
        System.out.println("Any > ₹10k: " + anyExpensive);  // true (Monitor)

        boolean allInStock = products.stream()
                .allMatch(p -> p.stock() > 0);      // are all products in stock?
        System.out.println("All in stock: " + allInStock);  // true

        boolean noneNegative = products.stream()
                .noneMatch(p -> p.price() < 0);    // no negative prices?
        System.out.println("No negative prices: " + noneNegative);  // true

        // ── REDUCE — combine elements into single value ────────
        // reduce(identity, accumulator)
        // identity = starting value (like 0 for sum, 1 for multiply)
        // accumulator = function combining current result + next element

        // Sum of all prices
        double totalPrice = products.stream()
                .mapToDouble(Product::price)
                .reduce(0.0, Double::sum);         // 0.0 + each price
        System.out.printf("Total: ₹%.0f%n", totalPrice);

        // Product of numbers (multiplication)
        int product = IntStream.rangeClosed(1, 5)
                .reduce(1, (acc, n) -> acc * n);   // 1 * 1 * 2 * 3 * 4 * 5 = 120
        System.out.println("5! = " + product);    // 120

        // Find longest product name using reduce
        Optional<String> longestName = products.stream()
                .map(Product::name)
                .reduce((a, b) -> a.length() >= b.length() ? a : b);
        longestName.ifPresent(n -> System.out.println("Longest name: " + n));

        // ── INTSTREAM STATISTICAL OPS ──────────────────────────
        // When you have IntStream/DoubleStream — use built-in stat ops
        IntSummaryStatistics stats = products.stream()
                .mapToInt(Product::stock)
                .summaryStatistics();
        System.out.println("\n=== Stock Statistics ===");
        System.out.println("Count:   " + stats.getCount());
        System.out.println("Sum:     " + stats.getSum());
        System.out.println("Min:     " + stats.getMin());
        System.out.println("Max:     " + stats.getMax());
        System.out.printf ("Average: %.1f%n", stats.getAverage());

        // ── COLLECTORS — the most powerful terminal op ─────────

        // collect(toList()) — most common
        List<String> names = products.stream()
                .map(Product::name)
                .collect(Collectors.toList());       // mutable list
        // Java 16+: .toList() — unmodifiable list (shorter)
        List<String> immutableNames = products.stream()
                .map(Product::name)
                .toList();   // Java 16+

        // collect(toSet()) — removes duplicates
        Set<String> categories = products.stream()
                .map(Product::category)
                .collect(Collectors.toSet());
        System.out.println("\nCategories: " + categories);

        // collect(toMap()) — collect to Map
        // toMap(keyMapper, valueMapper)
        Map<String, Double> priceMap = products.stream()
                .collect(Collectors.toMap(
                        Product::name,    // key = product name
                        Product::price    // value = price
                ));
        System.out.println("Java Book price: ₹" + priceMap.get("Java Book"));

        // ── GROUPING BY — like SQL GROUP BY ───────────────────
        // Most powerful and commonly used Collector in real projects!
        Map<String, List<Product>> byCategory = products.stream()
                .collect(Collectors.groupingBy(Product::category));

        System.out.println("\n=== Grouped by Category ===");
        byCategory.forEach((cat, prods) -> {
            System.out.println(cat + ":");
            prods.forEach(p -> System.out.printf("  %-20s ₹%.0f%n", p.name(), p.price()));
        });

        // groupingBy with downstream collector
        // Count per category
        Map<String, Long> countPerCat = products.stream()
                .collect(Collectors.groupingBy(
                        Product::category,
                        Collectors.counting()     // downstream: count elements in each group
                ));
        System.out.println("\nCount per category: " + countPerCat);

        // Average price per category
        Map<String, Double> avgPricePerCat = products.stream()
                .collect(Collectors.groupingBy(
                        Product::category,
                        Collectors.averagingDouble(Product::price)
                ));
        System.out.println("Avg price per cat: " + avgPricePerCat);

        // Most expensive product per category
        Map<String, Optional<Product>> maxPerCat = products.stream()
                .collect(Collectors.groupingBy(
                        Product::category,
                        Collectors.maxBy(Comparator.comparingDouble(Product::price))
                ));
        maxPerCat.forEach((cat, prod) ->
                prod.ifPresent(p -> System.out.println(cat + " max: " + p.name())));

        // ── PARTITIONING BY — split into true/false groups ────
        // Special case of groupingBy — always produces Map with 2 keys: true, false
        Map<Boolean, List<Product>> partition = products.stream()
                .collect(Collectors.partitioningBy(p -> p.price() > 1000));

        System.out.println("\nExpensive (>₹1000): " +
                partition.get(true).stream().map(Product::name).toList());
        System.out.println("Affordable:          " +
                partition.get(false).stream().map(Product::name).toList());

        // ── JOINING — concatenate strings ─────────────────────
        String allNames = products.stream()
                .map(Product::name)
                .collect(Collectors.joining(", "));          // delimiter
        System.out.println("\nAll: " + allNames);

        String formatted = products.stream()
                .map(Product::name)
                .collect(Collectors.joining(", ", "[", "]")); // delimiter, prefix, suffix
        System.out.println("Formatted: " + formatted);
    }
    
}
