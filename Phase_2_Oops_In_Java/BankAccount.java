package Phase_2_Oops_In_Java;
// ─────────────────────────────────────────────────────────────
// JAVA OOP — L2: Constructors Deep Dive
//
// Java lets you have MULTIPLE constructors (overloading).
// JS doesn't support this natively — you'd use default params.
//
// this()  → calls another constructor in the same class
// super() → calls the parent class constructor (L5)
// ─────────────────────────────────────────────────────────────

public class BankAccount {

    private String accountNumber;
    private String ownerName;
    private double balance;
    private String accountType;  // "SAVINGS" or "CURRENT"

    // ── CONSTRUCTOR 1: Full constructor ──────────────────────
    public BankAccount(String accountNumber, String ownerName,
                       double balance, String accountType) {
        this.accountNumber = accountNumber;
        this.ownerName     = ownerName;
        this.balance       = balance;
        this.accountType   = accountType;
        System.out.println("Account created: " + accountNumber);
    }

    // ── CONSTRUCTOR 2: Default balance & type ────────────────
    // Calls Constructor 1 using this(...)
    // this() must be the FIRST statement in the constructor
    public BankAccount(String accountNumber, String ownerName) {
        this(accountNumber, ownerName, 0.0, "SAVINGS");  // delegates to full constructor
    }

    // ── CONSTRUCTOR 3: Zero balance, custom type ─────────────
    public BankAccount(String accountNumber, String ownerName, String accountType) {
        this(accountNumber, ownerName, 0.0, accountType); // delegates again
    }

    // ── METHODS ───────────────────────────────────────────────
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive!");
            return;
        }
        this.balance += amount;
        System.out.println("Deposited ₹" + amount + " | Balance: ₹" + balance);
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds! Balance: ₹" + balance);
            return;
        }
        this.balance -= amount;
        System.out.println("Withdrawn ₹" + amount + " | Balance: ₹" + balance);
    }

    public double getBalance() {
        return balance;   // getter — controlled read access
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | Type: %s | Balance: ₹%.2f",
                accountNumber, ownerName, accountType, balance);
    }
}

class L2Main {
    public static void main(String[] args) {

        // Using different constructors
        BankAccount acc1 = new BankAccount("ACC001", "Rashid", 5000.0, "SAVINGS");
        BankAccount acc2 = new BankAccount("ACC002", "Ali");              // balance=0, type=SAVINGS
        BankAccount acc3 = new BankAccount("ACC003", "Sara", "CURRENT");  // balance=0, type=CURRENT

        acc1.deposit(2000);    // Balance: ₹7000.0
        acc1.withdraw(1500);   // Balance: ₹5500.0
        acc1.withdraw(9999);   // Insufficient funds!

        System.out.println(acc1); // [ACC001] Rashid | Type: SAVINGS | Balance: ₹5500.00
        System.out.println(acc2); // [ACC002] Ali | Type: SAVINGS | Balance: ₹0.00
    }
    
}
