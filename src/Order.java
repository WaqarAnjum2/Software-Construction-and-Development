import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

// Abstract base class — Generalization root for orders
// Composition: Order owns OrderItems — they are destroyed with the Order
// Aggregation: Order references Customer and Payment (they exist independently)
public abstract class Order {

    public enum OrderStatus { PENDING, CONFIRMED, PREPARING, READY, DELIVERED, CANCELLED }

    private String orderId;
    private Customer customer;                // Aggregation
    private List<OrderItem> orderItems;       // Composition — created from Cart snapshot
    private Payment payment;                  // Aggregation
    private double totalAmount;
    private OrderStatus status;
    private LocalDateTime placedAt;

    protected Order(Customer customer, Cart cart, Payment payment) {
        this.orderId    = "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.customer   = customer;
        this.payment    = payment;
        this.status     = OrderStatus.PENDING;
        this.placedAt   = LocalDateTime.now();
        this.orderItems = snapshotCart(cart);  // Composition — snapshot owned by this Order
        this.totalAmount = cart.getTotalPrice();
        payment.processPayment();
    }

    // Snapshot cart into OrderItems (Composition — these items belong exclusively to this order)
    private List<OrderItem> snapshotCart(Cart cart) {
        List<OrderItem> snapshot = new ArrayList<>();
        for (CartItem ci : cart.getItems()) {
            snapshot.add(new OrderItem(ci));   // Composition
        }
        return snapshot;
    }

    // --- Getters ---
    public String getOrderId()          { return orderId; }
    public Customer getCustomer()       { return customer; }
    public List<OrderItem> getOrderItems() { return Collections.unmodifiableList(orderItems); }
    public Payment getPayment()         { return payment; }
    public double getTotalAmount()      { return totalAmount; }
    public OrderStatus getStatus()      { return status; }
    public LocalDateTime getPlacedAt()  { return placedAt; }

    public void setStatus(OrderStatus status) {
        this.status = status;
        System.out.println("Order " + orderId + " status updated to: " + status);
    }

    public void cancelOrder() {
        this.status = OrderStatus.CANCELLED;
        payment.refund();
    }

    // Template method — each subtype describes its fulfillment type
    public abstract String getFulfillmentDetails();

    public void displayOrder() {
        System.out.println("\n========== Order Summary ==========");
        System.out.println("Order ID  : " + orderId);
        System.out.println("Customer  : " + customer.getName());
        System.out.println("Placed    : " + placedAt);
        System.out.println("Status    : " + status);
        System.out.println("Fulfillment: " + getFulfillmentDetails());
        System.out.println("------------------------------------");
        for (OrderItem oi : orderItems) oi.display();
        System.out.println("------------------------------------");
        System.out.printf("Total     : $%.2f%n", totalAmount);
        System.out.println("Payment   : " + payment);
        System.out.println("====================================");
    }
}
