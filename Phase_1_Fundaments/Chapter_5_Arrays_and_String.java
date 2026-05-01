import java.util.Arrays;   // import for utility methods
public class Chapter_5_Arrays_and_String {
    
    public static void main(String[] args) {

        // ── DECLARING & INITIALIZING ──────────────────────────────
        int[] nums = {5, 2, 8, 1, 9};     // inline init
        int[] scores = new int[5];         // size 5, all zeros by default
        String[] names = new String[3];   // all null by default

        // ── ACCESSING & MODIFYING ─────────────────────────────────
        System.out.println(nums[0]);   // 5  — zero-indexed like JS
        nums[0] = 100;                 // modify element
        System.out.println(nums.length); // 5  — .length property (NOT .length())

        // ── ITERATING ─────────────────────────────────────────────
        for (int num : nums) {           // for-each loop
            System.out.print(num + " ");
        }

        // ── SORTING ───────────────────────────────────────────────
        Arrays.sort(nums);              // in-place sort — like .sort() in JS
        System.out.println(Arrays.toString(nums));  // [2, 5, 8, 9, 100]

        // ── SEARCHING ─────────────────────────────────────────────
        // Binary search (array must be sorted first)
        int index = Arrays.binarySearch(nums, 8);  // returns index of 8

        // ── COPYING ───────────────────────────────────────────────
        int[] copy = Arrays.copyOf(nums, nums.length);       // full copy
        int[] slice = Arrays.copyOfRange(nums, 1, 3);         // like .slice(1,3)

        // ── 2D ARRAY (Matrix) ─────────────────────────────────────
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println(matrix[1][2]);   // 6 (row 1, col 2)

        for (int[] row : matrix) {
            for (int cell : row) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }
    }
}


// String Methods (Essential)
// ☕ Java — String API
    class StringDemo {
    public static void main(String[] args) {

        String s = "  Hello, Java World!  ";

        // ── BASICS ────────────────────────────────────────────────
        System.out.println(s.length());           // .length() — note: it IS a method, not property
        System.out.println(s.trim());             // remove whitespace — like .trim() in JS
        System.out.println(s.strip());            // Java 11+ — Unicode-aware trim
        System.out.println(s.toUpperCase());     // HELLO, JAVA WORLD!
        System.out.println(s.toLowerCase());     // hello, java world!
        System.out.println(s.isEmpty());         // false
        System.out.println(s.isBlank());         // false (Java 11+) — true if only whitespace

        // ── SEARCHING ─────────────────────────────────────────────
        System.out.println(s.contains("Java"));      // true  — like .includes() in JS
        System.out.println(s.indexOf("Java"));       // index of first occurrence
        System.out.println(s.startsWith("  Hello")); // true
        System.out.println(s.endsWith("!  "));      // true

        // ── EXTRACTION ────────────────────────────────────────────
        System.out.println(s.charAt(2));             // 'H' — character at index
        System.out.println(s.substring(2, 7));      // "Hello" — like .slice(2,7) in JS

        // ── REPLACING ─────────────────────────────────────────────
        System.out.println(s.replace("Java", "JS"));       // replaceAll occurrences
        System.out.println(s.replaceAll("\\s+", "-"));    // regex replace (like .replace(/\s+/g, '-'))
        System.out.println(s.replaceFirst("\\s", "_")); // replace first match only

        // ── SPLITTING ─────────────────────────────────────────────
        String csv = "a,b,c,d";
        String[] parts = csv.split(",");   // ["a","b","c","d"] — like .split() in JS

        // ── JOINING ───────────────────────────────────────────────
        String joined = String.join(", ", "a", "b", "c");  // "a, b, c"

        // ── COMPARISON ────────────────────────────────────────────
        String a = "apple", b = "banana";
        System.out.println(a.equals(b));            // false — value comparison
        System.out.println(a.equalsIgnoreCase("APPLE")); // true
        System.out.println(a.compareTo(b));          // negative (a < b lexicographically)

        // ── STRING BUILDER — for mutable strings (efficient concatenation) ──
        // ⚠️ Never concatenate strings in a loop with + → use StringBuilder
        // In Node: arr.join('') is efficient. In Java: use StringBuilder
        StringBuilder sb = new StringBuilder();
        sb.append("Hello");
        sb.append(", ");
        sb.append("World");
        sb.insert(5, " Beautiful");  // insert at index
        sb.reverse();                  // reverse the string
        System.out.println(sb.toString());
    }
}