import java.util.ArrayList;
import java.util.List;

// Inheritance (Generalization): Customer IS-A Person
// Aggregation: Customer HAS-A Cart (Cart can exist independently)
public class Customer extends Person {

    private Cart cart;                       // Aggregation — Cart lifecycle independent
    private List<Order> orderHistory;        // Aggregation — past orders

    public Customer(String personId, String name, String email, String phone) {
        super(personId, name, email, phone);
        this.cart         = new Cart(this);
        this.orderHistory = new ArrayList<>();
    }

    // --- Cart operations ---
    public Cart getCart() { return cart; }

    public void addToCart(MenuItem item, int quantity, List<Customization> customizations) {
        cart.addItem(item, quantity, customizations);
    }

    public void applyPromoCode(String code) {
        PromoCode promo = new PromoCode(code);
        cart.applyPromoCode(promo);
    }

    // --- Order operations ---
    public Order placeDeliveryOrder(Address deliveryAddress, Payment payment) {
        Order order = new DeliveryOrder(this, cart, deliveryAddress, payment);
        orderHistory.add(order);
        cart.clear();
        return order;
    }

    public Order placePickupOrder(String pickupLocation, Payment payment) {
        Order order = new PickupOrder(this, cart, pickupLocation, payment);
        orderHistory.add(order);
        cart.clear();
        return order;
    }

    public List<Order> getOrderHistory() { return orderHistory; }

    @Override
    public void displayProfile() {
        System.out.println("=== Customer Profile ===");
        System.out.println("ID    : " + getPersonId());
        System.out.println("Name  : " + getName());
        System.out.println("Email : " + getEmail());
        System.out.println("Phone : " + getPhone());
    }
}
