package Phase_4_Exceptional_Handling_And_Input_Output;

// ─────────────────────────────────────────────────────────────
// PHASE 4 — L5: File I/O — Reading and Writing Files
//
// Java has TWO main I/O APIs:
//
// 1. java.io  (classic, stream-based, character/byte oriented)
//      FileReader, FileWriter, BufferedReader, BufferedWriter
//      PrintWriter, FileInputStream, FileOutputStream
//
// 2. java.nio.file (modern, Java 7+, more concise)
//      Files class — utility methods for most file operations
//      Path / Paths — platform-independent file paths
//      Recommended for new code — much cleaner API
//
// Node.js parallel:
//   fs.readFileSync()  → Files.readString()  (Java 11+)
//   fs.writeFileSync() → Files.writeString() (Java 11+)
//   fs.createReadStream() → BufferedReader   (streaming, line by line)
//   path.join()        → Path.of()           (Java 11+)
// ─────────────────────────────────────────────────────────────

import java.io.*;
import java.nio.file.*;
import java.util.List;

public class FileIODemo {

    // ── NIO.FILE — MODERN APPROACH (Recommended) ──────────────

    public static void modernFileOps() throws IOException {
        System.out.println("=== Modern NIO File Operations ===");

        // Create a Path object — platform independent
        Path filePath = Path.of("students.txt");   // Java 11+

        // ── WRITE: write entire string at once ────────────────
        String content = "Rashid,25,Backend\nAli,22,Frontend\nSara,24,DevOps";
        Files.writeString(filePath, content);    // overwrites if exists
        System.out.println("File written.");

        // ── APPEND: add to existing file ──────────────────────
        Files.writeString(filePath, "\nZara,23,Mobile",
                StandardOpenOption.APPEND);      // APPEND flag — don't overwrite
        System.out.println("Line appended.");

        // ── READ: entire file as a String ─────────────────────
        String all = Files.readString(filePath);  // Java 11+
        System.out.println("Full content:\n" + all);

        // ── READ: all lines as a List<String> ─────────────────
        List<String> lines = Files.readAllLines(filePath);
        System.out.println("\nLine count: " + lines.size());
        for (String line : lines) {
            String[] parts = line.split(",");
            System.out.printf("Name: %-10s Age: %s Dept: %s%n",
                    parts[0], parts[1], parts[2]);
        }

        // ── FILE INFO ─────────────────────────────────────────
        System.out.println("\nExists: "      + Files.exists(filePath));
        System.out.println("Size (bytes): " + Files.size(filePath));
        System.out.println("Readable: "     + Files.isReadable(filePath));
        System.out.println("Writeable: "    + Files.isWritable(filePath));

        // ── COPY / MOVE / DELETE ──────────────────────────────
        Path backup = Path.of("students_backup.txt");
        Files.copy(filePath, backup, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Backup created.");

        // Files.move(backup, Path.of("students_archive.txt"));
        // Files.delete(filePath);   // throws if not found
        Files.deleteIfExists(backup); // safer — no exception if missing

        // ── DIRECTORIES ───────────────────────────────────────
        Path logsDir = Path.of("logs", "2024", "december");
        Files.createDirectories(logsDir);   // creates all parent dirs (like mkdir -p)
        System.out.println("Dirs created: " + logsDir);

        // List files in a directory
        Files.list(Path.of("."))
             .filter(p -> p.toString().endsWith(".txt"))
             .forEach(p -> System.out.println("Found: " + p.getFileName()));

        Files.deleteIfExists(filePath);
    }

    // ── CLASSIC I/O — BUFFERED LINE BY LINE ───────────────────
    // Use when: processing LARGE files — don't load all into memory
    // BufferedReader wraps FileReader for efficiency (reads chunks, not char by char)

    public static void classicBufferedIO() throws IOException {
        System.out.println("\n=== Classic Buffered I/O ===");

        // Writing line by line with BufferedWriter
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("log.txt"))) {
            bw.write("2024-01-01 INFO  Server started");  bw.newLine();
            bw.write("2024-01-01 DEBUG Request received"); bw.newLine();
            bw.write("2024-01-01 ERROR DB timeout");       bw.newLine();
            bw.flush();   // force write buffer to disk (optional — close() does it too)
        }

        // Reading line by line — memory efficient for large files
        try (BufferedReader br = new BufferedReader(new FileReader("log.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {   // null = end of file
                if (line.contains("ERROR")) {
                    System.out.println("⚠️  " + line);  // only print errors
                }
            }
        }

        Files.deleteIfExists(Path.of("log.txt"));
    }

    // ── PRINT WRITER — formatted output to file ────────────────
    // Like System.out.println but writes to a file
    public static void printWriterDemo() throws IOException {
        System.out.println("\n=== PrintWriter Demo ===");

        try (PrintWriter pw = new PrintWriter(new FileWriter("report.txt"))) {
            pw.println("=== Sales Report ===");
            pw.printf("Product: %-15s | Units: %4d | Revenue: ₹%8.2f%n",
                    "Java Book", 120, 59880.00);
            pw.printf("Product: %-15s | Units: %4d | Revenue: ₹%8.2f%n",
                    "Spring Course", 85, 127500.00);
            pw.println("====================");
            pw.printf("Total Revenue: ₹%.2f%n", 187380.00);
        }

        // Verify by reading back
        Files.readAllLines(Path.of("report.txt"))
             .forEach(System.out::println);

        Files.deleteIfExists(Path.of("report.txt"));
    }

    // ── BINARY FILE I/O (FileInputStream / FileOutputStream) ───
    // For non-text files: images, PDFs, audio, serialized objects
    public static void binaryFileDemo() throws IOException {
        System.out.println("\n=== Binary I/O ===");

        byte[] data = {72, 101, 108, 108, 111};  // ASCII for "Hello"

        // Write bytes
        try (FileOutputStream fos = new FileOutputStream("binary.dat")) {
            fos.write(data);
        }

        // Read bytes
        try (FileInputStream fis = new FileInputStream("binary.dat")) {
            byte[] buffer = new byte[1024];
            int bytesRead = fis.read(buffer);
            String decoded = new String(buffer, 0, bytesRead);
            System.out.println("Read " + bytesRead + " bytes: " + decoded);
        }

        Files.deleteIfExists(Path.of("binary.dat"));
    }

    public static void main(String[] args) throws IOException {
        modernFileOps();
        classicBufferedIO();
        printWriterDemo();
        binaryFileDemo();
    }
}