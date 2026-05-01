package Phase_2_Oops_In_Java;

 // ─────────────────────────────────────────────────────────────
// JAVA OOP — L9: static, final & Utility Patterns
//
// static = belongs to the CLASS, not to any specific object.
//   - static field  → one shared copy across all instances
//   - static method → call without creating object: ClassName.method()
//   - static block  → runs once when class is loaded
//
// final = cannot change
//   - final variable → constant (value cannot change)
//   - final method   → cannot be overridden by child class
//   - final class    → cannot be extended (e.g., String, Integer)
//
// In Node.js: static is similar to class-level methods in ES6 classes.
// ─────────────────────────────────────────────────────────────

public class UserAccount {

    // ── STATIC FIELDS — shared across ALL instances ───────────
    private static int    totalAccounts = 0;      // count of all created accounts
    private static final String PLATFORM = "JavaApp";  // constant

    // ── INSTANCE FIELDS — unique per object ───────────────────
    private int    accountId;
    private String username;
    private String email;
    private boolean isActive;

    // ── STATIC BLOCK — runs ONCE when class is first loaded ───
    // Like a one-time setup — before any object is created
    static {
        System.out.println("[UserAccount] Class loaded. Platform: " + PLATFORM);
        // Could connect to DB, load config, etc.
    }

    // ── CONSTRUCTOR ───────────────────────────────────────────
    public UserAccount(String username, String email) {
        totalAccounts++;                  // increment shared counter
        this.accountId = totalAccounts;   // auto-assigned ID
        this.username  = username;
        this.email     = email;
        this.isActive  = true;
    }

    // ── STATIC METHOD — utility, no 'this' needed ─────────────
    // Called as: UserAccount.getTotalAccounts()
    public static int getTotalAccounts() {
        return totalAccounts;
    }

    // Validate email without creating an account
    public static boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    // Factory method pattern — static method that creates objects
    public static UserAccount createGuestAccount() {
        return new UserAccount("guest_" + (totalAccounts + 1), "guest@temp.com");
    }

    @Override
    public String toString() {
        return String.format("[%d] %s | %s | Active: %b", accountId, username, email, isActive);
    }
}

// ── FINAL CLASS — cannot be extended ─────────────────────────
// Like String, Integer in Java's own library
final class AppConfig {

    // All constants — public static final (convention: UPPER_SNAKE_CASE)
    public static final String  APP_NAME    = "MyJavaApp";
    public static final String  VERSION     = "1.0.0";
    public static final int     MAX_RETRIES = 3;
    public static final double  TAX_RATE    = 0.18;  // 18% GST

    // Private constructor — no one can instantiate this
    private AppConfig() {}

    // Static utility method
    public static double calculateGST(double amount) {
        return amount * TAX_RATE;
    }
}

class L9Main {
    public static void main(String[] args) {

        // Static method — no object needed
        System.out.println(UserAccount.isValidEmail("rashid@example.com")); // true
        System.out.println(UserAccount.isValidEmail("invalid-email"));       // false
        System.out.println("Total accounts: " + UserAccount.getTotalAccounts()); // 0

        // Creating objects
        UserAccount u1 = new UserAccount("rashid", "rashid@example.com");
        UserAccount u2 = new UserAccount("ali", "ali@example.com");
        UserAccount u3 = UserAccount.createGuestAccount();  // factory method

        System.out.println(u1);  // [1] rashid | rashid@example.com | Active: true
        System.out.println(u2);  // [2] ali | ...
        System.out.println(u3);  // [3] guest_3 | ...

        // Static field reflects across all objects
        System.out.println("Total accounts: " + UserAccount.getTotalAccounts()); // 3

        // AppConfig — utility class, no instantiation
        System.out.println(AppConfig.APP_NAME);  // MyJavaApp
        System.out.printf("GST on ₹1000 = ₹%.2f%n", AppConfig.calculateGST(1000)); // ₹180.00

        // final variable
        final int MAX_CONNECTIONS = 10;
        // MAX_CONNECTIONS = 20;   // ❌ COMPILE ERROR — final cannot be reassigned
    }
    
}
