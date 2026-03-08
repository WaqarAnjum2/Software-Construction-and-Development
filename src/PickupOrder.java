// Inheritance (Generalization): PickupOrder IS-A Order
public class PickupOrder extends Order {

    private String pickupLocation;
    private String pickupCode;

    public PickupOrder(Customer customer, Cart cart,
                       String pickupLocation, Payment payment) {
        super(customer, cart, payment);
        this.pickupLocation = pickupLocation;
        this.pickupCode     = generatePickupCode();
        System.out.println("Pickup order created: " + getOrderId()
                + " | Pickup code: " + pickupCode);
    }

    private String generatePickupCode() {
        return "PU-" + (int)(Math.random() * 9000 + 1000);
    }

    public String getPickupLocation() { return pickupLocation; }
    public String getPickupCode()     { return pickupCode; }

    @Override
    public String getFulfillmentDetails() {
        return "Pickup at: " + pickupLocation + " | Code: " + pickupCode;
    }
}
