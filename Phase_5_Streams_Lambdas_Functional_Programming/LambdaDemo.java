package Phase_5_Streams_Lambdas_Functional_Programming;

// public // ─────────────────────────────────────────────────────────────
// PHASE 5 — L1: Lambda Expressions
//
// Lambda = a short, anonymous function you can pass around.
// Introduced in Java 8 — the biggest change in Java's history.
//
// SYNTAX:
//   (parameters) -> expression
//   (parameters) -> { statements; }
//
// Node.js parallel:
//   JS arrow function:  (x) => x * 2
//   Java lambda:        (x) -> x * 2    ← almost identical!
//
// WHY LAMBDAS?
//   Before Java 8, to pass behaviour you needed anonymous classes:
//     button.addListener(new ActionListener() {
//         public void actionPerformed(ActionEvent e) {
//             System.out.println("clicked");
//         }
//     });
//
//   With Lambda — same thing in one line:
//     button.addListener(e -> System.out.println("clicked"));
//
// KEY RULE: Lambdas only work with FUNCTIONAL INTERFACES
//   A functional interface = interface with exactly ONE abstract method.
//   Examples: Runnable, Comparator, Predicate, Function, Consumer, Supplier
//
// VARIABLE CAPTURE:
//   Lambdas can use variables from outer scope,
//   but those variables must be EFFECTIVELY FINAL
//   (not changed after assignment — same as JS arrow functions
//    capturing variables from outer scope).
// ─────────────────────────────────────────────────────────────

import java.util.*;
import java.util.function.*;

public class LambdaDemo {
    public static void main(String[] args) {

        // ── LAMBDA SYNTAX VARIATIONS ──────────────────────────

        // 1. No parameters
        Runnable greet = () -> System.out.println("Hello from Lambda!");
        greet.run();

        // 2. One parameter — parentheses optional
        Consumer<String> print = name -> System.out.println("Hi, " + name);
        print.accept("Rashid");

        // 3. Multiple parameters — parentheses required
        Comparator<Integer> compare = (a, b) -> a - b;
        System.out.println(compare.compare(10, 5));   // positive → 10 > 5

        // 4. Multi-line body — use curly braces + return
        Function<Integer, String> classify = (n) -> {
            if (n < 0)   return "Negative";
            if (n == 0)  return "Zero";
            return "Positive";
        };
        System.out.println(classify.apply(-5));   // Negative
        System.out.println(classify.apply(0));    // Zero
        System.out.println(classify.apply(42));   // Positive

        // 5. With explicit type parameters (optional — compiler infers usually)
        Comparator<String> byLength = (String a, String b) -> a.length() - b.length();

        // ── LAMBDAS WITH COLLECTIONS ──────────────────────────

        List<String> names = new ArrayList<>(
                Arrays.asList("Zara", "Rashid", "Ali", "Sara", "Priya"));

        // Sort with lambda comparator — like arr.sort((a,b) => ...) in JS
        names.sort((a, b) -> a.compareTo(b));      // alphabetical
        System.out.println("Sorted: " + names);

        names.sort((a, b) -> b.compareTo(a));      // reverse alphabetical
        System.out.println("Reversed: " + names);

        names.sort((a, b) -> a.length() - b.length()); // by length
        System.out.println("By length: " + names);

        // forEach with lambda — like .forEach(name => ...) in JS
        names.forEach(name -> System.out.println("→ " + name));

        // removeIf with lambda — like .filter() but mutates the list
        names.removeIf(name -> name.length() <= 3);  // remove short names
        System.out.println("After removeIf: " + names);

        // ── VARIABLE CAPTURE ──────────────────────────────────
        String prefix  = "Hello";      // effectively final — not reassigned
        int    counter = 0;            // effectively final

        Consumer<String> greeter = name -> {
            System.out.println(prefix + ", " + name + "!");
            // counter++;   // ❌ COMPILE ERROR — cannot modify captured variable
        };
        greeter.accept("Rashid");

        // ── LAMBDAS AS METHOD ARGUMENTS ───────────────────────
        // This is the most common use — pass behaviour to a method
        processNumbers(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                n -> n % 2 == 0,           // filter: keep even numbers
                n -> System.out.println("Even: " + n)  // action: print them
        );
    }

    // Method that accepts lambdas as parameters
    public static void processNumbers(
            List<Integer> numbers,
            Predicate<Integer> filter,    // filter condition
            Consumer<Integer> action) {   // what to do with each

        for (Integer n : numbers) {
            if (filter.test(n)) {
                action.accept(n);
            }
        }
    }
    
}
