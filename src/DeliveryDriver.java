// Inheritance (Generalization): DeliveryDriver IS-A Person
public class DeliveryDriver extends Person {

    private String vehicleType;
    private boolean isAvailable;
    private String currentLocation;

    public DeliveryDriver(String personId, String name, String email,
                          String phone, String vehicleType) {
        super(personId, name, email, phone);
        this.vehicleType     = vehicleType;
        this.isAvailable     = true;
        this.currentLocation = "Depot";
    }

    public String getVehicleType()     { return vehicleType; }
    public boolean isAvailable()       { return isAvailable; }
    public String getCurrentLocation() { return currentLocation; }

    public void assignDelivery(DeliveryOrder order) {
        this.isAvailable     = false;
        this.currentLocation = "En route to " + order.getDeliveryAddress().getStreet();
        System.out.println("Driver " + getName() + " assigned to order " + order.getOrderId());
    }

    public void completeDelivery() {
        this.isAvailable = true;
        System.out.println("Delivery completed by " + getName());
    }

    @Override
    public void displayProfile() {
        System.out.println("=== Driver Profile ===");
        System.out.println("ID      : " + getPersonId());
        System.out.println("Name    : " + getName());
        System.out.println("Vehicle : " + vehicleType);
        System.out.println("Status  : " + (isAvailable ? "Available" : "Busy"));
    }
}
