package Phase_5_Streams_Lambdas_Functional_Programming;

 // ─────────────────────────────────────────────────────────────
// PHASE 5 — L5: Intermediate Stream Operations
//
// Intermediate operations are LAZY — they return a new Stream.
// They don't run until a terminal operation triggers the pipeline.
//
// ALL intermediate operations:
//   filter(Predicate)       → keep elements matching condition
//   map(Function)           → transform each element (T → R)
//   flatMap(Function)       → flatten nested structures
//   mapToInt/Long/Double    → map to primitive stream (avoid boxing)
//   distinct()              → remove duplicates
//   sorted()                → natural sort
//   sorted(Comparator)      → custom sort
//   peek(Consumer)          → debug — see element without changing it
//   limit(n)                → take first n elements
//   skip(n)                 → skip first n elements
//   takeWhile(Predicate)    → take while condition true (Java 9+)
//   dropWhile(Predicate)    → drop while condition true (Java 9+)
// ─────────────────────────────────────────────────────────────

import java.util.*;
import java.util.stream.*;

public class IntermediateOpsDemo {

    record Employee(String name, String dept, double salary, int age) {}
    // record = compact class with auto getters, equals, hashCode, toString (Java 16+)

    public static void main(String[] args) {

        List<Employee> employees = Arrays.asList(
                new Employee("Rashid", "Backend",  85000, 25),
                new Employee("Ali",    "Frontend", 72000, 22),
                new Employee("Sara",   "Backend",  91000, 28),
                new Employee("Priya",  "DevOps",   95000, 30),
                new Employee("Zara",   "Frontend", 68000, 24),
                new Employee("Arjun",  "Backend",  78000, 26),
                new Employee("Neha",   "DevOps",   88000, 29)
        );

        // ── filter() — like .filter() in JS ───────────────────
        System.out.println("=== Backend employees ===");
        employees.stream()
                 .filter(e -> e.dept().equals("Backend"))
                 .forEach(e -> System.out.println("  " + e.name()));
        // Rashid, Sara, Arjun

        // Multiple filters chained
        employees.stream()
                 .filter(e -> e.dept().equals("Backend"))
                 .filter(e -> e.salary() > 80000)
                 .forEach(e -> System.out.println("  Senior Backend: " + e.name()));
        // Sara (91k), Rashid (85k)

        // ── map() — transform each element ────────────────────
        System.out.println("\n=== Names only ===");
        List<String> names = employees.stream()
                .map(Employee::name)          // Employee → String (name)
                .collect(Collectors.toList());
        System.out.println(names);

        // Map to a different type
        List<String> summaries = employees.stream()
                .map(e -> e.name() + " [" + e.dept() + "] ₹" + e.salary())
                .collect(Collectors.toList());
        summaries.forEach(System.out::println);

        // ── mapToInt/Double — AVOID boxing overhead ─────────
        // When mapping to primitives, use specialised streams
        // mapToInt() returns IntStream — no Integer boxing!
        int[] ages = employees.stream()
                .mapToInt(Employee::age)   // Employee → int (no boxing to Integer)
                .toArray();
        System.out.println("\nAges: " + Arrays.toString(ages));

        double totalSalary = employees.stream()
                .mapToDouble(Employee::salary)  // Employee → double
                .sum();
        System.out.printf("Total salary: ₹%.0f%n", totalSalary);

        // ── flatMap() — flatten nested collections ─────────────
        // When each element maps to MULTIPLE elements (1 → many)
        // Like .flatMap() in RxJS or Promise.all chaining

        List<List<String>> nestedSkills = Arrays.asList(
                Arrays.asList("Java", "Spring", "MySQL"),
                Arrays.asList("Python", "Django"),
                Arrays.asList("React", "Node.js", "TypeScript")
        );

        // Without flatMap: Stream<List<String>> — nested
        // With flatMap:    Stream<String> — flattened
        List<String> allSkills = nestedSkills.stream()
                .flatMap(Collection::stream)    // List<String> → Stream<String>
                .distinct()                      // remove any duplicates
                .sorted()                        // alphabetical
                .collect(Collectors.toList());
        System.out.println("\nAll skills: " + allSkills);

        // Real-world flatMap: employee with multiple projects
        // Each employee maps to their list of skills
        List<String> teamSkills = employees.stream()
                .flatMap(e -> getSkillsForDept(e.dept()).stream())
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Team skills: " + teamSkills);

        // ── distinct() — remove duplicates ────────────────────
        List<String> depts = employees.stream()
                .map(Employee::dept)
                .distinct()    // keeps only unique values
                .sorted()
                .collect(Collectors.toList());
        System.out.println("\nUnique departments: " + depts);
        // [Backend, DevOps, Frontend]

        // ── sorted() — natural and custom sort ────────────────
        System.out.println("\n=== Sorted by salary desc ===");
        employees.stream()
                 .sorted(Comparator.comparingDouble(Employee::salary).reversed())
                 .forEach(e -> System.out.printf("  %-8s ₹%.0f%n", e.name(), e.salary()));

        // ── peek() — debug without changing stream ─────────────
        // Like .tap() in RxJS — look at each element mid-pipeline
        System.out.println("\n=== peek() for debugging ===");
        List<String> debugResult = employees.stream()
                .peek(e -> System.out.println("  [1] Input: " + e.name()))
                .filter(e -> e.salary() > 80000)
                .peek(e -> System.out.println("  [2] After filter: " + e.name()))
                .map(Employee::name)
                .peek(s -> System.out.println("  [3] After map: " + s))
                .collect(Collectors.toList());
        System.out.println("Final: " + debugResult);

        // ── limit() and skip() — pagination ───────────────────
        // Very useful for implementing page-based results
        System.out.println("\n=== Pagination ===");
        int pageSize = 3;
        int pageNum  = 1;   // 0-indexed page

        List<Employee> page = employees.stream()
                .skip((long) pageNum * pageSize)   // skip first page
                .limit(pageSize)                   // take next 3
                .collect(Collectors.toList());
        page.forEach(e -> System.out.println("  Page " + pageNum + ": " + e.name()));

        // ── takeWhile() and dropWhile() — Java 9+ ─────────────
        // takeWhile: take elements WHILE condition is true (stop at first false)
        // dropWhile: skip elements WHILE condition is true (start at first false)
        List<Integer> nums = Arrays.asList(2, 4, 6, 7, 8, 10, 12);

        List<Integer> taken = nums.stream()
                .takeWhile(n -> n % 2 == 0)   // take while even — stops at 7
                .collect(Collectors.toList());
        System.out.println("\ntakeWhile even: " + taken); // [2, 4, 6]

        List<Integer> dropped = nums.stream()
                .dropWhile(n -> n % 2 == 0)   // drop while even — starts at 7
                .collect(Collectors.toList());
        System.out.println("dropWhile even: " + dropped); // [7, 8, 10, 12]
    }

    static List<String> getSkillsForDept(String dept) {
        return switch (dept) {
            case "Backend"  -> List.of("Java", "Spring", "MySQL");
            case "Frontend" -> List.of("React", "TypeScript", "CSS");
            case "DevOps"   -> List.of("Docker", "Kubernetes", "Linux");
            default         -> List.of();
        };
    }
    
}
