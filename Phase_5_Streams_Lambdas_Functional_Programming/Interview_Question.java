package Phase_5_Streams_Lambdas_Functional_Programming;

// ─────────────────────────────────────────────────────────────
// Interview Questions (Streams & Functional)
// ─────────────────────────────────────────────────────────────

/**
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
 * 
 Q1. What is a Stream? How is it different from a Collection?
 ANSWER:

Collection:
  - STORES data in memory (List, Set, Map)
  - Can be iterated multiple times
  - External iteration (you write the loop)
  - Eager — all elements exist at once

Stream:
  - PROCESSES data — no storage of its own
  - Can only be consumed ONCE
  - Internal iteration (Stream handles the loop)
  - Lazy — intermediate ops don't run until terminal
  - Can be infinite (Stream.generate(), Stream.iterate())
  - Supports parallel processing natively

ANALOGY:
  Collection = a bucket of water (stored, reusable)
  Stream     = a hose pipe (flowing, processes in transit)

KEY POINT FOR INTERVIEWS:
  "Streams are declarative — you say WHAT to do,
   not HOW to do it. The Stream API figures out the
   most efficient execution order (lazy + short-circuit)."

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Q2. What is the difference between map() and flatMap()?
// ANSWER:
// map()     → one input → one output   (1:1 transformation)
// flatMap() → one input → many outputs (1:N, then flattens to 1 level)

// map() — wraps in Optional/Stream if mapper returns one
List<String> names = List.of("Rashid", "Ali");
List<Integer> lengths = names.stream()
        .map(String::length)      // "Rashid" → 6, "Ali" → 3
        .collect(Collectors.toList()); // [6, 3]

// If map() returns a Stream, you get Stream<Stream<T>> — nested!
List<List<Integer>> nested = List.of(List.of(1,2), List.of(3,4), List.of(5));
List<Stream<Integer>> wrong = nested.stream()
        .map(Collection::stream)   // each List → Stream — NOT what we want!
        .collect(Collectors.toList()); // Stream<Stream<Integer>>

// flatMap() — maps then FLATTENS one level
List<Integer> flat = nested.stream()
        .flatMap(Collection::stream)   // each List → Stream, then flattened
        .collect(Collectors.toList()); // [1, 2, 3, 4, 5]

// REAL-WORLD EXAMPLE:
// User has multiple addresses — get ALL cities across all users
List<List<String>> userCities = List.of(
        List.of("Delhi", "Mumbai"),
        List.of("Bangalore"),
        List.of("Chennai", "Hyderabad", "Pune")
);
List<String> allCities = userCities.stream()
        .flatMap(Collection::stream)
        .distinct()
        .collect(Collectors.toList());

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Q3. What is the difference between findFirst() and findAny()?
ANSWER:

findFirst():
  - Returns the FIRST element in encounter order
  - Deterministic — always returns same element
  - For sequential streams: first in the list
  - For parallel streams: still returns "first" in order (slower)

findAny():
  - Returns ANY element — whichever thread finds one first
  - Non-deterministic in parallel streams
  - Faster in parallel — no coordination overhead
  - For sequential streams: usually returns first element too

RULE:
  Sequential stream → use findFirst() (predictable)
  Parallel stream   → use findAny() (faster)
  If order matters  → always findFirst()

Both return Optional<T> — handle the empty case!

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Q4. What is the difference between map() and peek()?
// ANSWER:
// map()  → TRANSFORMS each element (returns new value)
//          Changes the stream element type or value
// peek() → OBSERVES each element (returns SAME element unchanged)
//          Used for debugging/logging only

// map() — changes the value
List<String> upper = List.of("java", "spring")
        .stream()
        .map(String::toUpperCase)   // "java" → "JAVA"
        .collect(Collectors.toList());
// Result: ["JAVA", "SPRING"]

// peek() — observes, doesn't change
List<String> peeked = List.of("java", "spring")
        .stream()
        .peek(s -> System.out.println("Before: " + s))  // just logging
        .map(String::toUpperCase)
        .peek(s -> System.out.println("After: " + s))   // just logging
        .collect(Collectors.toList());
// Elements are STILL modified by map() — peek didn't change them
// peek() is a DIAGNOSTIC tool — don't use for side effects in production

// WARNING: Don't rely on peek() for business logic
// It may not run in some terminal operations (short-circuit)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Q5. What is reduce() and how does it work?

// ANSWER:
// reduce() combines all stream elements into a SINGLE value
// by repeatedly applying a BinaryOperator.

// reduce(identity, accumulator):
//   identity    = initial/default value (returned if stream is empty)
//   accumulator = (result, element) -> newResult

// SUM: 0 + 1 = 1, 1 + 2 = 3, 3 + 3 = 6, 6 + 4 = 10, 10 + 5 = 15
int sum = IntStream.rangeClosed(1, 5)
        .reduce(0, Integer::sum);  // 15

// PRODUCT: 1 * 1 * 2 * 3 * 4 * 5 = 120
int factorial = IntStream.rangeClosed(1, 5)
        .reduce(1, (acc, n) -> acc * n);  // 120

// MAX string by length
Optional<String> longest = List.of("Java", "Python", "Go")
        .stream()
        .reduce((a, b) -> a.length() >= b.length() ? a : b);
// Optional because stream may be empty (no identity provided)

// HOW IT WORKS INTERNALLY:
// Stream: [1, 2, 3, 4, 5], identity=0, op = Integer::sum
// Step 1: acc=0,  n=1 → acc=1
// Step 2: acc=1,  n=2 → acc=3
// Step 3: acc=3,  n=3 → acc=6
// Step 4: acc=6,  n=4 → acc=10
// Step 5: acc=10, n=5 → acc=15
// Result: 15
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Q6. What is the difference between Collector and collect()?
ANSWER:

collect() is a TERMINAL OPERATION on a stream.
  It takes a Collector and produces a result.

Collector is the STRATEGY for how to accumulate elements.
  It defines: supplier (create container), accumulator (add element),
              combiner (merge containers for parallel), finisher (final transform)

Collectors (plural) is a UTILITY CLASS with factory methods
  for the most common Collectors:
    Collectors.toList()
    Collectors.toSet()
    Collectors.toMap()
    Collectors.groupingBy()
    Collectors.joining()
    Collectors.counting()
    Collectors.summarizingDouble()

SUMMARY:
  stream.collect( Collectors.toList() )
  ↑ terminal op   ↑ Collector from Collectors class

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Q7. What is the difference between Predicate, Function, and Consumer?
ANSWER (table format — great for interviews):

┌─────────────────┬────────────┬────────────┬──────────────────────────┐
│ Interface       │ Input      │ Output     │ Used in                  │
├─────────────────┼────────────┼────────────┼──────────────────────────┤
│ Predicate<T>    │ T          │ boolean    │ filter(), anyMatch()      │
│ Function<T,R>   │ T          │ R          │ map(), collect()          │
│ Consumer<T>     │ T          │ void       │ forEach(), peek()         │
│ Supplier<T>     │ (none)     │ T          │ orElseGet(), lazy init    │
│ UnaryOperator<T>│ T          │ T          │ replaceAll(), map()       │
│ BinaryOperator<T>│ T, T      │ T          │ reduce()                 │
└─────────────────┴────────────┴────────────┴──────────────────────────┘

MEMORY AID:
  Predicate → "Is it?" (yes/no question)
  Function  → "Transform it" (change the type/value)
  Consumer  → "Use it" (do something, return nothing)
  Supplier  → "Give me one" (produce without input)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

  Q8. What is a functional interface? Can you create your own?
  // ANSWER:
// A functional interface has EXACTLY ONE abstract method.
// It can have default methods and static methods — that's fine.
// Annotate with @FunctionalInterface (optional but recommended —
// compiler gives error if you accidentally add a second abstract method).

// Creating your own — very common in Spring, custom APIs
@FunctionalInterface
interface Validator<T> {
    boolean validate(T value);   // only ONE abstract method

    // default methods are allowed
    default Validator<T> and(Validator<T> other) {
        return value -> this.validate(value) && other.validate(value);
    }

    default Validator<T> or(Validator<T> other) {
        return value -> this.validate(value) || other.validate(value);
    }
}

// Usage:
Validator<String> notEmpty   = s -> !s.isBlank();
Validator<String> hasAtSign  = s -> s.contains("@");
Validator<String> emailCheck = notEmpty.and(hasAtSign);

System.out.println(emailCheck.validate("r@x.com")); // true
System.out.println(emailCheck.validate(""));         // false

// Built-in equivalents:
// Validator<T>   ≈  Predicate<T>
// Transformer<T> ≈  Function<T, R>
// Create your own ONLY when built-in names don't express intent clearly
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Q9. What is the difference between orElse() and orElseGet() in Optional?
// ANSWER:
// Both provide a fallback when Optional is empty.
// KEY DIFFERENCE: when the fallback is COMPUTED.

// orElse(T value)  → fallback is ALWAYS EVALUATED
//                    even if Optional has a value!
// orElseGet(Supplier<T>) → fallback is LAZILY EVALUATED
//                          only when Optional IS empty

// Demo:
Optional<String> present = Optional.of("Rashid");
Optional<String> empty   = Optional.empty();

// Both of these call computeDefault() — even though 'present' has a value!
present.orElse(computeDefault());   // computeDefault() called unnecessarily!
empty.orElse(computeDefault());     // computeDefault() called — expected

// orElseGet() — computeDefault() only called when needed:
present.orElseGet(() -> computeDefault()); // computeDefault() NOT called
empty.orElseGet(() -> computeDefault());   // computeDefault() called — expected

// RULE:
//   orElse()    → use for CHEAP defaults (string literals, constants)
//   orElseGet() → use for EXPENSIVE defaults (DB calls, file reads, new objects)

static String computeDefault() {
    System.out.println("(expensive operation running...)");
    return "Default";
}

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Q10. Can a stream be reused? What happens if you try?
// ANSWER:
// NO — a stream can only be consumed ONCE.
// After a terminal operation, the stream is CLOSED.
// Trying to reuse it throws: IllegalStateException: stream has already been operated upon or closed

Stream<String> stream = List.of("A", "B", "C").stream();

long count = stream.count();  // ✅ terminal op — consumes stream
System.out.println(count);    // 3

try {
    List<String> list = stream.collect(Collectors.toList()); // ❌ stream already closed!
} catch (IllegalStateException e) {
    System.out.println("Error: " + e.getMessage());
}

// FIX 1: call .stream() again from the source collection
List<String> source = List.of("A", "B", "C");
long count2 = source.stream().count();
List<String> list2 = source.stream().collect(Collectors.toList()); // fresh stream

// FIX 2: store intermediate result in a collection first
List<String> filtered = source.stream()
        .filter(s -> !s.equals("B"))
        .collect(Collectors.toList()); // store result

// Now reuse 'filtered' — it's a Collection, not a Stream
long cnt = filtered.stream().count();
String joined = filtered.stream().collect(Collectors.joining(", "));

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

 */
