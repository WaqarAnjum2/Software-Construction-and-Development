// Abstract base class — Generalization root for all notification types
public abstract class Notification {

    private String notificationId;
    private String recipientEmail;
    private String subject;
    private String body;
    private boolean sent;

    public Notification(String notificationId, String recipientEmail,
                        String subject, String body) {
        this.notificationId  = notificationId;
        this.recipientEmail  = recipientEmail;
        this.subject         = subject;
        this.body            = body;
        this.sent            = false;
    }

    public String getNotificationId() { return notificationId; }
    public String getRecipientEmail() { return recipientEmail; }
    public String getSubject()        { return subject; }
    public String getBody()           { return body; }
    public boolean isSent()           { return sent; }

    protected void markSent() { this.sent = true; }

    // Template method — subclasses define the sending mechanism
    public abstract void send();
}
