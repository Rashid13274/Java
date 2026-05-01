package Phase_4_Exceptional_Handling_And_Input_Output;

 // ─────────────────────────────────────────────────────────────
// PHASE 4 — L3: Custom Exceptions
//
// You can create your own exception classes — very common in
// Spring Boot projects for domain-specific error handling.
//
// Examples from real projects:
//   ResourceNotFoundException  → 404 Not Found
//   UnauthorizedException      → 401 Unauthorized
//   ValidationException        → 400 Bad Request
//   InsufficientFundsException → business logic error
//
// PATTERN:
//   Checked custom   → extend Exception
//   Unchecked custom → extend RuntimeException (most common in Spring)
//
// In Spring Boot, custom exceptions + @ExceptionHandler/@ControllerAdvice
// is the standard pattern for clean REST error responses.
// ─────────────────────────────────────────────────────────────

// ── CUSTOM UNCHECKED EXCEPTION (most common in Spring Boot) ───
// Extend RuntimeException — caller doesn't have to catch it
public class ResourceNotFoundException extends RuntimeException {

    // Store the resource type and ID — useful for error responses
    private final String resourceName;
    private final Object resourceId;

    // Constructor 1: simple message
    public ResourceNotFoundException(String message) {
        super(message);                  // pass message to parent
        this.resourceName = "Unknown";
        this.resourceId   = null;
    }

    // Constructor 2: structured — resource type + ID
    public ResourceNotFoundException(String resourceName, Object resourceId) {
        // Build a clear message automatically
        super(resourceName + " not found with id: " + resourceId);
        this.resourceName = resourceName;
        this.resourceId   = resourceId;
    }

    // Constructor 3: with cause — for wrapping another exception
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
        this.resourceName = "Unknown";
        this.resourceId   = null;
    }

    // Custom getters — useful in @ExceptionHandler to build error response
    public String getResourceName() { return resourceName; }
    public Object getResourceId()   { return resourceId; }
}

// ── CUSTOM CHECKED EXCEPTION ──────────────────────────────────
// Extend Exception — caller MUST handle or declare throws
class InsufficientFundsException extends Exception {

    private final double amount;     // amount attempted
    private final double balance;    // actual balance

    public InsufficientFundsException(double amount, double balance) {
        super(String.format(
                "Cannot process ₹%.2f. Available balance: ₹%.2f", amount, balance));
        this.amount  = amount;
        this.balance = balance;
    }

    public double getAmount()  { return amount; }
    public double getBalance() { return balance; }
    public double getShortfall() { return amount - balance; }
}

// ── CUSTOM EXCEPTION HIERARCHY ────────────────────────────────
// Build a hierarchy of your own exceptions — very clean in large apps
class AppException extends RuntimeException {
    private final int errorCode;

    public AppException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() { return errorCode; }
}

class ValidationException extends AppException {
    private final String field;

    public ValidationException(String field, String reason) {
        super("Validation failed for '" + field + "': " + reason, 400);
        this.field = field;
    }

    public String getField() { return field; }
}

class UnauthorizedException extends AppException {
    public UnauthorizedException(String action) {
        super("Unauthorized to perform: " + action, 401);
    }
}

// ── SERVICE USING CUSTOM EXCEPTIONS ───────────────────────────
class UserService {

    private java.util.Map<Integer, String> users = new java.util.HashMap<>();

    public UserService() {
        users.put(1, "Rashid");
        users.put(2, "Ali");
        users.put(3, "Sara");
    }

    // Throws ResourceNotFoundException (unchecked) — caller doesn't have to catch
    public String getUserById(int id) {
        String user = users.get(id);
        if (user == null) {
            throw new ResourceNotFoundException("User", id);
        }
        return user;
    }

    // Validates input and throws ValidationException
    public void createUser(String name, String email) {
        if (name == null || name.isBlank()) {
            throw new ValidationException("name", "cannot be empty");
        }
        if (email == null || !email.contains("@")) {
            throw new ValidationException("email", "must be a valid email address");
        }
        System.out.println("User created: " + name + " | " + email);
    }
}

class BankService {

    private double balance;

    public BankService(double initialBalance) {
        this.balance = initialBalance;
    }

    // Throws CHECKED exception — caller MUST handle it
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException(amount, balance);
        }
        balance -= amount;
        System.out.printf("Withdrawn ₹%.2f | Remaining: ₹%.2f%n", amount, balance);
    }
}

class CustomExceptionDemo {
    public static void main(String[] args) {

        UserService userService = new UserService();

        // ── ResourceNotFoundException ──────────────────────────
        try {
            String user = userService.getUserById(99);   // doesn't exist
        } catch (ResourceNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Resource: " + e.getResourceName());
            System.out.println("ID: " + e.getResourceId());
            // In Spring Boot: return ResponseEntity.status(404).body(e.getMessage())
        }

        // ── ValidationException ────────────────────────────────
        try {
            userService.createUser("", "invalid-email");
        } catch (ValidationException e) {
            System.out.println("\nValidation Error [" + e.getErrorCode() + "]: "
                               + e.getMessage());
            System.out.println("Field: " + e.getField());
        }

        // Valid creation
        userService.createUser("Rashid", "rashid@example.com");

        // ── InsufficientFundsException (checked) ───────────────
        BankService bank = new BankService(5000);

        try {
            bank.withdraw(2000);   // ok
            bank.withdraw(5000);   // ❌ exceeds balance — throws checked exception

        } catch (InsufficientFundsException e) {
            System.out.println("\nBank Error: " + e.getMessage());
            System.out.printf("Shortfall: ₹%.2f%n", e.getShortfall());
        }

        // ── Exception hierarchy — catch parent catches all children ─
        try {
            throw new UnauthorizedException("DELETE /admin/users");
        } catch (AppException e) {
            // Catches UnauthorizedException because it extends AppException
            System.out.println("\nApp Error [" + e.getErrorCode() + "]: "
                               + e.getMessage());
        }
    }
    
}
