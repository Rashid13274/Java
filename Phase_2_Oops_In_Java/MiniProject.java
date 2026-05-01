package Phase_2_Oops_In_Java;

// ─────────────────────────────────────────────────────────────
// JAVA OOP — L10: Mini Project — Employee Management System
//
// Combines everything from L1–L9:
//   ✅ Abstract class   (Person)
//   ✅ Interface        (Taxable, Reportable)
//   ✅ Inheritance      (Employee, Manager extends Person)
//   ✅ Encapsulation    (private fields + getters/setters)
//   ✅ Polymorphism     (calculateTax() differs per type)
//   ✅ static           (EmployeeRegistry.count)
//   ✅ final            (COMPANY_NAME, TAX_SLAB constants)
//   ✅ toString()       (custom print per class)
// ─────────────────────────────────────────────────────────────

import java.util.ArrayList;
import java.util.List;

// ── INTERFACES ────────────────────────────────────────────────
interface Taxable {
    double TAX_SLAB_STANDARD = 0.20;  // 20% (constant in interface)
    double TAX_SLAB_SENIOR   = 0.10;  // 10% for seniors

    double calculateTax();
    double getNetSalary();
}

interface Reportable {
    void generateReport();
}

// ── ABSTRACT CLASS ────────────────────────────────────────────
abstract class Person {

    private String name;
    private int    age;
    private String email;

    public Person(String name, int age, String email) {
        this.name  = name;
        this.age   = age;
        this.email = email;
    }

    // Getters
    public String getName()  { return name; }
    public int    getAge()   { return age; }
    public String getEmail() { return email; }

    // Abstract — each subclass introduces itself differently
    public abstract String getRole();

    @Override
    public String toString() {
        return String.format("%s [%s | Age: %d]", name, getRole(), age);
    }
}

// ── CONCRETE CLASS 1: Regular Employee ───────────────────────
class Employee extends Person implements Taxable, Reportable {

    private static int employeeCount = 0;   // tracks total employees created

    private int    employeeId;
    private String department;
    private double baseSalary;

    public Employee(String name, int age, String email,
                    String department, double baseSalary) {
        super(name, age, email);
        employeeId     = ++employeeCount;
        this.department = department;
        this.baseSalary = baseSalary;
    }

    @Override
    public String getRole() { return "Employee"; }

    // ── Taxable interface ──────────────────────────────────────
    @Override
    public double calculateTax() {
        // Senior employees (age > 55) get lower tax
        double slab = getAge() > 55 ? TAX_SLAB_SENIOR : TAX_SLAB_STANDARD;
        return baseSalary * slab;
    }

    @Override
    public double getNetSalary() {
        return baseSalary - calculateTax();
    }

    // ── Reportable interface ───────────────────────────────────
    @Override
    public void generateReport() {
        System.out.println("────────────────────────────");
        System.out.println("Employee Report");
        System.out.println("ID         : EMP-" + employeeId);
        System.out.println("Name       : " + getName());
        System.out.println("Department : " + department);
        System.out.printf ("Gross      : ₹%.2f%n", baseSalary);
        System.out.printf ("Tax        : ₹%.2f%n", calculateTax());
        System.out.printf ("Net Salary : ₹%.2f%n", getNetSalary());
        System.out.println("────────────────────────────");
    }

    public double getBaseSalary()  { return baseSalary; }
    public String getDepartment()  { return department; }
    public int    getEmployeeId()  { return employeeId; }

    public static int getEmployeeCount() { return employeeCount; }
}

// ── CONCRETE CLASS 2: Manager (extends Employee) ─────────────
class Manager extends Employee {

    private double bonus;
    private List<Employee> team;

    public Manager(String name, int age, String email,
                   String department, double baseSalary, double bonus) {
        super(name, age, email, department, baseSalary);
        this.bonus = bonus;
        this.team  = new ArrayList<>();
    }

    @Override
    public String getRole() { return "Manager"; }

    // Manager pays higher tax — override
    @Override
    public double calculateTax() {
        double totalComp = getBaseSalary() + bonus;
        return totalComp * 0.30;   // 30% tax for managers
    }

    @Override
    public double getNetSalary() {
        return (getBaseSalary() + bonus) - calculateTax();
    }

    public void addTeamMember(Employee e) {
        team.add(e);
        System.out.println(e.getName() + " added to " + getName() + "'s team.");
    }

    @Override
    public void generateReport() {
        System.out.println("════════════════════════════");
        System.out.println("Manager Report");
        System.out.println("Name       : " + getName());
        System.out.println("Department : " + getDepartment());
        System.out.printf ("Base       : ₹%.2f%n", getBaseSalary());
        System.out.printf ("Bonus      : ₹%.2f%n", bonus);
        System.out.printf ("Tax (30%%) : ₹%.2f%n", calculateTax());
        System.out.printf ("Net Salary : ₹%.2f%n", getNetSalary());
        System.out.println("Team size  : " + team.size());
        if (!team.isEmpty()) {
            System.out.print("Team       : ");
            team.forEach(e -> System.out.print(e.getName() + " "));
            System.out.println();
        }
        System.out.println("════════════════════════════");
    }
}

// ── REGISTRY: Static utility class ───────────────────────────
class EmployeeRegistry {

    private static final String COMPANY = "JavaCorp Pvt. Ltd.";
    private static List<Employee> registry = new ArrayList<>();

    private EmployeeRegistry() {}   // no instantiation

    public static void register(Employee e) {
        registry.add(e);
    }

    public static void printAllReports() {
        System.out.println("\n=== " + COMPANY + " — All Reports ===");
        for (Employee e : registry) {
            e.generateReport();
        }
        System.out.println("Total Employees: " + Employee.getEmployeeCount());
    }

    public static double totalPayroll() {
        return registry.stream()
                .mapToDouble(Employee::getNetSalary)
                .sum();
    }
}

// ── MAIN ──────────────────────────────────────────────────────
class L10Main {
    public static void main(String[] args) {

        // Create employees
        Employee e1 = new Employee("Rashid", 25, "rashid@corp.com", "Backend", 85000);
        Employee e2 = new Employee("Sara",   30, "sara@corp.com",   "Frontend", 72000);
        Employee e3 = new Employee("Ali",    57, "ali@corp.com",    "DevOps",   90000); // senior

        // Create manager
        Manager mgr = new Manager("Priya", 40, "priya@corp.com", "Engineering", 150000, 30000);
        mgr.addTeamMember(e1);
        mgr.addTeamMember(e2);
        mgr.addTeamMember(e3);

        // Register all
        EmployeeRegistry.register(e1);
        EmployeeRegistry.register(e2);
        EmployeeRegistry.register(e3);
        EmployeeRegistry.register(mgr);

        // Generate all reports (polymorphism: each generateReport() differs)
        EmployeeRegistry.printAllReports();

        System.out.printf("%nTotal Company Payroll: ₹%.2f%n",
                EmployeeRegistry.totalPayroll());

        // Polymorphism demo
        System.out.println("\n=== Polymorphism Demo ===");
        Taxable[] taxables = { e1, e2, e3, mgr };   // all are Taxable
        for (Taxable t : taxables) {
            System.out.printf("%-10s → Tax: ₹%.2f | Net: ₹%.2f%n",
                    ((Person) t).getName(), t.calculateTax(), t.getNetSalary());
        }
    }
}