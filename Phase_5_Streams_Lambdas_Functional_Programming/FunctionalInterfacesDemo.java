package Phase_5_Streams_Lambdas_Functional_Programming;

// ─────────────────────────────────────────────────────────────
// PHASE 5 — L2: Built-in Functional Interfaces (java.util.function)
//
// Java 8 added ~43 functional interfaces in java.util.function.
// You don't need to memorise all — focus on these 5 core ones:
//
// ┌─────────────────┬──────────────────┬─────────────────────┐
// │ Interface       │ Method           │ Description         │
// ├─────────────────┼──────────────────┼─────────────────────┤
// │ Predicate<T>    │ test(T t)        │ T → boolean         │
// │ Function<T,R>   │ apply(T t)       │ T → R (transform)  │
// │ Consumer<T>     │ accept(T t)      │ T → void (use it)  │
// │ Supplier<T>     │ get()            │ () → T (produce it) │
// │ BiFunction<T,U,R>│ apply(T t, U u) │ T,U → R            │
// └─────────────────┴──────────────────┴─────────────────────┘
//
// Node.js parallel:
//   Predicate  → filter callback:  (item) => item.age > 18
//   Function   → map callback:     (item) => item.name
//   Consumer   → forEach callback: (item) => console.log(item)
//   Supplier   → factory function: () => new Object()
// ─────────────────────────────────────────────────────────────

import java.util.*;
import java.util.function.*;

public class FunctionalInterfacesDemo {
    public static void main(String[] args) {

        // ── 1. PREDICATE<T> — T → boolean ─────────────────────
        // Tests a condition — returns true or false
        // Used in: filter(), removeIf(), anyMatch(), allMatch()

        Predicate<Integer> isEven      = n -> n % 2 == 0;
        Predicate<String>  isLongName  = s -> s.length() > 5;
        Predicate<String>  startsWithA = s -> s.startsWith("A");

        System.out.println(isEven.test(4));        // true
        System.out.println(isEven.test(7));        // false
        System.out.println(isLongName.test("Rashid")); // true

        // Predicate COMPOSITION — combine predicates
        Predicate<Integer> isPositive    = n -> n > 0;
        Predicate<Integer> isSmall       = n -> n < 100;

        // and() → both must be true (&&)
        Predicate<Integer> isSmallPositive = isPositive.and(isSmall);
        System.out.println(isSmallPositive.test(50));   // true
        System.out.println(isSmallPositive.test(-5));   // false
        System.out.println(isSmallPositive.test(150));  // false

        // or() → at least one must be true (||)
        Predicate<Integer> isEvenOrPositive = isEven.or(isPositive);
        System.out.println(isEvenOrPositive.test(-4));  // true (even)
        System.out.println(isEvenOrPositive.test(3));   // true (positive)
        System.out.println(isEvenOrPositive.test(-3));  // false

        // negate() → flips the result (!)
        Predicate<Integer> isOdd = isEven.negate();
        System.out.println(isOdd.test(5));   // true

        // ── 2. FUNCTION<T, R> — T → R ─────────────────────────
        // Transforms input of type T to output of type R
        // Used in: map(), collect(), transform()

        Function<String, Integer> strLen    = s -> s.length();
        Function<Integer, String> intToStr  = n -> "Number: " + n;
        Function<String, String>  toUpper   = String::toUpperCase; // method reference

        System.out.println(strLen.apply("Rashid"));    // 6
        System.out.println(intToStr.apply(42));        // Number: 42
        System.out.println(toUpper.apply("java"));     // JAVA

        // Function COMPOSITION
        // andThen() → apply this, THEN apply the next function
        Function<String, String> trimAndUpper = 
                ((Function<String, String>) String::trim).andThen(String::toUpperCase);
        System.out.println(trimAndUpper.apply("  hello  ")); // HELLO

        // compose() → apply the GIVEN function FIRST, then this one
        // f.compose(g) = f(g(x))
        Function<Integer, Integer> times2    = n -> n * 2;
        Function<Integer, Integer> plus3     = n -> n + 3;
        Function<Integer, Integer> times2ThenPlus3 = plus3.compose(times2);
        // times2(5) = 10 → plus3(10) = 13
        System.out.println(times2ThenPlus3.apply(5));  // 13

        // ── 3. CONSUMER<T> — T → void ─────────────────────────
        // Performs an action on the input, returns nothing
        // Used in: forEach(), peek()

        Consumer<String> printUpper  = s -> System.out.println(s.toUpperCase());
        Consumer<String> printLength = s -> System.out.println("Length: " + s.length());

        printUpper.accept("hello");    // HELLO
        printLength.accept("hello");   // Length: 5

        // Consumer CHAINING — andThen()
        // Execute first consumer, then second consumer on same input
        Consumer<String> printBoth = printUpper.andThen(printLength);
        printBoth.accept("rashid");   // RASHID \n Length: 6

        // With forEach
        List<String> items = List.of("Java", "Spring", "MySQL");
        items.forEach(item -> System.out.println("✓ " + item));

        // ── 4. SUPPLIER<T> — () → T ───────────────────────────
        // Produces a value — takes NO input, returns something
        // Used in: lazy initialisation, Optional.orElseGet(), factories

        Supplier<String>   greeting = () -> "Hello, World!";
        Supplier<List<String>> newList = () -> new ArrayList<>();  // factory
        Supplier<Double>   randomNum  = () -> Math.random() * 100;

        System.out.println(greeting.get());     // Hello, World!
        System.out.println(randomNum.get());    // some random number
        List<String> list = newList.get();      // fresh ArrayList each time

        // Supplier for lazy default value (don't compute unless needed)
        // orElse()    → always computes the default, even if not needed
        // orElseGet() → only computes default if value is absent (lazy)
        Optional<String> opt = Optional.empty();
        String val1 = opt.orElse(expensiveComputation());           // always runs
        String val2 = opt.orElseGet(() -> expensiveComputation());  // lazy — only if empty

        // ── 5. BIFUNCTION<T, U, R> — T, U → R ────────────────
        // Like Function but takes TWO inputs
        BiFunction<String, Integer, String> repeat =
                (str, times) -> str.repeat(times);
        System.out.println(repeat.apply("Java! ", 3));  // Java! Java! Java!

        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        System.out.println(add.apply(10, 20));           // 30

        // ── 6. OTHER USEFUL ONES ──────────────────────────────
        // UnaryOperator<T> — T → T (Function where input = output type)
        UnaryOperator<String> trim  = String::trim;
        UnaryOperator<Integer> abs  = Math::abs;
        System.out.println(trim.apply("  hello  "));   // "hello"
        System.out.println(abs.apply(-42));             // 42

        // BinaryOperator<T> — T, T → T (BiFunction where all types same)
        BinaryOperator<Integer> multiply = (a, b) -> a * b;
        BinaryOperator<String>  concat   = (a, b) -> a + b;
        System.out.println(multiply.apply(6, 7));    // 42
        System.out.println(concat.apply("Java", "8")); // Java8
    }

    static String expensiveComputation() {
        System.out.println("(computing default...)");
        return "default";
    }
}