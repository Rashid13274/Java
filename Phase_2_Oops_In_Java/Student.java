package Phase_2_Oops_In_Java;

// ─────────────────────────────────────────────────────────────
// JAVA OOP — L1: Classes & Objects
//
// In Node.js you can write functions anywhere at top level.
// In Java, EVERYTHING must live inside a class.
//
// Class  = blueprint / template
// Object = real instance created from that blueprint
//
// Like in JS:  class Student { constructor(...) {} }
// In Java  :  public class Student { Student(...) {} }
// ─────────────────────────────────────────────────────────────

public class Student {

    // ── FIELDS (Instance Variables) ──────────────────────────
    // These belong to each object separately.
    // Like 'this.name' inside a JS class constructor.
    // Access modifiers:
    //   public  → accessible from anywhere
    //   private → accessible only within this class (preferred)
    //   protected → accessible in same package + subclasses

    private String name;      // private by default — best practice
    private int    age;
    private String grade;
    private double gpa;

    // ── CONSTRUCTOR ──────────────────────────────────────────
    // Special method with the SAME NAME as the class.
    // No return type (not even void).
    // Called automatically when you do: new Student(...)
    // Like constructor() in a JS class.

    public Student(String name, int age, String grade, double gpa) {
        // 'this' refers to the current object being created
        // Same as 'this' in JS — but Java requires it when
        // parameter name shadows the field name
        this.name  = name;
        this.age   = age;
        this.grade = grade;
        this.gpa   = gpa;
    }

    // ── INSTANCE METHODS ─────────────────────────────────────
    // Methods that belong to each object.
    // Must be called on an object: student.introduce()

    public void introduce() {
        // Can access all fields directly (same class scope)
        System.out.println("Hi! I'm " + name + ", age " + age);
    }

    public boolean isDistinction() {
        return gpa >= 8.0;   // returns boolean — like a predicate in JS
    }

    public String getSummary() {
        // String.format() — like template literals in JS
        return String.format("Student[%s | Grade: %s | GPA: %.1f]", name, grade, gpa);
    }

    // ── toString() ───────────────────────────────────────────
    // Special method — called when you print the object.
    // Like .toString() or [Symbol.toPrimitive] in JS.
    // Without this, System.out.println(obj) shows: Student@7852e922 (ugly)

    @Override                     // annotation: tells compiler we're overriding Object's method
    public String toString() {
        return "Student(" + name + ", " + age + ", GPA=" + gpa + ")";
    }
}

// ── MAIN CLASS — entry point ──────────────────────────────────
// In real projects: separate file. Here combined for clarity.

class Main {
    public static void main(String[] args) {

        // Creating objects with 'new' keyword
        // Like: const s1 = new Student(...) in JS
        Student s1 = new Student("Rashid", 25, "A", 8.5);
        Student s2 = new Student("Ali",    22, "B", 7.2);

        // Calling methods on objects
        s1.introduce();                          // Hi! I'm Rashid, age 25
        System.out.println(s1.isDistinction()); // true
        System.out.println(s1.getSummary());    // Student[Rashid | Grade: A | GPA: 8.5]
        System.out.println(s1);                 // Student(Rashid, 25, GPA=8.5)
        System.out.println(s2);                 // Student(Ali, 22, GPA=7.2)

        // Each object has its OWN copy of fields
        System.out.println(s1 == s2);   // false — different objects in memory
    }
}