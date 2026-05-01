package Phase_4_Exceptional_Handling_And_Input_Output;

// ─────────────────────────────────────────────────────────────
// PHASE 4 — L5: Interview Question — Exception Handling and I/O



/**
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
 * Q1. What is the difference between Checked and Unchecked exceptions?
ANSWER:

Checked Exception:
  - Subclass of Exception (NOT RuntimeException)
  - Compiler FORCES you to handle or declare with 'throws'
  - Represents RECOVERABLE situations
  - Examples: IOException, SQLException, FileNotFoundException
  - Use when: caller CAN and SHOULD take action on failure

Unchecked Exception:
  - Subclass of RuntimeException
  - Compiler does NOT force handling
  - Usually indicates a programming BUG
  - Examples: NullPointerException, ArrayIndexOutOfBoundsException
  - Use when: caller cannot realistically recover

INTERVIEW ANSWER (crisp):
  "Checked exceptions must be handled at compile time — they
  represent recoverable conditions like file not found or
  network timeout. Unchecked exceptions are programming bugs
  like null access or bad array index — fix the code, not catch them.
  Spring Boot prefers unchecked (RuntimeException) to avoid boilerplate."
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Q2. What is the difference between throw and throws?
// ANSWER:

// 'throws' — declares that a method MAY throw this exception
// Goes in the METHOD SIGNATURE — warning to callers
public void readFile(String path) throws IOException {
    // method body
}

// 'throw' — actually THROWS an exception object (one-time action)
// Goes INSIDE the method body
public void validateAge(int age) {
    if (age < 0) {
        throw new IllegalArgumentException("Age cannot be negative: " + age);
        // ↑ creates and throws the exception RIGHT NOW
    }
}

// KEY POINTS:
// throws → plural, in signature, zero or more exception types
// throw  → singular, in body, exactly one exception at a time
// 'throws' is required for CHECKED exceptions
// 'throws' is optional for UNCHECKED (but good documentation)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Q3. Can we have a try block without a catch block?
// ANSWER: YES — if there is a 'finally' block.

// Valid: try + finally (no catch)
try {
    doSomething();
} finally {
    cleanup();   // always runs, exception propagates to caller
}

// Valid: try-with-resources (no catch, no finally)
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    br.readLine();
}
// Auto-close handles cleanup — catch is optional

// Invalid: try alone (no catch AND no finally) → COMPILE ERROR
// try {
//     doSomething();
// }  ← COMPILE ERROR

// COMMON INTERVIEW TRAP:
// try + finally is valid — exception propagates but finally still runs.
// Use it when you need guaranteed cleanup but want the exception to bubble up.
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Q4. What happens if an exception is thrown in a finally block?
// ANSWER: It SUPPRESSES the original exception — very dangerous!

public static void riskyMethod() {
    try {
        System.out.println("try block");
        throw new RuntimeException("Original exception");

    } finally {
        System.out.println("finally block");
        throw new RuntimeException("Exception in finally");
        // ← This REPLACES the original exception!
        // The original "Original exception" is LOST forever
    }
}

// OUTPUT:
// try block
// finally block
// Exception: Exception in finally  ← original is gone!

// BEST PRACTICES for finally:
//   ✅ Close resources
//   ✅ Reset state / flags
//   ❌ Never throw exceptions from finally
//   ❌ Never return from finally (hides original exception)

// Modern fix: use try-with-resources
// If BOTH the body AND close() throw, the close() exception
// is added as a SUPPRESSED exception — original is preserved.
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Q5. What is Exception Chaining and why is it important?
// ANSWER:
// Exception chaining = wrapping one exception inside another
// to preserve the ORIGINAL CAUSE while converting the type.

// Without chaining — original cause is LOST:
public void badPattern() {
    try {
        callDatabase();
    } catch (SQLException e) {
        throw new RuntimeException("DB failed");
        // ↑ caller sees "DB failed" but doesn't know WHY — no debugging info!
    }
}

// With chaining — original cause is PRESERVED:
public void goodPattern() {
    try {
        callDatabase();
    } catch (SQLException e) {
        throw new RuntimeException("DB operation failed", e);
        //                                                ↑ 'e' is the cause
    }
}

// Caller can inspect the full chain:
try {
    goodPattern();
} catch (RuntimeException e) {
    System.out.println("Error: " + e.getMessage());
    System.out.println("Caused by: " + e.getCause().getMessage()); // original!
    e.printStackTrace();  // prints full chain with stack traces
}

// WHY IT MATTERS:
// In Spring Boot, service layers wrap repository exceptions in
// domain/service exceptions — chaining lets you trace from
// controller → service → repository → SQL in one stack trace.
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Q6. What is try-with-resources and what interface must a class implement to use it?
// ANSWER:
// try-with-resources automatically closes resources when the
// try block exits (normally or via exception).

// REQUIREMENT: The resource class must implement AutoCloseable
// (or its subinterface Closeable, which is the same but declares
//  IOException instead of Exception).

// AutoCloseable interface:
// public interface AutoCloseable {
//     void close() throws Exception;
// }

// Any class with this interface works:
class MyResource implements AutoCloseable {
    public MyResource() { System.out.println("Resource opened"); }

    @Override
    public void close() {
        System.out.println("Resource closed automatically");
    }
}

// Usage:
try (MyResource r = new MyResource()) {
    System.out.println("Using resource");
}   // close() called here automatically

// BENEFITS over manual finally:
//   1. Shorter, cleaner code
//   2. Handles MULTIPLE resources (closed in reverse order)
//   3. Suppressed exceptions preserved (finally loses them)
//   4. Can't forget to close — compiler/language handles it

// Built-in classes that implement AutoCloseable:
//   FileReader, FileWriter, BufferedReader, BufferedWriter
//   InputStream, OutputStream, Scanner
//   Connection, PreparedStatement, ResultSet (JDBC)
//   HttpClient, Socket, ServerSocket

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Q7. What is the difference between final, finally, and finalize()?
ANSWER (classic interview question — must know):

final:
  - KEYWORD — modifier for variables, methods, classes
  - final variable   → value cannot change (constant)
  - final method     → cannot be overridden by subclass
  - final class      → cannot be extended (e.g. String, Integer)

finally:
  - BLOCK — part of try-catch-finally structure
  - Always executes after try (and catch) — even if exception thrown
  - Use for: cleanup, closing resources, logging
  - Exception: System.exit() or JVM crash prevents finally

finalize():
  - METHOD — in Object class, called by GC before object is collected
  - Called when object has no more references (eligible for GC)
  - ⚠️ DEPRECATED in Java 9, REMOVED in Java 18
  - Never rely on it — GC timing is unpredictable
  - Use try-with-resources / close() instead

EASY MEMORY AID:
  final     → can't change it
  finally   → cleanup block, always runs
  finalize  → garbage collector called it (deprecated, forget it)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

  Q8. How do you handle multiple exceptions — what is multi-catch?
  // ANSWER:
// Multi-catch (Java 7+) lets you catch multiple exception
// types in ONE catch block using | (pipe).

// OLD WAY — repetitive:
try {
    riskyOperation();
} catch (NumberFormatException e) {
    handleError(e);
} catch (IllegalArgumentException e) {
    handleError(e);   // same handler for both — code duplication!
} catch (ArrayIndexOutOfBoundsException e) {
    handleError(e);
}

// NEW WAY — multi-catch:
try {
    riskyOperation();
} catch (NumberFormatException | IllegalArgumentException
         | ArrayIndexOutOfBoundsException e) {
    handleError(e);   // one block handles all three
    // 'e' is effectively final here — can't reassign
}

// RULES:
//   Cannot catch parent AND child in same multi-catch:
//   catch (Exception | IOException e)  ← COMPILE ERROR
//   (IOException extends Exception — redundant)

// WHEN TO USE:
//   ✅ Multiple exceptions with the SAME handling logic
//   ❌ Exceptions that need different handling (use separate catch)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Q9. What is the difference between IOException and FileNotFoundException?
ANSWER:

Hierarchy:
  IOException
    └── FileNotFoundException

FileNotFoundException IS-A IOException (it extends it).

FileNotFoundException:
  - Thrown when a file/path does not exist
  - More SPECIFIC — you know exactly what went wrong

IOException:
  - Broader — covers all I/O errors:
    file not found, permission denied, disk full,
    network error, stream closed, etc.

CODE IMPLICATION:
  catch (FileNotFoundException e) → only for missing files
  catch (IOException e) → catches FileNotFoundException too (parent)

BEST PRACTICE:
  Catch SPECIFIC exceptions first, generic ones last.
  catch (FileNotFoundException e) { ... }  // specific first
  catch (IOException e) { ... }           // generic second
  // Reverse order → COMPILE ERROR (unreachable catch)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━


  Q10. Design a custom exception hierarchy for a banking application.
  // ANSWER: This tests OOP + exception design skills together.

// ROOT — all banking exceptions extend this
class BankingException extends RuntimeException {
    private final String errorCode;

    public BankingException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    public String getErrorCode() { return errorCode; }
}

// SPECIFIC EXCEPTIONS
class AccountNotFoundException extends BankingException {
    public AccountNotFoundException(String accountNo) {
        super("Account not found: " + accountNo, "ACCT_001");
    }
}

class InsufficientFundsException extends BankingException {
    private final double shortfall;
    public InsufficientFundsException(double amount, double balance) {
        super(String.format("Need ₹%.2f more", amount - balance), "TXN_001");
        this.shortfall = amount - balance;
    }
    public double getShortfall() { return shortfall; }
}

class DailyLimitExceededException extends BankingException {
    public DailyLimitExceededException(double limit) {
        super("Daily limit of ₹" + limit + " exceeded", "TXN_002");
    }
}

class AccountFrozenException extends BankingException {
    public AccountFrozenException(String reason) {
        super("Account frozen: " + reason, "ACCT_002");
    }
}

// USAGE IN SERVICE:
// throw new InsufficientFundsException(amount, balance);

// IN SPRING BOOT CONTROLLER ADVICE:
// @ExceptionHandler(BankingException.class)
// public ResponseEntity<ErrorResponse> handleBanking(BankingException e) {
//     return ResponseEntity.badRequest()
//            .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
// }

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
 */
