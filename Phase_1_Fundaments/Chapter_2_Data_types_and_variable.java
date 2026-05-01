/*
Primitive Data Types (8 total)

Type     | Size   | Range / Use                  | Example              | JS Equivalent
-------------------------------------------------------------------------------------
byte     | 8-bit  | -128 to 127                 | byte b = 100;       | —
short    | 16-bit | -32768 to 32767             | short s = 5000;     | —
int      | 32-bit | ±2.1 billion                | int age = 25;       | number
long     | 64-bit | Very large integers         | long id = 123L;     | BigInt
float    | 32-bit | Decimal (less precise)      | float f = 3.14f;    | —
double   | 64-bit | Decimal (default)           | double pi = 3.14;   | number
char     | 16-bit | Single Unicode character    | char c = 'A';       | —
boolean  | 1-bit  | true / false                | boolean ok = true;  | boolean
*/
// Java — Variable Declarations

public class Chapter_2_Data_types_and_variable {
    public static void main(String[] args) {

        // ── PRIMITIVE TYPES ──────────────────────────────────────────

        int age = 25;               // most common integer type
        long userId = 9876543210L;   // note the 'L' suffix for long literals
        double salary = 75000.50;   // default for decimals (like JS number)
        float gpa = 3.9f;           // note the 'f' suffix — else compiler error
        boolean isActive = true;    // lowercase true/false (unlike JS's same)
        char grade = 'A';           // single quotes ONLY for char

        // ── STRING (Reference Type, not primitive) ────────────────
        // String is an Object in Java — starts with capital S
        String name = "Rashid";     // double quotes ONLY for String
        String empty = null;        // strings can be null (careful!)

        // ── TYPE INFERENCE with 'var' (Java 10+) ─────────────────
        // var is like 'let' in TypeScript — type is inferred
        var count = 10;              // compiler infers: int
        var message = "Hello";      // compiler infers: String
        var price = 99.99;          // compiler infers: double

        // ── CONSTANTS with 'final' ────────────────────────────────
        // 'final' = const in JS. Convention: UPPER_SNAKE_CASE
        final int MAX_SIZE = 100;
        final String API_URL = "https://api.example.com";

        // ── PRINTING ──────────────────────────────────────────────
        System.out.println("Name: " + name + ", Age: " + age);

        // String.format() — like template literals in JS
        String info = String.format("Name: %s, Salary: %.2f", name, salary);
        System.out.println(info);

        // Text Blocks (Java 15+) — like template literals `...` in JS
        String json = """
                {
                  "name": "Rashid",
                  "role": "developer"
                }
                """;
        System.out.println(json);
    }
}

// ☕ Java — Scope rules


 class ScopeDemo {

    // Class-level variable (field) — like module-level var in Node
    static int counter = 0;

    public static void main(String[] args) {
        int x = 10;         // method scope

        if (x > 5) {
            int y = 20;    // block scope — only available inside this if block
            System.out.println(x + y);  // 30 — x is accessible here
        }
        // System.out.println(y); // ❌ COMPILE ERROR — y out of scope

        for (int i = 0; i < 3; i++) {
            System.out.println(i);   // i is scoped to the for loop
        }
        // System.out.println(i); // ❌ COMPILE ERROR — i out of scope
    }
}