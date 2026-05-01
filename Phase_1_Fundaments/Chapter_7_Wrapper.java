import java.util.ArrayList;
// Java has Object versions of every primitive. 
// They're needed when working with Collections (which can't hold primitives). 
// Java auto-converts between them — called autoboxing.
public class Chapter_7_Wrapper {
    public static void main(String[] args) {

        // ── AUTOBOXING — primitive → Wrapper (automatic) ──────────
        Integer boxed = 42;       // int 42 auto-boxed to Integer object
        Double d = 3.14;          // double auto-boxed to Double object

        // ── UNBOXING — Wrapper → primitive (automatic) ────────────
        int raw = boxed;           // Integer auto-unboxed to int
        int val = boxed + 10;     // unboxed for arithmetic

        // ── WHY NEEDED: Collections can't hold primitives ──────────
        // ❌ ArrayList<int> nums = ...  — COMPILE ERROR
        // ✅ ArrayList<Integer> nums = ...  — Wrapper required
        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(10);   // 10 is auto-boxed to Integer
        nums.add(20);
        int first = nums.get(0);  // auto-unboxed to int

        // ── USEFUL STATIC METHODS ─────────────────────────────────
        int parsed = Integer.parseInt("123");    // "123" → 123
        double dp = Double.parseDouble("3.14"); // "3.14" → 3.14

        System.out.println(Integer.MAX_VALUE);   // 2147483647
        System.out.println(Integer.MIN_VALUE);   // -2147483648
        System.out.println(Integer.toBinaryString(10)); // "1010"
        System.out.println(Integer.toHexString(255));   // "ff"

        // ── CHARACTER UTILITIES ───────────────────────────────────
        char ch = 'A';
        System.out.println(Character.isLetter(ch));   // true
        System.out.println(Character.isDigit(ch));    // false
        System.out.println(Character.toLowerCase(ch)); // 'a'

        // ── ⚠️ AUTOBOXING TRAP: == on Integer ─────────────────────
        Integer x = 127;
        Integer y = 127;
        System.out.println(x == y);  // true (cached range: -128 to 127)

        Integer a = 200;
        Integer b = 200;
        System.out.println(a == b);        // FALSE! Outside cache range
        System.out.println(a.equals(b));  // true — always use .equals() for Wrappers!
    }
}
