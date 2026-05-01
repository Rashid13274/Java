package Phase_4_Exceptional_Handling_And_Input_Output;

// ─────────────────────────────────────────────────────────────
// PHASE 4 — L9: Real-World Patterns
//
// Patterns used in actual Spring Boot backends.
// Understanding these before learning Spring Boot will make
// the @ExceptionHandler and @ControllerAdvice concepts click
// immediately when you encounter them.
// ─────────────────────────────────────────────────────────────

import java.io.*;
import java.util.*;
import java.util.logging.*;

// ── PATTERN 1: RESULT WRAPPER ─────────────────────────────────
// Instead of throwing exceptions for expected failures,
// return a typed Result object — like Rust's Result<T,E>.
// Clean alternative for validation results, optional lookups.
class Result<T> {

    private final T       data;
    private final String  error;
    private final boolean success;

    // Private constructors — use factory methods below
    private Result(T data, String error, boolean success) {
        this.data    = data;
        this.error   = error;
        this.success = success;
    }

    // Factory methods — clear intent
    public static <T> Result<T> ok(T data) {
        return new Result<>(data, null, true);
    }

    public static <T> Result<T> fail(String error) {
        return new Result<>(null, error, false);
    }

    public boolean isSuccess()  { return success; }
    public T       getData()    { return data; }
    public String  getError()   { return error; }

    @Override
    public String toString() {
        return success ? "OK[" + data + "]" : "FAIL[" + error + "]";
    }
}

// ── PATTERN 2: SERVICE LAYER EXCEPTION HANDLING ───────────────
// Clean separation: service throws domain exceptions,
// controller catches and maps to HTTP responses.
class ProductService {

    private final Map<Integer, String> products = new HashMap<>();

    public ProductService() {
        products.put(1, "Java Book");
        products.put(2, "Spring Course");
        products.put(3, "MySQL Guide");
    }

    // Throws specific domain exception — controller maps to 404
    public String getProduct(int id) {
        String product = products.get(id);
        if (product == null) {
            throw new ResourceNotFoundException("Product", id);
        }
        return product;
    }

    // Returns Result — no exception needed for expected failure cases
    public Result<String> findProduct(String name) {
        return products.values().stream()
                .filter(p -> p.equalsIgnoreCase(name))
                .findFirst()
                .map(Result::ok)
                .orElse(Result.fail("Product not found: " + name));
    }

    // Validates and throws ValidationException
    public void createProduct(int id, String name) {
        if (id <= 0)                           throw new ValidationException("id",   "must be positive");
        if (name == null || name.isBlank())    throw new ValidationException("name", "cannot be empty");
        if (products.containsKey(id))          throw new IllegalStateException("Product ID " + id + " already exists");

        products.put(id, name.trim());
        System.out.println("Created: [" + id + "] " + name);
    }
}

// ── PATTERN 3: RETRY LOGIC ────────────────────────────────────
// Retry a failing operation N times before giving up.
// Common for: network calls, DB connections, external APIs.
class RetryUtil {

    public static <T> T retry(
            int maxAttempts,
            long delayMs,
            java.util.concurrent.Callable<T> operation) throws Exception {

        Exception lastException = null;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                System.out.println("  Attempt " + attempt + "...");
                return operation.call();   // try the operation

            } catch (Exception e) {
                lastException = e;
                System.out.println("  Attempt " + attempt + " failed: " + e.getMessage());

                if (attempt < maxAttempts) {
                    try {
                        Thread.sleep(delayMs);  // wait before retry
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();  // restore interrupt status
                        throw ie;
                    }
                }
            }
        }

        // All attempts failed — throw the last exception
        throw new RuntimeException("All " + maxAttempts + " attempts failed", lastException);
    }
}

// ── PATTERN 4: LOGGING EXCEPTIONS PROPERLY ────────────────────
// Never use System.out.println for exceptions in real projects.
// Use a logger — java.util.logging (built-in) or SLF4J+Logback (preferred).
class LoggingDemo {

    // Standard: use Logger per class
    private static final Logger logger = Logger.getLogger(LoggingDemo.class.getName());

    public static void processRequest(String input) {
        logger.info("Processing request: " + input);   // INFO level

        try {
            if (input == null) {
                throw new IllegalArgumentException("Input cannot be null");
            }
            int result = Integer.parseInt(input) * 2;
            logger.info("Result: " + result);

        } catch (NumberFormatException e) {
            // Log the full stack trace with WARNING
            logger.warning("Invalid input format: " + e.getMessage());

        } catch (Exception e) {
            // Log unexpected errors with SEVERE + full stack trace
            logger.log(Level.SEVERE, "Unexpected error processing: " + input, e);
        }
    }
}

// ── PATTERN 5: DEFENSIVE PROGRAMMING ─────────────────────────
// Validate inputs at the start of methods (fail-fast).
// Use Objects.requireNonNull, explicit checks.
class OrderService {

    public double calculateTotal(List<Double> prices, double taxRate) {
        // Validate inputs BEFORE doing any work — fail fast
        Objects.requireNonNull(prices, "Prices list cannot be null");

        if (prices.isEmpty()) {
            throw new IllegalArgumentException("Prices list cannot be empty");
        }
        if (taxRate < 0 || taxRate > 1) {
            throw new IllegalArgumentException("Tax rate must be between 0 and 1, got: " + taxRate);
        }

        double subtotal = prices.stream().mapToDouble(Double::doubleValue).sum();
        double tax      = subtotal * taxRate;
        return subtotal + tax;
    }
}

public class RealWorldPatterns {
    public static void main(String[] args) throws Exception {

        // ── Pattern 1: Result wrapper ──────────────────────────
        ProductService svc = new ProductService();
        System.out.println("=== Result Pattern ===");
        Result<String> found    = svc.findProduct("Java Book");
        Result<String> notFound = svc.findProduct("Rust Book");
        System.out.println(found);     // OK[Java Book]
        System.out.println(notFound);  // FAIL[Product not found: Rust Book]

        if (found.isSuccess()) System.out.println("Got: " + found.getData());

        // ── Pattern 2: Service exception handling ──────────────
        System.out.println("\n=== Service Exceptions ===");
        try { svc.getProduct(99); }
        catch (ResourceNotFoundException e) { System.out.println(e.getMessage()); }

        try { svc.createProduct(-1, "Bad Product"); }
        catch (ValidationException e) {
            System.out.println("Field: " + e.getField() + " | " + e.getMessage());
        }

        svc.createProduct(4, "Kafka Guide");

        // ── Pattern 3: Retry logic ─────────────────────────────
        System.out.println("\n=== Retry Pattern ===");
        int[] callCount = {0};  // effectively final array trick
        try {
            String result = RetryUtil.retry(3, 100, () -> {
                callCount[0]++;
                if (callCount[0] < 3) throw new IOException("Simulated network error");
                return "Success on attempt " + callCount[0];
            });
            System.out.println("Final result: " + result);
        } catch (Exception e) {
            System.out.println("All retries failed: " + e.getMessage());
        }

        // ── Pattern 4: Defensive programming ──────────────────
        System.out.println("\n=== Defensive Programming ===");
        OrderService orderSvc = new OrderService();
        try {
            double total = orderSvc.calculateTotal(List.of(100.0, 200.0, 300.0), 0.18);
            System.out.printf("Total with 18%% GST: ₹%.2f%n", total);
        } catch (IllegalArgumentException e) {
            System.out.println("Bad input: " + e.getMessage());
        }

        try { orderSvc.calculateTotal(null, 0.18); }
        catch (NullPointerException e) { System.out.println("Null check: " + e.getMessage()); }

        try { orderSvc.calculateTotal(List.of(100.0), 1.5); }
        catch (IllegalArgumentException e) { System.out.println("Rate check: " + e.getMessage()); }
    }
    
}
