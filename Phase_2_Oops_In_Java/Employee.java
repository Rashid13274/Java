package Phase_2_Oops_In_Java;

// ─────────────────────────────────────────────────────────────
// JAVA OOP — L3: Encapsulation
//
// Encapsulation = hide internal data, expose controlled access.
// Think: private fields + public getters/setters.
//
// In JS: no enforced privacy (# prefix is new & rare).
// In Java: private is ENFORCED by compiler.
//
// Rule of thumb:
//   All fields → private
//   Read access needed → public getter
//   Write access needed → public setter (with validation)
// ─────────────────────────────────────────────────────────────

public class Employee {

    // ── FIELDS: All private — no direct outside access ───────
    private int    id;
    private String name;
    private String email;
    private double salary;
    private String department;

    // ── CONSTRUCTOR ──────────────────────────────────────────
    public Employee(int id, String name, String email,
                    double salary, String department) {
        this.id         = id;
        this.name       = name;
        this.email      = email;
        setSalary(salary);           // reuse setter — gets validation for free
        this.department = department;
    }

    // ── GETTERS — controlled read access ─────────────────────
    public int    getId()         { return id; }
    public String getName()       { return name; }
    public String getEmail()      { return email; }
    public double getSalary()     { return salary; }
    public String getDepartment() { return department; }

    // ── SETTERS — controlled write access with validation ─────
    // Not every field needs a setter — id is read-only here.

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty!");
        }
        this.name = name.trim();   // clean whitespace
    }

    public void setEmail(String email) {
        // Basic email validation
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email: " + email);
        }
        this.email = email.toLowerCase();   // normalize to lowercase
    }

    public void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative!");
        }
        this.salary = salary;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // ── BUSINESS METHOD: Uses private fields safely ───────────
    public double calculateAnnualBonus(double percentage) {
        return salary * (percentage / 100);
    }

    public void giveRaise(double percent) {
        double raise = salary * (percent / 100);
        this.salary += raise;
        System.out.printf("Raise given! New salary: ₹%.2f%n", salary);
    }

    @Override
    public String toString() {
        return String.format("Employee[%d | %s | %s | ₹%.0f]",
                id, name, department, salary);
    }
}

class L3Main {
    public static void main(String[] args) {

        Employee emp = new Employee(101, "Rashid", "rashid@company.com", 75000, "Backend");

        // ✅ Access via getters
        System.out.println(emp.getName());    // Rashid
        System.out.println(emp.getSalary());  // 75000.0

        // ✅ Modify via setters (validated)
        emp.setSalary(85000);
        emp.setEmail("rashid@newcompany.com");

        // ❌ emp.salary = -9999;  → COMPILE ERROR — private field!
        // ❌ emp.email = "bad";   → COMPILE ERROR — private field!

        // Setter with validation catches bad data
        try {
            emp.setSalary(-5000);  // throws IllegalArgumentException
        } catch (IllegalArgumentException e) {
            System.out.println("Caught: " + e.getMessage()); // Salary cannot be negative!
        }

        emp.giveRaise(10);  // Raise given! New salary: ₹93500.00

        double bonus = emp.calculateAnnualBonus(15);
        System.out.printf("Bonus: ₹%.2f%n", bonus);

        System.out.println(emp);  // Employee[101 | Rashid | Backend | ₹93500]
    }
    
}
