// Payment is a value object composed inside an Order
public class Payment {

    public enum PaymentMethod { CREDIT_CARD, DEBIT_CARD, CASH_ON_DELIVERY, DIGITAL_WALLET }
    public enum PaymentStatus { PENDING, COMPLETED, FAILED, REFUNDED }

    private String paymentId;
    private PaymentMethod method;
    private double amount;
    private PaymentStatus status;
    private String transactionRef;

    public Payment(String paymentId, PaymentMethod method, double amount) {
        this.paymentId      = paymentId;
        this.method         = method;
        this.amount         = amount;
        this.status         = PaymentStatus.PENDING;
        this.transactionRef = "";
    }

    public String getPaymentId()      { return paymentId; }
    public PaymentMethod getMethod()  { return method; }
    public double getAmount()         { return amount; }
    public PaymentStatus getStatus()  { return status; }
    public String getTransactionRef() { return transactionRef; }

    public boolean processPayment() {
        // Simulate payment processing
        this.transactionRef = "TXN-" + System.currentTimeMillis();
        this.status         = PaymentStatus.COMPLETED;
        System.out.println("Payment of $" + String.format("%.2f", amount)
                + " via " + method + " processed. Ref: " + transactionRef);
        return true;
    }

    public void refund() {
        this.status = PaymentStatus.REFUNDED;
        System.out.println("Payment " + paymentId + " refunded.");
    }

    @Override
    public String toString() {
        return method + " | $" + String.format("%.2f", amount) + " | " + status;
    }
}
