// public Methods & Varargs
// Methods in Java are like functions in JS, but they MUST be inside a class.
// You must specify the return type. Java supports method overloading — same name, different parameters.


public class Chapter_6_Methods_and_Varages {

    // ── METHOD ANATOMY ────────────────────────────────────────────
    // [access] [static?] returnType methodName(params) { ... }
    //
    // In Node.js: function add(a, b) { return a + b; }
    // In Java:    you must declare the TYPE of every param and return value

    public static int add(int a, int b) {
        return a + b;   // return is required if return type is not void
    }

    // void = no return value (like a function that doesn't return)
    public static void printGreeting(String name) {
        System.out.println("Hello, " + name + "!");
    }

    // ── METHOD OVERLOADING ────────────────────────────────────────
    // Same method name, different parameter types/counts
    // Java picks the right one at COMPILE time based on args
    public static double add(double a, double b) {  // ← overloaded add
        return a + b;
    }
    public static String add(String a, String b) {  // ← another overload
        return a + b;                                 // concatenates strings
    }

    // ── VARARGS (Variable Arguments) ─────────────────────────────
    // Like rest params in JS: function sum(...nums) {}
    // In Java: use type... paramName — MUST be last parameter
    public static int sum(int... numbers) {
        int total = 0;
        for (int n : numbers) {   // varargs treated as array inside method
            total += n;
        }
        return total;
    }

    // ── PASSING BY VALUE ──────────────────────────────────────────
    // ⚠️ Java is ALWAYS pass-by-value for primitives
    // Objects pass the reference VALUE (same behavior as JS objects)
    public static void tryModify(int x) {
        x = 999;   // doesn't affect caller — new copy of x
    }

    public static void main(String[] args) {

        // Calling methods
        System.out.println(add(5, 3));         // 8 (int version)
        System.out.println(add(5.5, 3.2));    // 8.7 (double version)
        System.out.println(add("Hi", "!"));   // "Hi!" (String version)

        printGreeting("Rashid");

        // Varargs — pass any number of arguments
        System.out.println(sum(1, 2, 3));         // 6
        System.out.println(sum(10, 20, 30, 40)); // 100
        System.out.println(sum());                // 0 — zero args is valid

        // Pass-by-value demo
        int val = 42;
        tryModify(val);
        System.out.println(val);   // still 42 — primitive not changed
    }
}
