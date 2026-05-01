package Phase_4_Exceptional_Handling_And_Input_Output;

// ─────────────────────────────────────────────────────────────
// PHASE 4 — L1: Exception Hierarchy & Basics
//
// In Node.js: errors are just objects thrown/caught with try/catch.
// In Java   : exceptions are TYPED objects in a strict hierarchy.
//             The compiler ENFORCES handling of certain exceptions.
//
// EXCEPTION HIERARCHY:
//
//   Throwable  ← root of everything throwable
//     ├── Error              ← JVM-level, do NOT catch these
//     │     ├── OutOfMemoryError
//     │     ├── StackOverflowError
//     │     └── VirtualMachineError
//     │
//     └── Exception          ← things your code should handle
//           ├── RuntimeException  ← UNCHECKED (compiler won't force you)
//           │     ├── NullPointerException
//           │     ├── ArrayIndexOutOfBoundsException
//           │     ├── ClassCastException
//           │     ├── ArithmeticException
//           │     ├── NumberFormatException
//           │     ├── IllegalArgumentException
//           │     └── IllegalStateException
//           │
//           └── (other exceptions) ← CHECKED (compiler forces handling)
//                 ├── IOException
//                 ├── SQLException
//                 ├── FileNotFoundException
//                 └── ParseException
//
// CHECKED vs UNCHECKED — the biggest Java-specific concept:
//
//   Checked   → Compiler FORCES you to handle or declare them.
//               You must: catch it  OR  declare 'throws XYZ'
//               Use for: recoverable situations (file not found, DB down)
//
//   Unchecked → Compiler does NOT force handling (RuntimeException).
//               Usually bugs in your code: null access, bad index, etc.
//               Fix the code instead of catching these.
//
// Node.js has no checked exceptions — everything is unchecked.
// This is one of the biggest mental shifts from JS to Java.
// ─────────────────────────────────────────────────────────────

public class ExceptionBasics {
    public static void main(String[] args) {

        // ── UNCHECKED EXCEPTIONS — common ones ────────────────

        // 1. NullPointerException — accessing method/field on null
        try {
            String s = null;
            s.length();   // ❌ NPE — s is null!
        } catch (NullPointerException e) {
            System.out.println("NPE caught: " + e.getMessage());
        }

        // 2. ArrayIndexOutOfBoundsException
        try {
            int[] arr = {1, 2, 3};
            System.out.println(arr[5]);   // ❌ only indices 0,1,2 exist
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array bounds: " + e.getMessage()); // Index 5 out of bounds
        }

        // 3. ClassCastException — wrong type casting
        try {
            Object obj = "I am a String";
            Integer num = (Integer) obj;   // ❌ String cannot be cast to Integer
        } catch (ClassCastException e) {
            System.out.println("Cast failed: " + e.getMessage());
        }

        // 4. NumberFormatException — parsing invalid string to number
        try {
            int n = Integer.parseInt("abc");  // ❌ "abc" is not a valid integer
        } catch (NumberFormatException e) {
            System.out.println("Format error: " + e.getMessage());
        }

        // 5. ArithmeticException — divide by zero
        try {
            int result = 10 / 0;   // ❌ integer division by zero
        } catch (ArithmeticException e) {
            System.out.println("Math error: " + e.getMessage()); // / by zero
        }

        // 6. StackOverflowError — infinite recursion
        // try {
        //     infiniteRecursion();  // would cause StackOverflowError
        // } catch (StackOverflowError e) {
        //     System.out.println("Stack overflow!");  // can catch but shouldn't
        // }

        // ── CHECKED EXCEPTION — must handle or declare ────────
        // FileNotFoundException is CHECKED — compiler forces you to handle it
        try {
            java.io.FileReader fr = new java.io.FileReader("nonexistent.txt");
            // ↑ Without try-catch or 'throws' declaration → COMPILE ERROR
        } catch (java.io.FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        System.out.println("Program continues after exceptions ✅");
    }

    // ── 'throws' declaration — propagate checked exception up ──
    // When you don't want to handle it here, declare it with 'throws'
    // Caller is then responsible for handling it
    public static void readFile(String path) throws java.io.IOException {
        java.io.FileReader fr = new java.io.FileReader(path);
        // No try-catch here — we declared 'throws IOException'
        // The method that calls readFile() must handle it
    }
    
}
