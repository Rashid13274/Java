package Phase_2_Oops_In_Java;

public // ─────────────────────────────────────────────────────────────
// JAVA OOP — L5: Polymorphism
//
// Polymorphism = "many forms" — same method name, different behavior
// depending on the ACTUAL object type at runtime.
//
// Two types:
//   Compile-time (static)  → Method Overloading (L2 — different params)
//   Runtime (dynamic)      → Method Overriding  (this lesson)
//
// Runtime Polymorphism:
//   Parent reference → Child object
//   Method call → resolved at RUNTIME based on actual object type
//   This is the foundation of Spring's Dependency Injection!
// ─────────────────────────────────────────────────────────────

// ── PARENT ────────────────────────────────────────────────────
abstract class Payment {    // abstract = cannot instantiate directly

    protected String transactionId;
    protected double amount;

    public Payment(String transactionId, double amount) {
        this.transactionId = transactionId;
        this.amount        = amount;
    }

    // Each payment type processes differently — must override
    public abstract void processPayment();   // no body — child MUST implement

    // Common method shared by all — not overridden
    public void printReceipt() {
        System.out.println("=== RECEIPT ===");
        System.out.println("TxID   : " + transactionId);
        System.out.printf ("Amount : ₹%.2f%n", amount);
        System.out.println("Method : " + getClass().getSimpleName());
        System.out.println("===============");
    }
}

// ── CHILD 1: UPI ──────────────────────────────────────────────
class UpiPayment extends Payment {

    private String upiId;

    public UpiPayment(String txId, double amount, String upiId) {
        super(txId, amount);
        this.upiId = upiId;
    }

    @Override
    public void processPayment() {
        System.out.println("Processing UPI payment of ₹" + amount);
        System.out.println("Sending to UPI ID: " + upiId);
        System.out.println("✅ UPI Payment Successful!");
    }
}

// ── CHILD 2: Card ─────────────────────────────────────────────
class CardPayment extends Payment {

    private String cardNumber;
    private String cardType;   // "VISA", "MASTERCARD"

    public CardPayment(String txId, double amount, String cardNumber, String cardType) {
        super(txId, amount);
        // Store only last 4 digits for security (like real apps do)
        this.cardNumber = "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
        this.cardType   = cardType;
    }

    @Override
    public void processPayment() {
        System.out.println("Processing " + cardType + " card payment of ₹" + amount);
        System.out.println("Card: " + cardNumber);
        System.out.println("✅ Card Payment Successful!");
    }
}

// ── CHILD 3: Wallet ───────────────────────────────────────────
class WalletPayment extends Payment {

    private String walletName;
    private double walletBalance;

    public WalletPayment(String txId, double amount, String walletName, double walletBalance) {
        super(txId, amount);
        this.walletName    = walletName;
        this.walletBalance = walletBalance;
    }

    @Override
    public void processPayment() {
        if (walletBalance < amount) {
            System.out.println("❌ Insufficient wallet balance!");
            return;
        }
        walletBalance -= amount;
        System.out.println("Processing " + walletName + " wallet payment of ₹" + amount);
        System.out.printf("Remaining wallet balance: ₹%.2f%n", walletBalance);
        System.out.println("✅ Wallet Payment Successful!");
    }
}

class L5Main {
    public static void main(String[] args) {

        // ── POLYMORPHISM IN ACTION ─────────────────────────────
        // Parent reference → different child objects
        // ONE variable type (Payment) handles ALL payment methods
        // This is exactly how Spring services/repositories work!

        Payment[] payments = {
            new UpiPayment("TXN001", 500.0, "rashid@paytm"),
            new CardPayment("TXN002", 1200.0, "4111111111111234", "VISA"),
            new WalletPayment("TXN003", 300.0, "PhonePe", 250.0),   // will fail
            new WalletPayment("TXN004", 150.0, "Paytm", 500.0)
        };

        // Same loop, same method call — different behavior per type
        for (Payment p : payments) {
            System.out.println("\n--- Processing ---");
            p.processPayment();   // which version runs? Decided at RUNTIME
            p.printReceipt();     // common method — same for all
        }

        // ── METHOD OVERLOADING (compile-time polymorphism) ─────
        // Same class, same method name, different parameters
        // Compiler decides which one to call based on arguments
    }
}

// ── OVERLOADING EXAMPLE ───────────────────────────────────────
class Calculator {

    // Compiler picks the right one based on argument types
    public int    add(int a, int b)          { return a + b; }
    public double add(double a, double b)    { return a + b; }
    public String add(String a, String b)    { return a + b; }   // concatenate
    public int    add(int a, int b, int c)   { return a + b + c; }

    // Not valid overloading — same params, different return type = COMPILE ERROR
    // public double add(int a, int b) { return a + b; }  // ❌

}
