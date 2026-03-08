// OrderConfirmation builds and sends a confirmation email for a placed Order
// Uses Aggregation: it references an Order (Order exists independently)
public class OrderConfirmation {

    private Order order;    // Aggregation — Order has independent lifecycle

    public OrderConfirmation(Order order) {
        this.order = order;
    }

    public void sendConfirmationEmail() {
        String subject = "Order Confirmed! — " + order.getOrderId();
        String body    = buildEmailBody();

        EmailNotification email = new EmailNotification(
                "NOTIF-" + order.getOrderId(),
                order.getCustomer().getEmail(),
                subject,
                body
        );
        email.send();
    }

    private String buildEmailBody() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hi ").append(order.getCustomer().getName()).append(",\n\n");
        sb.append("Thank you for your order!\n\n");
        sb.append("Order ID   : ").append(order.getOrderId()).append("\n");
        sb.append("Fulfillment: ").append(order.getFulfillmentDetails()).append("\n");
        sb.append("Items:\n");
        for (OrderItem oi : order.getOrderItems()) {
            sb.append("  - ").append(oi.getItemName())
              .append(" x").append(oi.getQuantity())
              .append("  $").append(String.format("%.2f", oi.getSubtotal())).append("\n");
        }
        sb.append("\nTotal      : $").append(String.format("%.2f", order.getTotalAmount()));
        sb.append("\nPayment    : ").append(order.getPayment());
        sb.append("\n\nWe'll notify you when your order is on its way.\n");
        sb.append("Bon appétit!\n— The FoodApp Team");
        return sb.toString();
    }
}
