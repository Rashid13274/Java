package Phase_5_Streams_Lambdas_Functional_Programming;

 // ─────────────────────────────────────────────────────────────
// PHASE 5 — L3: Method References
//
// Method references are a shorthand for lambdas that simply
// CALL an existing method — cleaner, more readable.
//
// SYNTAX:  ClassName::methodName  or  object::methodName
//
// 4 TYPES:
//   1. Static method ref    → ClassName::staticMethod
//   2. Instance method ref  → object::instanceMethod (specific object)
//   3. Instance method ref  → ClassName::instanceMethod (any object of type)
//   4. Constructor ref      → ClassName::new
//
// Node.js parallel:
//   Like passing a function reference:
//   arr.forEach(console.log)   →   list.forEach(System.out::println)
//   arr.map(parseInt)          →   list.stream().map(Integer::parseInt)
// ─────────────────────────────────────────────────────────────

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class MethodReferenceDemo {
    public static void main(String[] args) {

        // ── TYPE 1: STATIC METHOD REFERENCE ───────────────────
        // Lambda:          n -> Integer.parseInt(n)
        // Method ref:      Integer::parseInt

        Function<String, Integer> parseWithLambda = n -> Integer.parseInt(n);
        Function<String, Integer> parseWithRef    = Integer::parseInt;     // cleaner!

        System.out.println(parseWithRef.apply("42"));     // 42

        // More examples — static methods
        Function<Double, Double> abs  = Math::abs;        // n -> Math.abs(n)
        Function<String, String> trim = String::valueOf;  // n -> String.valueOf(n)

        List<String> numbers = Arrays.asList("5", "2", "8", "1", "9");
        List<Integer> parsed = numbers.stream()
                .map(Integer::parseInt)   // cleaner than n -> Integer.parseInt(n)
                .collect(Collectors.toList());
        System.out.println("Parsed: " + parsed);  // [5, 2, 8, 1, 9]

        // ── TYPE 2: INSTANCE METHOD on SPECIFIC OBJECT ────────
        // Lambda:       s -> myObject.someMethod(s)
        // Method ref:   myObject::someMethod

        String prefix = "Hello, ";
        Function<String, String> greet = prefix::concat;
        // Same as: name -> prefix.concat(name)
        System.out.println(greet.apply("Rashid"));  // Hello, Rashid

        // Most famous example:
        // System.out is a specific PrintStream OBJECT
        // println is an instance method on it
        Consumer<String> printer = System.out::println;
        // Same as: s -> System.out.println(s)

        List<String> names = List.of("Java", "Spring", "MySQL");
        names.forEach(System.out::println);           // classic usage

        // ── TYPE 3: INSTANCE METHOD on ANY OBJECT OF TYPE ─────
        // Lambda:       s -> s.toUpperCase()
        // Method ref:   String::toUpperCase
        // The first argument becomes the object the method is called on

        Function<String, String> upper = String::toUpperCase;
        // Same as: s -> s.toUpperCase()
        System.out.println(upper.apply("java"));    // JAVA

        Function<String, Integer> length = String::length;
        // Same as: s -> s.length()
        System.out.println(length.apply("Rashid")); // 6

        // BiFunction — first arg is the object, second is the parameter
        BiFunction<String, String, Boolean> contains = String::contains;
        // Same as: (s, sub) -> s.contains(sub)
        System.out.println(contains.apply("Java is great", "Java")); // true

        // Used in streams — very common pattern
        List<String> words = Arrays.asList("  Java  ", "  Spring  ", "  Boot  ");
        List<String> trimmed = words.stream()
                .map(String::trim)          // s -> s.trim()
                .map(String::toLowerCase)   // s -> s.toLowerCase()
                .collect(Collectors.toList());
        System.out.println(trimmed);   // [java, spring, boot]

        // ── TYPE 4: CONSTRUCTOR REFERENCE ─────────────────────
        // Lambda:       () -> new ArrayList<>()
        // Method ref:   ArrayList::new

        Supplier<ArrayList<String>> listFactory = ArrayList::new;
        // Same as: () -> new ArrayList<>()
        ArrayList<String> newList = listFactory.get();

        // Constructor with parameter
        Function<String, StringBuilder> sbFactory = StringBuilder::new;
        // Same as: s -> new StringBuilder(s)
        StringBuilder sb = sbFactory.apply("Hello");
        System.out.println(sb.reverse());  // olleH

        // With streams — create objects from data
        List<String> nameList = Arrays.asList("rashid", "ali", "sara");

        // Create StringBuilder from each string
        List<StringBuilder> builders = nameList.stream()
                .map(StringBuilder::new)       // s -> new StringBuilder(s)
                .collect(Collectors.toList());
        System.out.println(builders);

        // ── WHEN TO USE METHOD REFS vs LAMBDAS ────────────────
        // Use method reference when lambda simply calls one method
        // Use lambda when there's logic or multiple statements

        // ✅ Method ref — just calling existing method
        names.forEach(System.out::println);
        names.stream().map(String::toUpperCase).forEach(System.out::println);

        // ✅ Lambda — has logic
        names.stream()
             .filter(name -> name.length() > 3 && name.startsWith("S"))
             .forEach(name -> System.out.println("→ " + name));
    }
    
}
