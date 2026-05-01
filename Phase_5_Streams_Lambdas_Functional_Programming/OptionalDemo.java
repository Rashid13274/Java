package Phase_5_Streams_Lambdas_Functional_Programming;

// ─────────────────────────────────────────────────────────────
// PHASE 5 — L7: Optional<T>
//
// Optional is a container that may or may not hold a value.
// It forces you to handle the "no value" case explicitly —
// no more silent NullPointerExceptions!
//
// In Node.js: no direct equivalent — you use null checks,
// optional chaining (?.) and nullish coalescing (??) instead.
//
// Java's Optional is:
//   obj?.name              →  opt.map(obj -> obj.getName())
//   value ?? "default"     →  opt.orElse("default")
//   if (value != null) {}  →  opt.ifPresent(v -> {})
//
// WHEN TO USE Optional:
//   ✅ Return type of methods that may not find a result
//      (findById, findByEmail, stream().findFirst())
//   ❌ As a field in a class (use null instead)
//   ❌ As a method parameter (use overloading or null instead)
//   ❌ In collections (use empty collection instead)
// ─────────────────────────────────────────────────────────────

import java.util.*;
import java.util.stream.*;

public class OptionalDemo {

    record User(int id, String name, String email, String phone) {}

    public static void main(String[] args) {

        // ── CREATING OPTIONAL ──────────────────────────────────
        Optional<String> withValue   = Optional.of("Rashid");    // non-null value
        Optional<String> empty       = Optional.empty();         // no value
        Optional<String> nullable    = Optional.ofNullable(null);// null → empty optional
        Optional<String> nullable2   = Optional.ofNullable("Ali");// non-null → has value

        // Optional.of(null) → throws NullPointerException!
        // Optional.ofNullable(null) → returns Optional.empty() safely

        // ── CHECKING IF VALUE EXISTS ───────────────────────────
        System.out.println(withValue.isPresent());  // true
        System.out.println(empty.isPresent());      // false
        System.out.println(empty.isEmpty());        // true (Java 11+)

        // ── GETTING THE VALUE ──────────────────────────────────
        // get() — only if you're SURE there's a value
        String name = withValue.get();   // "Rashid"
        // empty.get();   // ❌ NoSuchElementException — don't do this!

        // orElse() — provide a default if empty (always evaluated)
        String result1 = empty.orElse("Default Name");
        System.out.println(result1);  // Default Name

        // orElseGet() — provide default via Supplier (LAZY — only if empty)
        String result2 = empty.orElseGet(() -> "Computed Default");
        System.out.println(result2);  // Computed Default

        // orElseThrow() — throw exception if empty
        try {
            String result3 = empty.orElseThrow(
                    () -> new RuntimeException("User not found!"));
        } catch (RuntimeException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        // ── CONDITIONAL ACTIONS ───────────────────────────────
        // ifPresent() — run action only if value exists
        withValue.ifPresent(n -> System.out.println("Hello, " + n));
        empty.ifPresent(n -> System.out.println("This never runs"));

        // ifPresentOrElse() — Java 9+ — handle both cases
        withValue.ifPresentOrElse(
                n -> System.out.println("Found: " + n),
                () -> System.out.println("Not found")
        );
        empty.ifPresentOrElse(
                n -> System.out.println("Found: " + n),
                () -> System.out.println("Not found")   // ← this runs
        );

        // ── TRANSFORMING OPTIONAL ──────────────────────────────

        // map() — transform the value if present
        Optional<Integer> nameLength = withValue.map(String::length);
        System.out.println(nameLength.orElse(0));  // 6

        Optional<String> upper = withValue.map(String::toUpperCase);
        System.out.println(upper.orElse("EMPTY")); // RASHID

        // empty optional stays empty after map
        Optional<Integer> emptyLen = empty.map(String::length);
        System.out.println(emptyLen.isPresent());  // false

        // filter() — keep value only if it passes condition
        Optional<String> longName = withValue.filter(n -> n.length() > 3);
        System.out.println(longName.isPresent());  // true (Rashid has 6 chars)

        Optional<String> shortName = withValue.filter(n -> n.length() > 10);
        System.out.println(shortName.isPresent()); // false — filtered out

        // flatMap() — when map returns Optional<Optional<T>>, flatMap flattens it
        Optional<String> email = findUser(1)
                .flatMap(u -> Optional.ofNullable(u.email()));
        System.out.println("Email: " + email.orElse("No email"));

        // ── CHAINING OPTIONALS — REAL WORLD PATTERN ───────────
        // Classic null-safe chain: user → address → city
        // Without Optional (old way — ugly):
        // if (user != null && user.email() != null) { ... }

        // With Optional (clean):
        String emailDomain = findUser(1)
                .map(User::email)
                .filter(e -> e.contains("@"))
                .map(e -> e.split("@")[1])
                .orElse("unknown domain");
        System.out.println("Email domain: " + emailDomain);

        // ── OPTIONAL WITH STREAMS ─────────────────────────────
        List<User> users = List.of(
                new User(1, "Rashid", "rashid@example.com", "9876543210"),
                new User(2, "Ali",    "ali@example.com",    null),
                new User(3, "Sara",   null,                 "9123456789")
        );

        // Find first user with email containing "example"
        Optional<User> found = users.stream()
                .filter(u -> u.email() != null && u.email().contains("example"))
                .findFirst();
        found.ifPresent(u -> System.out.println("Found: " + u.name()));

        // Java 9+: Optional.stream() — convert to 0 or 1 element stream
        // Useful for flatMapping a Stream<Optional<T>>
        List<String> emails = users.stream()
                .map(User::email)
                .map(Optional::ofNullable)   // null → empty Optional
                .flatMap(Optional::stream)   // empty → nothing, present → element
                .collect(Collectors.toList());
        System.out.println("Non-null emails: " + emails);
        // [rashid@example.com, ali@example.com] — Sara's null is gone

        // ── or() — Java 9+ — provide alternative Optional ─────
        Optional<User> userOrGuest = findUser(99)  // not found → empty
                .or(() -> Optional.of(new User(0, "Guest", null, null)));
        System.out.println("User: " + userOrGuest.get().name());  // Guest
    }

    // Simulated repository method — returns Optional
    static Optional<User> findUser(int id) {
        Map<Integer, User> db = Map.of(
                1, new User(1, "Rashid", "rashid@example.com", "9876543210"),
                2, new User(2, "Ali",    "ali@example.com",    null)
        );
        return Optional.ofNullable(db.get(id));  // null if not found
    }
}