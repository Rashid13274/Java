package Phase_4_Exceptional_Handling_And_Input_Output;

// ─────────────────────────────────────────────────────────────
// PHASE 4 — L8: Scanner & Console Input
//
// Scanner reads input from:
//   - Keyboard (System.in)
//   - File
//   - String
//
// In backend development you rarely use Scanner interactively.
// But it's commonly used in:
//   - CLI tools
//   - Competitive programming
//   - Reading config/data files line by line
//   - Unit tests (reading test data from strings)
//
// Node.js parallel:
//   readline.createInterface({ input: process.stdin })
// ─────────────────────────────────────────────────────────────

import java.util.Scanner;
import java.io.*;

public class ScannerDemo {

    // ── KEYBOARD INPUT ─────────────────────────────────────────
    public static void keyboardInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();    // reads entire line (spaces included)

        System.out.print("Enter your age: ");
        int age = scanner.nextInt();         // reads one integer token

        System.out.print("Enter your GPA: ");
        double gpa = scanner.nextDouble();   // reads one double token

        // ⚠️ COMMON TRAP: after nextInt()/nextDouble(),
        // a '\n' (newline) is left in the buffer.
        // Next nextLine() call reads the leftover '\n' as empty string!
        scanner.nextLine();  // consume the leftover newline — important!

        System.out.print("Enter your city: ");
        String city = scanner.nextLine();    // now works correctly

        System.out.printf("Hello %s! Age: %d, GPA: %.1f, City: %s%n",
                name, age, gpa, city);

        scanner.close();   // close to release the resource
    }

    // ── READING FROM A STRING ─────────────────────────────────
    // Very useful for parsing structured data in unit tests
    public static void scannerFromString() {
        String data = "101 Rashid 85000.50\n102 Ali 72000.00\n103 Sara 90000.75";

        try (Scanner sc = new Scanner(data)) {
            System.out.println("=== Parsing from String ===");
            while (sc.hasNextLine()) {
                int    id     = sc.nextInt();
                String name   = sc.next();
                double salary = sc.nextDouble();
                System.out.printf("ID: %d | Name: %-8s | Salary: ₹%.2f%n",
                        id, name, salary);
            }
        }
    }

    // ── READING FROM A FILE with Scanner ─────────────────────
    public static void scannerFromFile(String filePath) throws FileNotFoundException {
        System.out.println("\n=== Reading file with Scanner ===");

        try (Scanner sc = new Scanner(new File(filePath))) {
            int lineNum = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                lineNum++;
                System.out.printf("[%2d] %s%n", lineNum, line);
            }
        }
    }

    // ── DELIMITER — parse CSV or custom-separated data ────────
    public static void customDelimiter() {
        String csv = "Rashid,25,Backend,85000";

        try (Scanner sc = new Scanner(csv).useDelimiter(",")) {
            String name   = sc.next();
            int    age    = sc.nextInt();
            String dept   = sc.next();
            double salary = sc.nextDouble();
            System.out.printf("Name: %s | Age: %d | Dept: %s | ₹%.0f%n",
                    name, age, dept, salary);
        }
    }

    // ── VALIDATION LOOP — keep asking until valid input ────────
    public static int readPositiveInt(Scanner scanner, String prompt) {
        int value = -1;
        while (value <= 0) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                if (value <= 0) {
                    System.out.println("Must be positive. Try again.");
                }
            } else {
                System.out.println("Not a valid integer. Try again.");
                scanner.next();   // discard invalid token
            }
        }
        return value;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // Comment out keyboardInput() for non-interactive demo
        // keyboardInput();

        scannerFromString();
        customDelimiter();
    }
}