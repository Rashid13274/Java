package Phase_5_Streams_Lambdas_Functional_Programming;

 // ─────────────────────────────────────────────────────────────
// PHASE 5 — L9: Real-World Stream Patterns
//
// Patterns you'll write daily in Spring Boot backends:
//   - DTO transformation
//   - Multi-level grouping
//   - Building complex reports
//   - Searching/filtering APIs
//   - Data aggregation (like SQL aggregates)
// ─────────────────────────────────────────────────────────────

import java.util.*;
import java.util.stream.*;

public class RealWorldStreams {

    // ── DOMAIN MODELS ─────────────────────────────────────────
    record Order(int id, String customer, String product,
                 String category, double amount, String status) {}

    record CustomerReport(String customer, long orderCount,
                          double totalSpent, double avgOrderValue,
                          String mostBoughtCategory) {}

    public static void main(String[] args) {

        List<Order> orders = Arrays.asList(
                new Order(1,  "Rashid", "Java Book",     "Books",    499,  "DELIVERED"),
                new Order(2,  "Rashid", "Spring Course", "Courses",  1999, "DELIVERED"),
                new Order(3,  "Ali",    "Python Book",   "Books",    399,  "PENDING"),
                new Order(4,  "Sara",   "Keyboard",      "Hardware", 2500, "DELIVERED"),
                new Order(5,  "Rashid", "MySQL Guide",   "Books",    549,  "CANCELLED"),
                new Order(6,  "Ali",    "Docker Course", "Courses",  1499, "DELIVERED"),
                new Order(7,  "Sara",   "Monitor",       "Hardware", 15000,"DELIVERED"),
                new Order(8,  "Priya",  "React Course",  "Courses",  1299, "PENDING"),
                new Order(9,  "Rashid", "Docker Course", "Courses",  1499, "DELIVERED"),
                new Order(10, "Ali",    "Java Book",     "Books",    499,  "DELIVERED")
        );

        // ── PATTERN 1: FILTER + TRANSFORM (DTO mapping) ────────
        // In Spring Boot: convert entity to DTO before sending response
        System.out.println("=== Delivered orders (summarised) ===");
        orders.stream()
              .filter(o -> o.status().equals("DELIVERED"))
              .sorted(Comparator.comparingDouble(Order::amount).reversed())
              .map(o -> String.format("Order#%-3d %-10s ₹%-6.0f",
                      o.id(), o.customer(), o.amount()))
              .forEach(System.out::println);

        // ── PATTERN 2: GROUPING + AGGREGATION (like SQL) ───────
        // GROUP BY customer, SUM amount, COUNT orders
        System.out.println("\n=== Revenue by customer ===");
        Map<String, DoubleSummaryStatistics> revenueByCustomer = orders.stream()
                .filter(o -> !o.status().equals("CANCELLED"))
                .collect(Collectors.groupingBy(
                        Order::customer,
                        Collectors.summarizingDouble(Order::amount)
                ));

        revenueByCustomer.entrySet().stream()
                .sorted(Map.Entry.<String, DoubleSummaryStatistics>comparingByValue(
                        Comparator.comparingDouble(DoubleSummaryStatistics::getSum)).reversed())
                .forEach(e -> System.out.printf(
                        "%-8s Orders: %d | Total: ₹%-7.0f | Avg: ₹%.0f%n",
                        e.getKey(), e.getValue().getCount(),
                        e.getValue().getSum(), e.getValue().getAverage()));

        // ── PATTERN 3: MULTI-LEVEL GROUPING ───────────────────
        // GROUP BY customer THEN BY category
        System.out.println("\n=== Orders by customer → category ===");
        Map<String, Map<String, Long>> nestedGroup = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::customer,
                        Collectors.groupingBy(
                                Order::category,
                                Collectors.counting()
                        )
                ));
        nestedGroup.forEach((customer, catMap) -> {
            System.out.println(customer + ":");
            catMap.forEach((cat, count) ->
                    System.out.println("  " + cat + ": " + count + " orders"));
        });

        // ── PATTERN 4: BUILDING A REPORT OBJECT ───────────────
        // Compute full customer report in one stream pass
        System.out.println("\n=== Customer Reports ===");
        List<CustomerReport> reports = orders.stream()
                .filter(o -> !o.status().equals("CANCELLED"))
                .collect(Collectors.collectingAndThen(
                        Collectors.groupingBy(Order::customer),
                        map -> map.entrySet().stream()
                                .map(e -> {
                                    String customer = e.getKey();
                                    List<Order> customerOrders = e.getValue();

                                    // Find most bought category
                                    String topCat = customerOrders.stream()
                                            .collect(Collectors.groupingBy(
                                                    Order::category, Collectors.counting()))
                                            .entrySet().stream()
                                            .max(Map.Entry.comparingByValue())
                                            .map(Map.Entry::getKey)
                                            .orElse("N/A");

                                    return new CustomerReport(
                                            customer,
                                            customerOrders.size(),
                                            customerOrders.stream()
                                                    .mapToDouble(Order::amount).sum(),
                                            customerOrders.stream()
                                                    .mapToDouble(Order::amount).average()
                                                    .orElse(0),
                                            topCat
                                    );
                                })
                                .sorted(Comparator.comparingDouble(
                                        CustomerReport::totalSpent).reversed())
                                .collect(Collectors.toList())
                ));

        reports.forEach(r -> System.out.printf(
                "%-8s | Orders: %2d | Total: ₹%-7.0f | Avg: ₹%-6.0f | Top: %s%n",
                r.customer(), r.orderCount(), r.totalSpent(),
                r.avgOrderValue(), r.mostBoughtCategory()));

        // ── PATTERN 5: SEARCH + RANK (like an API endpoint) ────
        // GET /orders?category=Books&status=DELIVERED&sortBy=amount&order=desc
        String filterCategory = "Books";
        String filterStatus   = "DELIVERED";

        System.out.println("\n=== Search Results ===");
        orders.stream()
              .filter(o -> filterCategory == null || o.category().equals(filterCategory))
              .filter(o -> filterStatus == null   || o.status().equals(filterStatus))
              .sorted(Comparator.comparingDouble(Order::amount).reversed())
              .forEach(o -> System.out.printf(
                      "[%d] %-15s ₹%.0f (%s)%n",
                      o.id(), o.product(), o.amount(), o.customer()));

        // ── PATTERN 6: FLAT STATISTICS ─────────────────────────
        System.out.println("\n=== Overall Stats ===");
        DoubleSummaryStatistics overall = orders.stream()
                .filter(o -> o.status().equals("DELIVERED"))
                .mapToDouble(Order::amount)
                .summaryStatistics();
        System.out.printf("Orders:  %d%n",   overall.getCount());
        System.out.printf("Revenue: ₹%.0f%n",overall.getSum());
        System.out.printf("Avg:     ₹%.0f%n",overall.getAverage());
        System.out.printf("Max:     ₹%.0f%n",overall.getMax());
        System.out.printf("Min:     ₹%.0f%n",overall.getMin());

        // ── PATTERN 7: PARTITION THEN PROCESS EACH ─────────────
        Map<Boolean, List<Order>> partitioned = orders.stream()
                .collect(Collectors.partitioningBy(
                        o -> o.status().equals("DELIVERED")));

        System.out.println("\nDelivered: " + partitioned.get(true).size());
        System.out.println("Pending/Cancelled: " + partitioned.get(false).size());
    }
    
}
