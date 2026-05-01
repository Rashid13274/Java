package Phase_4_Exceptional_Handling_And_Input_Output;

// ─────────────────────────────────────────────────────────────
// PHASE 4 — L2: try / catch / finally / multi-catch
//
// try     → code that might throw an exception
// catch   → handles a specific exception type
// finally → ALWAYS runs — even if exception thrown or not
//           even if there's a return inside try!
// multi-catch → catch multiple types in one block (Java 7+)
//
// Node.js parallel:
//   try { } catch(e) { } finally { }  ← same structure!
//   BUT in Java, catch is TYPED — you specify what to catch.
// ─────────────────────────────────────────────────────────────

// import java.io.*;
import java.sql.*;

public class TryCatchDemo {

    // ── BASIC try-catch-finally ───────────────────────────────
    public static void basicExample() {
        System.out.println("=== Basic try-catch-finally ===");

        try {
            System.out.println("try: start");
            int result = 10 / 0;           // throws ArithmeticException
            System.out.println("try: this line never runs");

        } catch (ArithmeticException e) {
            // 'e' is the exception object — has getMessage(), printStackTrace()
            System.out.println("catch: " + e.getMessage());  // / by zero

        } finally {
            // ALWAYS runs — connection close, file close, cleanup goes here
            System.out.println("finally: always runs");
        }

        System.out.println("After try-catch");
    }

    // ── MULTIPLE CATCH BLOCKS ─────────────────────────────────
    // Catch from MOST specific to LEAST specific
    // If you put parent class first → child catch blocks are unreachable (compile error)
    public static void multiCatch(String input, int index) {
        System.out.println("\n=== Multiple Catch Blocks ===");

        try {
            int[] arr = {1, 2, 3};
            int num  = Integer.parseInt(input);    // may throw NumberFormatException
            int val  = arr[index];                 // may throw ArrayIndexOutOfBoundsException
            int res  = 100 / num;                  // may throw ArithmeticException
            System.out.println("Result: " + res);

        } catch (NumberFormatException e) {
            // Most specific first — handles "abc" as input
            System.out.println("Invalid number format: " + e.getMessage());

        } catch (ArrayIndexOutOfBoundsException e) {
            // Handles bad index
            System.out.println("Bad array index: " + e.getMessage());

        } catch (ArithmeticException e) {
            // Handles divide by zero
            System.out.println("Math error: " + e.getMessage());

        } catch (Exception e) {
            // Catch-all — handles anything not caught above
            // Put this LAST — it's the parent of all exceptions
            System.out.println("Unexpected error: " + e.getMessage());

        } finally {
            System.out.println("Cleanup done");
        }
    }

    // ── MULTI-CATCH (Java 7+) — one block, multiple types ─────
    // When you handle multiple exceptions the SAME WAY
    // Saves code duplication — use | (pipe) to combine types
    public static void multiCatchSingleBlock(String input) {
        System.out.println("\n=== Multi-Catch ===");

        try {
            int num = Integer.parseInt(input);
            String[] arr = {"a", "b"};
            System.out.println(arr[num]);

        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            // Handle both the same way — 'e' is effectively final here
            System.out.println("Input error: " + e.getClass().getSimpleName()
                               + " — " + e.getMessage());
        }
    }

    // ── FINALLY WITH RETURN — tricky behavior ─────────────────
    // finally runs BEFORE the return value is sent to caller
    // If finally also has a return — it OVERRIDES the try's return!
    public static int finallyWithReturn() {
        try {
            System.out.println("\ntry block running...");
            return 1;     // this return is "remembered" but finally runs first

        } finally {
            System.out.println("finally runs before actual return");
            // ⚠️ return 2 here would override the return 1 above!
            // Avoid return/throw in finally — it hides original exception
        }
    }

    // ── EXCEPTION CHAINING — wrapping one exception in another ─
    // Preserves the ORIGINAL cause while converting exception type
    // Very common in service layers: catch DB exception, throw domain exception
    public static void serviceMethod() throws RuntimeException {
        try {
            // Simulate a DB operation failing
            throw new SQLException("Connection timeout");

        } catch (SQLException e) {
            // Wrap the low-level SQLException in a domain-level exception
            // 'e' is the CAUSE — preserved in the chain
            throw new RuntimeException("Database operation failed", e);
        }
    }

    // ── RETHROWING AN EXCEPTION ───────────────────────────────
    // Catch, log, then rethrow for caller to handle
    public static void processData(String data) throws IllegalArgumentException {
        try {
            if (data == null || data.isBlank()) {
                throw new IllegalArgumentException("Data cannot be null or blank");
            }
            System.out.println("Processing: " + data);

        } catch (IllegalArgumentException e) {
            System.out.println("Logging error: " + e.getMessage()); // log it
            throw e;   // rethrow — let caller handle it too
        }
    }

    public static void main(String[] args) {
        basicExample();

        multiCatch("abc", 0);    // NumberFormatException
        multiCatch("0",   0);    // ArithmeticException (div by 0)
        multiCatch("2",   5);    // ArrayIndexOutOfBoundsException

        multiCatchSingleBlock("xyz");
        multiCatchSingleBlock("10");

        System.out.println("\nfinallyWithReturn returned: " + finallyWithReturn());

        // Exception chaining
        try {
            serviceMethod();
        } catch (RuntimeException e) {
            System.out.println("\nCaught: " + e.getMessage());
            System.out.println("Caused by: " + e.getCause().getMessage());
        }
    }
    
}
