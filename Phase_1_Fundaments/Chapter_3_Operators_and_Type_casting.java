// ☕ Java — All Operators

public class Chapter_3_Operators_and_Type_casting {
    public static void main(String[] args) {

        // ── ARITHMETIC ────────────────────────────────────────────
        int a = 10, b = 3;
        System.out.println(a + b);   // 13
        System.out.println(a - b);   // 7
        System.out.println(a * b);   // 30
        System.out.println(a / b);   // 3  ← INTEGER division! (not 3.33)
        System.out.println(a % b);   // 1  ← modulo (same as JS)

        // To get decimal: cast one operand to double
        System.out.println((double) a / b);  // 3.3333...

        // ── ASSIGNMENT SHORTCUTS ──────────────────────────────────
        int x = 5;
        x += 3;   // x = x + 3 = 8
        x -= 1;   // x = 7
        x *= 2;   // x = 14
        x /= 2;   // x = 7
        x++;       // increment: x = 8
        x--;       // decrement: x = 7

        // ── COMPARISON ────────────────────────────────────────────
        // Java has only == (no === like JS). But == on Objects is tricky!
        System.out.println(a == b);   // false
        System.out.println(a != b);   // true
        System.out.println(a > b);    // true
        System.out.println(a >= b);   // true

        // ⚠️ CRITICAL: For Strings, never use == (compares reference!)
        String s1 = "hello";
        String s2 = "hello";
        System.out.println(s1 == s2);        // true (by luck — string pool)
        System.out.println(s1.equals(s2));  // true (ALWAYS use .equals() for Strings)

        String s3 = new String("hello");
        System.out.println(s1 == s3);        // FALSE! Different object reference
        System.out.println(s1.equals(s3));  // true — value comparison ✅

        // ── LOGICAL ───────────────────────────────────────────────
        boolean t = true, f = false;
        System.out.println(t && f);   // AND → false (same as JS)
        System.out.println(t || f);   // OR  → true  (same as JS)
        System.out.println(!t);      // NOT → false (same as JS)

        // ── TERNARY ───────────────────────────────────────────────
        int age = 20;
        String status = (age >= 18) ? "Adult" : "Minor";  // same as JS

        // ── INSTANCEOF ────────────────────────────────────────────
        // Check if object is of a type — like typeof/instanceof in JS
        Object obj = "I am a string";
        System.out.println(obj instanceof String);  // true
    }
}
// Type Casting
// ☕ Java — Widening & Narrowing Casting
    class CastingDemo {
    public static void main(String[] args) {

        // ── WIDENING (Implicit) — smaller → larger type, auto-done ──
        // byte → short → int → long → float → double
        int i = 100;
        long l = i;       // ✅ automatic — int fits in long
        double d = i;     // ✅ automatic — int fits in double

        // ── NARROWING (Explicit) — larger → smaller, manual cast required ──
        double pi = 3.14159;
        int approx = (int) pi;   // explicit cast — truncates decimal → 3
        System.out.println(approx);  // 3 (not rounded, just truncated)

        long big = 999L;
        int small = (int) big;  // ⚠️ risky if big > Integer.MAX_VALUE

        // ── STRING ↔ PRIMITIVE CONVERSION ────────────────────────
        // String → int  (like parseInt in JS)
        String numStr = "42";
        int parsed = Integer.parseInt(numStr);   // → 42

        // int → String (like .toString() in JS)
        String str = String.valueOf(42);    // "42"
        String str2 = Integer.toString(42); // "42" — same result

        // double → String
        double price = 99.99;
        String priceStr = Double.toString(price);   // "99.99"
    }
}