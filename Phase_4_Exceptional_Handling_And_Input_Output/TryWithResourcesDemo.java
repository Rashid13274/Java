package Phase_4_Exceptional_Handling_And_Input_Output;

// ─────────────────────────────────────────────────────────────
// PHASE 4 — L4: try-with-resources (Java 7+)
//
// Resources (files, DB connections, streams, sockets) MUST be
// closed after use — even if an exception occurs.
//
// Old way (verbose, error-prone):
//   FileReader fr = null;
//   try {
//       fr = new FileReader("file.txt");
//       ...
//   } finally {
//       if (fr != null) fr.close();  // easy to forget!
//   }
//
// New way (try-with-resources):
//   try (FileReader fr = new FileReader("file.txt")) {
//       ...
//   }  // ← fr.close() called AUTOMATICALLY — even if exception thrown
//
// Any class implementing AutoCloseable can be used this way.
// BufferedReader, FileWriter, Connection, ResultSet — all support it.
//
// Node.js parallel: similar to using 'using' in C# or context managers
// (with statement) in Python. JS doesn't have a direct equivalent.
// ─────────────────────────────────────────────────────────────

import java.io.*;
// import java.util.Scanner;

public class TryWithResourcesDemo {

    // ── BASIC try-with-resources ───────────────────────────────
    public static void readFileBasic(String path) {
        System.out.println("=== Reading: " + path);

        // FileReader is declared in () after 'try'
        // close() is called automatically when try block exits
        try (FileReader fr = new FileReader(path)) {

            int ch;
            while ((ch = fr.read()) != -1) {  // read one char at a time
                System.out.print((char) ch);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Read error: " + e.getMessage());
        }
        // fr.close() automatically called here — guaranteed!
    }

    // ── BUFFERED READER — efficient line-by-line reading ───────
    public static void readFileBuffered(String path) throws IOException {
        // Chain resources: FileReader inside BufferedReader
        // Both will be auto-closed (in reverse order: BufferedReader first)
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line;
            int lineNum = 1;

            while ((line = br.readLine()) != null) {   // readLine() returns null at EOF
                System.out.printf("[%3d] %s%n", lineNum++, line);
            }
        }
        // No finally needed — br and fr both auto-closed
    }

    // ── MULTIPLE RESOURCES in one try ─────────────────────────
    // Closed in REVERSE order: resource2 first, then resource1
    public static void copyFile(String source, String dest) throws IOException {
        System.out.println("Copying " + source + " → " + dest);

        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(dest))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();   // write OS-appropriate line separator
            }
            System.out.println("Copy complete.");
        }
        // writer.close() called first, then reader.close() — automatic!
    }

    // ── CUSTOM AutoCloseable CLASS ─────────────────────────────
    // Any class implementing AutoCloseable works with try-with-resources
    // This is how JDBC Connection, PreparedStatement, etc. work
    static class DatabaseConnection implements AutoCloseable {

        private final String dbName;
        private boolean isOpen;

        public DatabaseConnection(String dbName) {
            this.dbName = dbName;
            this.isOpen = true;
            System.out.println("DB Connected: " + dbName);
        }

        public void query(String sql) {
            if (!isOpen) throw new IllegalStateException("Connection is closed!");
            System.out.println("Executing: " + sql);
        }

        @Override
        public void close() {
            // This runs automatically when try block exits
            isOpen = false;
            System.out.println("DB Connection closed: " + dbName);
        }
    }

    // ── SUPPRESSED EXCEPTIONS ─────────────────────────────────
    // If BOTH the try block AND close() throw exceptions,
    // the try block exception is the PRIMARY exception.
    // The close() exception is SUPPRESSED (attached to primary).
    static class BrokenResource implements AutoCloseable {

        @Override
        public void close() throws Exception {
            throw new Exception("Exception during close()");
        }
    }

    public static void suppressedExceptionDemo() {
        try (BrokenResource r = new BrokenResource()) {
            throw new RuntimeException("Exception in try block");

        } catch (Exception e) {
            System.out.println("Primary:    " + e.getMessage());
            // Suppressed exceptions are attached to the primary
            for (Throwable suppressed : e.getSuppressed()) {
                System.out.println("Suppressed: " + suppressed.getMessage());
            }
        }
    }

    public static void main(String[] args) throws IOException {

        // Create a temp file for demo
        File tempFile = File.createTempFile("demo", ".txt");
        tempFile.deleteOnExit();

        // Write something to it first
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
            bw.write("Line 1: Hello from Java");
            bw.newLine();
            bw.write("Line 2: try-with-resources is clean");
            bw.newLine();
            bw.write("Line 3: auto-close is safe");
        }

        // Read it back
        readFileBuffered(tempFile.getAbsolutePath());

        // Custom AutoCloseable
        System.out.println("\n=== Custom AutoCloseable ===");
        try (DatabaseConnection db = new DatabaseConnection("myapp_db")) {
            db.query("SELECT * FROM users");
            db.query("SELECT * FROM orders");
        } catch (Exception e) {
            System.out.println("DB Error: " + e.getMessage());
        }
        // db.close() was automatically called!

        // Suppressed exceptions demo
        System.out.println("\n=== Suppressed Exceptions ===");
        suppressedExceptionDemo();
    }
    
}
