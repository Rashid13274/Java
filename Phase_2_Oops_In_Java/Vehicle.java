package Phase_2_Oops_In_Java;

// ─────────────────────────────────────────────────────────────
// JAVA OOP — L6: Abstract Classes
//
// Abstract class = partially defined class.
//   - Cannot be instantiated directly (no 'new AbstractClass()')
//   - Can have abstract methods (no body — child MUST implement)
//   - Can have concrete methods (with body — shared logic)
//   - Can have fields, constructors, everything a normal class has
//
// When to use:
//   "These classes SHARE code but each also has unique behavior"
//   Use abstract class when child classes share STATE (fields)
//   and some common implementation.
// ─────────────────────────────────────────────────────────────

abstract class Vehicle {

    // ── SHARED FIELDS ─────────────────────────────────────────
    protected String brand;
    protected String model;
    protected int    year;
    protected double fuelLevel;      // 0.0 to 1.0 (percentage)

    // ── CONSTRUCTOR — called by child via super() ─────────────
    public Vehicle(String brand, String model, int year) {
        this.brand     = brand;
        this.model     = model;
        this.year      = year;
        this.fuelLevel = 1.0;   // start with full tank
    }

    // ── ABSTRACT METHODS — no body, child MUST implement ──────
    // Each vehicle type starts/stops differently
    public abstract void start();
    public abstract void stop();
    public abstract double getFuelEfficiency();  // km per litre

    // ── CONCRETE METHODS — shared implementation ───────────────
    // Every vehicle can refuel — same logic for all
    public void refuel(double litres) {
        fuelLevel = Math.min(1.0, fuelLevel + litres / 50.0);  // 50L tank
        System.out.printf("%s refuelled. Tank: %.0f%%%n", brand, fuelLevel * 100);
    }

    // Shared display — but calls abstract methods via polymorphism
    public void status() {
        System.out.printf("[%s %s %d] Fuel: %.0f%% | Efficiency: %.1f km/l%n",
                brand, model, year, fuelLevel * 100, getFuelEfficiency());
    }

    @Override
    public String toString() {
        return brand + " " + model + " (" + year + ")";
    }
}

// ── CONCRETE CHILD 1: Car ─────────────────────────────────────
class Car extends Vehicle {

    private int engineCC;
    private boolean isElectric;

    public Car(String brand, String model, int year, int engineCC) {
        super(brand, model, year);   // call abstract parent constructor
        this.engineCC  = engineCC;
        this.isElectric = false;
    }

    // Must implement ALL abstract methods — else COMPILE ERROR
    @Override
    public void start() {
        System.out.println(brand + " " + model + ": Ignition ON 🔑 Vroom!");
    }

    @Override
    public void stop() {
        System.out.println(brand + " " + model + ": Engine OFF.");
    }

    @Override
    public double getFuelEfficiency() {
        // Smaller engine = better efficiency
        return engineCC <= 1000 ? 22.0 : engineCC <= 1500 ? 18.0 : 14.0;
    }
}

// ── CONCRETE CHILD 2: Truck ───────────────────────────────────
class Truck extends Vehicle {

    private double loadCapacityTons;

    public Truck(String brand, String model, int year, double loadCapacityTons) {
        super(brand, model, year);
        this.loadCapacityTons = loadCapacityTons;
    }

    @Override
    public void start() {
        System.out.println(brand + " Truck: Engine growling... STARTED 🚛");
    }

    @Override
    public void stop() {
        System.out.println(brand + " Truck: Air brakes hiss... STOPPED.");
    }

    @Override
    public double getFuelEfficiency() {
        return 8.0 - (loadCapacityTons * 0.5);   // heavier load = worse efficiency
    }
}

class L6Main {
    public static void main(String[] args) {

        // ❌ Cannot instantiate abstract class
        // Vehicle v = new Vehicle("Generic", "X", 2024);  // COMPILE ERROR

        // ✅ Must use concrete subclasses
        Car   car   = new Car("Honda", "City", 2023, 1500);
        Truck truck = new Truck("Tata", "Prima", 2022, 15.0);

        car.start();
        car.status();    // uses Car's getFuelEfficiency()
        car.refuel(20);
        car.stop();

        System.out.println();

        truck.start();
        truck.status();  // uses Truck's getFuelEfficiency()
        truck.stop();

        // ── Polymorphism with abstract class ──────────────────
        // Vehicle array holds both Car and Truck
        Vehicle[] fleet = { car, truck, new Car("Maruti", "Swift", 2024, 1200) };

        System.out.println("\n=== FLEET STATUS ===");
        for (Vehicle v : fleet) {
            v.status();
        }
    }

    
}
