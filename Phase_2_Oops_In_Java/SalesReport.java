package Phase_2_Oops_In_Java;

// ─────────────────────────────────────────────────────────────
// JAVA OOP — L7: Interfaces
//
// Interface = pure contract. Defines WHAT a class must do,
// not HOW it does it.
//
// Key rules:
//   - All methods are public abstract by default (no body)
//   - All fields are public static final (constants)
//   - A class can implement MULTIPLE interfaces (unlike extends)
//   - Use 'implements' keyword
//   - Java 8+: can have default methods (with body)
//   - Java 8+: can have static methods
//
// When to use Interface vs Abstract Class:
//   Interface  → defines CAPABILITY ("can do X") — unrelated classes
//   Abstract   → defines IDENTITY  ("is a X")   — related classes
//
// This is how Spring works internally — everything is an Interface!
// e.g., JpaRepository, Serializable, Comparable, Runnable
// ─────────────────────────────────────────────────────────────

// ── INTERFACE 1: Printable ────────────────────────────────────
interface Printable {
    // No 'public abstract' needed — it's implicit
    void print();
    void printSummary(int maxLines);
}

// ── INTERFACE 2: Exportable ───────────────────────────────────
interface Exportable {
    void exportToCSV(String filepath);
    void exportToJSON(String filepath);

    // default method (Java 8+) — has a body, optional to override
    default String getExportFileName(String name) {
        return name + "_" + System.currentTimeMillis() + ".csv";
    }

    // static method in interface — called as Exportable.validate()
    static boolean isValidPath(String path) {
        return path != null && !path.isBlank();
    }
}

// ── INTERFACE 3: Comparable (built-in) ───────────────────────
// Java's built-in interface for sorting
// compareTo() returns:
//   negative → this < other
//   zero     → this == other
//   positive → this > other

// ── CLASS implementing MULTIPLE interfaces ────────────────────
// A Report "is" Printable AND Exportable AND Comparable
// This is multiple inheritance of TYPE — Java allows this for interfaces

public class SalesReport implements Printable, Exportable, Comparable<SalesReport> {

    private String reportName;
    private String department;
    private double totalSales;
    private int    year;

    public SalesReport(String reportName, String department, double totalSales, int year) {
        this.reportName  = reportName;
        this.department  = department;
        this.totalSales  = totalSales;
        this.year        = year;
    }

    // ── Implementing Printable interface ──────────────────────
    @Override
    public void print() {
        System.out.println("=== " + reportName + " ===");
        System.out.println("Department : " + department);
        System.out.printf ("Total Sales: ₹%.2f%n", totalSales);
        System.out.println("Year       : " + year);
    }

    @Override
    public void printSummary(int maxLines) {
        System.out.printf("[%s] ₹%.0f (%d)%n", department, totalSales, year);
    }

    // ── Implementing Exportable interface ─────────────────────
    @Override
    public void exportToCSV(String filepath) {
        System.out.println("Exporting to CSV: " + filepath);
        // Real implementation would write to file
    }

    @Override
    public void exportToJSON(String filepath) {
        System.out.println("Exporting to JSON: " + filepath);
    }

    // ── Implementing Comparable<SalesReport> ──────────────────
    @Override
    public int compareTo(SalesReport other) {
        // Sort by totalSales ascending
        return Double.compare(this.totalSales, other.totalSales);
    }

    public double getTotalSales() { return totalSales; }
}

class L7Main {
    public static void main(String[] args) {

        SalesReport r1 = new SalesReport("Q1 Report", "Backend", 450000, 2024);
        SalesReport r2 = new SalesReport("Q2 Report", "Frontend", 380000, 2024);
        SalesReport r3 = new SalesReport("Q3 Report", "Mobile", 520000, 2024);

        r1.print();
        System.out.println();

        // Interface reference — only sees interface methods
        Printable p = r2;      // SalesReport IS-A Printable
        p.printSummary(3);     // works fine

        Exportable e = r1;     // SalesReport IS-A Exportable
        e.exportToCSV("/reports/q1.csv");

        // Using default method
        String filename = r1.getExportFileName("sales");
        System.out.println("Filename: " + filename);

        // Using static interface method
        System.out.println(Exportable.isValidPath("/output/data.csv")); // true

        // Comparable — sort array of reports by sales
        SalesReport[] reports = {r1, r2, r3};
        java.util.Arrays.sort(reports);   // uses compareTo()
        System.out.println("\n=== Sorted by Sales ===");
        for (SalesReport r : reports) {
            r.printSummary(1);
        }
    }
}