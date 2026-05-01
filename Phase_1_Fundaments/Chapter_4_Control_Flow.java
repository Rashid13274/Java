// public ☕ Java — if/else & switch
public class Chapter_4_Control_Flow {
    public static void main(String[] args) {

        // ── IF / ELSE IF / ELSE ───────────────────────────────────
        int score = 75;

        if (score >= 90) {
            System.out.println("Grade: A");
        } else if (score >= 80) {
            System.out.println("Grade: B");
        } else if (score >= 70) {
            System.out.println("Grade: C");  // ← this runs
        } else {
            System.out.println("Grade: F");
        }

        // ── TRADITIONAL SWITCH ────────────────────────────────────
        String day = "MONDAY";
        switch (day) {
            case "MONDAY":
            case "TUESDAY":
                System.out.println("Weekday");
                break;        // ⚠️ don't forget break! fall-through is default
            case "SATURDAY":
            case "SUNDAY":
                System.out.println("Weekend");
                break;
            default:
                System.out.println("Midweek");
        }

        // ── SWITCH EXPRESSION (Java 14+) — cleaner syntax ─────────
        // No break needed, no fall-through, returns a value
        String type = switch (day) {
            case "MONDAY", "TUESDAY", "WEDNESDAY",
                 "THURSDAY", "FRIDAY"  -> "Weekday";
            case "SATURDAY", "SUNDAY"  -> "Weekend";
            default                   -> "Unknown";
        };
        System.out.println(type);  // "Weekday"
    }
}

// ☕ Java — All Loop Types

    class LoopsDemo {
    public static void main(String[] args) {

        // ── FOR LOOP — same as JS ──────────────────────────────────
        for (int i = 0; i < 5; i++) {
            System.out.print(i + " ");  // 0 1 2 3 4
        }

        // ── ENHANCED FOR-EACH (for arrays/collections) ───────────
        // Like for...of in JS  →  for (let item of arr)
        int[] numbers = {10, 20, 30, 40, 50};
        for (int num : numbers) {     // "for each num in numbers"
            System.out.print(num + " ");
        }

        // ── WHILE LOOP — same as JS ────────────────────────────────
        int count = 0;
        while (count < 3) {
            System.out.println("Count: " + count);
            count++;
        }

        // ── DO-WHILE — runs at least once ─────────────────────────
        int x = 10;
        do {
            System.out.println("Runs once even if condition is false");
            x++;
        } while (x < 5);  // condition is false, but body runs once

        // ── BREAK & CONTINUE ──────────────────────────────────────
        for (int i = 0; i < 10; i++) {
            if (i == 3) continue;  // skip 3
            if (i == 6) break;     // stop at 6
            System.out.print(i + " ");  // 0 1 2 4 5
        }

        // ── LABELED BREAK (for nested loops) ──────────────────────
        // Java has labeled break — useful to break outer loops
        outer:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) break outer;  // breaks outer loop
                System.out.println(i + "," + j);
            }
        }
    }
}
