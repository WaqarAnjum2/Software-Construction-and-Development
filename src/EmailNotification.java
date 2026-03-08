// Inheritance (Generalization): EmailNotification IS-A Notification
// Responsible for sending order confirmation emails (Acceptance Criteria #6)
public class EmailNotification extends Notification {

    private String senderEmail;

    public EmailNotification(String notificationId, String recipientEmail,
                             String subject, String body) {
        super(notificationId, recipientEmail, subject, body);
        this.senderEmail = "noreply@foodapp.com";
    }

    @Override
    public void send() {
        System.out.println("\n--- Sending Email ---");
        System.out.println("From    : " + senderEmail);
        System.out.println("To      : " + getRecipientEmail());
        System.out.println("Subject : " + getSubject());
        System.out.println("Body    :\n" + getBody());
        System.out.println("--------------------");
        markSent();
        System.out.println("Email sent successfully.");
    }
}
