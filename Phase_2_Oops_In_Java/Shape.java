package Phase_2_Oops_In_Java;

// ─────────────────────────────────────────────────────────────
// JAVA OOP — L4: Inheritance
//
// Inheritance = child class gets all fields + methods of parent.
// Keyword: 'extends'
//
// Node.js:  class Dog extends Animal { constructor() { super() } }
// Java:     public class Dog extends Animal { Dog() { super() } }
//
// Key rules:
//   - Java supports SINGLE inheritance only (one parent class)
//   - Use super() to call parent constructor — MUST be first line
//   - Use super.methodName() to call parent method
//   - Constructor is NOT inherited — must call super() explicitly
//   - private fields of parent are NOT directly accessible in child
// ─────────────────────────────────────────────────────────────

// ── PARENT CLASS ─────────────────────────────────────────────
public class Shape {

    private String color;
    private boolean filled;

    // Parent constructor
    public Shape(String color, boolean filled) {
        this.color  = color;
        this.filled = filled;
    }

    // Getters
    public String  getColor()    { return color; }
    public boolean isFilled()    { return filled; }

    // Methods meant to be OVERRIDDEN by children
    public double getArea() {
        return 0;   // default — child should provide real value
    }

    public double getPerimeter() {
        return 0;
    }

    // Method NOT meant to be overridden — common across all shapes
    public void display() {
        System.out.printf("Shape: %s | Color: %s | Filled: %b%n",
                getClass().getSimpleName(), color, filled);
        System.out.printf("Area: %.2f | Perimeter: %.2f%n",
                getArea(), getPerimeter());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[color=" + color + "]";
    }
}

// ── CHILD CLASS 1: Circle ─────────────────────────────────────
 class Circle extends Shape {   // 'extends' = inherits Shape

    private double radius;

    public Circle(double radius, String color, boolean filled) {
        super(color, filled);   // calls Shape(color, filled) — MUST be first line!
        this.radius = radius;
    }

    // Override parent method with actual formula
    @Override
    public double getArea() {
        return Math.PI * radius * radius;  // π r²
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;       // 2πr
    }

    public double getRadius() { return radius; }
}

// ── CHILD CLASS 2: Rectangle ──────────────────────────────────
 class Rectangle extends Shape {

    private double width;
    private double height;

    public Rectangle(double width, double height, String color, boolean filled) {
        super(color, filled);   // call parent constructor first
        this.width  = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }
}

// ── CHILD CLASS 3: Triangle ───────────────────────────────────
 class Triangle extends Shape {

    private double a, b, c;  // three sides

    public Triangle(double a, double b, double c, String color) {
        super(color, true);
        this.a = a; this.b = b; this.c = c;
    }

    @Override
    public double getPerimeter() {
        return a + b + c;
    }

    @Override
    public double getArea() {
        // Heron's formula
        double s = getPerimeter() / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
}

class L4Main {
    public static void main(String[] args) {

        Circle    c = new Circle(7, "Red", true);
        Rectangle r = new Rectangle(4, 6, "Blue", false);
        Triangle  t = new Triangle(3, 4, 5, "Green");

        // Calling parent method display() — inherited by all children
        // display() calls getArea() and getPerimeter() — which version?
        // The CHILD version (runtime polymorphism — preview of L5)
        c.display();
        System.out.println("---");
        r.display();
        System.out.println("---");
        t.display();

        // instanceof — checks type at runtime
        System.out.println(c instanceof Shape);    // true — Circle IS-A Shape
        System.out.println(c instanceof Circle);   // true
        System.out.println(r instanceof Rectangle);   // true

        // Parent reference can hold child object (upcasting)
        Shape s = new Circle(5, "Yellow", true);  // Shape ref → Circle object
        s.display();   // calls Circle's getArea() — not Shape's!
    }
}