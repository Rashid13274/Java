package Phase_2_Oops_In_Java;

// ─────────────────────────────────────────────────────────────

// JAVA OOP — L8: Abstract Class vs Interface
//
// This is a TOP interview question. Know this cold.
//
// ┌──────────────────┬───────────────────┬────────────────────┐
// │ Feature          │ Abstract Class    │ Interface          │
// ├──────────────────┼───────────────────┼────────────────────┤
// │ Instantiate?     │ ❌ No             │ ❌ No              │
// │ Fields           │ Any type          │ public static final│
// │ Methods          │ abstract + normal │ abstract + default │
// │ Constructor      │ ✅ Yes            │ ❌ No              │
// │ Inheritance      │ Single only       │ Multiple           │
// │ Access modifiers │ Any               │ public only        │
// │ Use keyword      │ extends           │ implements         │
// ├──────────────────┼───────────────────┼────────────────────┤
// │ When to use      │ IS-A relationship │ CAN-DO capability  │
// │                  │ Share state/code  │ Unrelated classes  │
// └──────────────────┴───────────────────┴────────────────────┘
//
// Rule of thumb:
//   "A Dog IS-A Animal"     → abstract class Animal
//   "A Dog CAN swim"        → interface Swimmable
//   "A Plane CAN fly"       → interface Flyable
//   "A Bird CAN fly"        → interface Flyable
//   (Bird and Plane have nothing in common — interface is right)
// ─────────────────────────────────────────────────────────────

// ── SCENARIO: Notification System ────────────────────────────
// All notifications share: message, timestamp, recipient
// But each delivers differently: Email vs SMS vs Push

// Abstract class — shared state + some common behavior
abstract class Notification {

    // Shared fields — all notifications have these
    protected String recipient;
    protected String message;
    protected String timestamp;
    protected boolean isSent;

    public Notification(String recipient, String message) {
        this.recipient = recipient;
        this.message   = message;
        this.timestamp = java.time.LocalDateTime.now().toString();
        this.isSent    = false;
    }

    // Abstract — each delivery channel sends differently
    public abstract boolean send();

    // Concrete — same for all notification types
    public void markAsSent() {
        this.isSent = true;
        System.out.println("✅ Notification sent to: " + recipient);
    }

    public void logNotification() {
        System.out.printf("[%s] %s → %s | Sent: %b%n",
                getClass().getSimpleName(), recipient, message, isSent);
    }
}

// ── INTERFACE — for extra capabilities ───────────────────────
// Not all notifications are schedulable or retryable
// So these are better as interfaces (capabilities, not identity)

interface Schedulable {
    void schedule(String dateTime);
    void cancel();
}

interface Retryable {
    void retry(int maxAttempts);
    int getRetryCount();
}

// ── Email: is a Notification + can be Scheduled + Retried ────
class EmailNotification extends Notification
        implements Schedulable, Retryable {

    private String subject;
    private String scheduledTime;
    private int    retryCount = 0;

    public EmailNotification(String recipient, String subject, String message) {
        super(recipient, message);  // call abstract class constructor
        this.subject = subject;
    }

    // From abstract class Notification
    @Override
    public boolean send() {
        System.out.println("📧 Sending email to: " + recipient);
        System.out.println("   Subject: " + subject);
        System.out.println("   Body: " + message);
        markAsSent();   // call concrete parent method
        return true;
    }

    // From interface Schedulable
    @Override
    public void schedule(String dateTime) {
        this.scheduledTime = dateTime;
        System.out.println("📅 Email scheduled for: " + dateTime);
    }

    @Override
    public void cancel() {
        System.out.println("❌ Scheduled email cancelled.");
    }

    // From interface Retryable
    @Override
    public void retry(int maxAttempts) {
        for (int i = 0; i < maxAttempts; i++) {
            retryCount++;
            System.out.println("🔄 Retry attempt " + retryCount + " for: " + recipient);
            if (send()) break;
        }
    }

    @Override
    public int getRetryCount() { return retryCount; }
}

// ── SMS: is a Notification, NOT Schedulable ──────────────────
class SmsNotification extends Notification {

    private String phoneNumber;

    public SmsNotification(String phoneNumber, String message) {
        super(phoneNumber, message);
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean send() {
        System.out.println("📱 Sending SMS to: " + phoneNumber);
        System.out.println("   Message: " + message);
        markAsSent();
        return true;
    }
}

class L8Main {
    public static void main(String[] args) {

        EmailNotification email = new EmailNotification(
                "rashid@example.com", "Interview Update", "You have an interview tomorrow!");
        SmsNotification sms = new SmsNotification(
                "9876543210", "Your OTP is 4521");

        email.send();
        System.out.println();
        sms.send();
        System.out.println();

        // Schedulable interface reference
        Schedulable s = email;   // EmailNotification IS-A Schedulable
        s.schedule("2024-12-25 10:00");

        // Log all as Notification (common parent type)
        Notification[] all = { email, sms };
        System.out.println("\n=== Notification Log ===");
        for (Notification n : all) {
            n.logNotification();
        }
    }
}