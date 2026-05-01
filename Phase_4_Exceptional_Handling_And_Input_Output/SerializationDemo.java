package Phase_4_Exceptional_Handling_And_Input_Output;

// ─────────────────────────────────────────────────────────────
// PHASE 4 — L6: Serialization & Object I/O
//
// Serialization = converting a Java object into a byte stream
//                 so it can be saved to a file or sent over network.
// Deserialization = reading the byte stream back into a Java object.
//
// To serialize a class:
//   1. implement Serializable (marker interface — no methods!)
//   2. Add serialVersionUID (version control for deserialization)
//   3. Use ObjectOutputStream to write, ObjectInputStream to read
//
// Fields marked 'transient' are SKIPPED during serialization.
// Static fields are NOT serialized (they belong to the class, not instance).
//
// In real projects: Serialization is used for:
//   - Session storage (HTTP sessions)
//   - Caching objects (Redis Java serialization)
//   - Message queues (Kafka with Java serializers)
//   - Deep copying objects
//
// Modern alternative: Convert to JSON (Jackson/Gson) for portability.
// ─────────────────────────────────────────────────────────────

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// ── SERIALIZABLE CLASS ────────────────────────────────────────
// Must implement Serializable — marker interface (no methods)
class Employee implements Serializable {

    // serialVersionUID — version number for this class
    // If class changes and UID doesn't match → InvalidClassException during deserialization
    // Generate with: IntelliJ → right click field → Generate serialVersionUID
    private static final long serialVersionUID = 1L;

    private int    id;
    private String name;
    private String department;
    private double salary;

    // 'transient' = excluded from serialization
    // Use for: passwords, sensitive data, derived/computed fields
    private transient String password;

    // 'static' fields are also NOT serialized (belong to class, not object)
    private static int instanceCount = 0;

    public Employee(int id, String name, String department,
                    double salary, String password) {
        this.id         = id;
        this.name       = name;
        this.department = department;
        this.salary     = salary;
        this.password   = password;
        instanceCount++;
    }

    // Getters
    public int    getId()         { return id; }
    public String getName()       { return name; }
    public String getDepartment() { return department; }
    public double getSalary()     { return salary; }
    public String getPassword()   { return password; }  // will be null after deserialization

    @Override
    public String toString() {
        return String.format("Employee[%d | %s | %s | ₹%.0f | pwd=%s]",
                id, name, department, salary, password);
    }
}

public class SerializationDemo {

    private static final String FILE_PATH = "employees.ser";

    // ── SERIALIZE: Object → file ──────────────────────────────
    public static void serializeObject(Employee emp) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(FILE_PATH))) {

            oos.writeObject(emp);   // converts object to byte stream → file
            System.out.println("Serialized: " + emp);
        }
    }

    // ── DESERIALIZE: file → Object ────────────────────────────
    public static Employee deserializeObject() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(FILE_PATH))) {

            // readObject() returns Object — must cast
            Employee emp = (Employee) ois.readObject();
            return emp;
        }
    }

    // ── SERIALIZE A LIST of objects ───────────────────────────
    public static void serializeList(List<Employee> employees) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("employee_list.ser"))) {
            oos.writeObject(employees);   // writes the whole list
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Employee> deserializeList()
            throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("employee_list.ser"))) {
            return (List<Employee>) ois.readObject();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Employee emp = new Employee(1, "Rashid", "Backend", 85000, "secret123");
        System.out.println("Before: " + emp);

        // Serialize
        serializeObject(emp);

        // Deserialize
        Employee restored = deserializeObject();
        System.out.println("After:  " + restored);
        // Note: password is NULL after deserialization (transient field!)

        // List serialization
        List<Employee> team = new ArrayList<>();
        team.add(new Employee(1, "Rashid", "Backend",  85000, "pass1"));
        team.add(new Employee(2, "Ali",    "Frontend", 72000, "pass2"));
        team.add(new Employee(3, "Sara",   "DevOps",   90000, "pass3"));

        serializeList(team);
        List<Employee> restoredTeam = deserializeList();

        System.out.println("\nRestored team:");
        restoredTeam.forEach(e ->
                System.out.printf("  %s | pwd=%s%n", e.getName(), e.getPassword())
                // passwords are all null — transient!
        );

        // Cleanup
        new java.io.File(FILE_PATH).delete();
        new java.io.File("employee_list.ser").delete();
    }
    
}
