// Inheritance (Generalization): DeliveryOrder IS-A Order
// Composition: DeliveryOrder owns an Address (Address is meaningful only in context of this order)
public class DeliveryOrder extends Order {

    private Address deliveryAddress;    // Composition — created for this order
    private String estimatedDeliveryTime;
    private DeliveryDriver assignedDriver;  // Aggregation — driver exists independently

    public DeliveryOrder(Customer customer, Cart cart,
                         Address deliveryAddress, Payment payment) {
        super(customer, cart, payment);
        this.deliveryAddress       = deliveryAddress;
        this.estimatedDeliveryTime = "30-45 minutes";
        System.out.println("Delivery order created: " + getOrderId());
    }

    public Address getDeliveryAddress()      { return deliveryAddress; }
    public String getEstimatedDeliveryTime() { return estimatedDeliveryTime; }
    public DeliveryDriver getAssignedDriver() { return assignedDriver; }

    public void assignDriver(DeliveryDriver driver) {
        this.assignedDriver = driver;
        driver.assignDelivery(this);
        setStatus(OrderStatus.READY);
    }

    @Override
    public String getFulfillmentDetails() {
        return "Delivery to: " + deliveryAddress
                + " | ETA: " + estimatedDeliveryTime
                + (assignedDriver != null ? " | Driver: " + assignedDriver.getName() : "");
    }
}
