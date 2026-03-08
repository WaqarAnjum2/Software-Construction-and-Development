import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

/**
 * Online Food Ordering System — Demo
 *
 * Relationships demonstrated:
 *  Generalization (Inheritance) : Person -> Customer, DeliveryDriver
 *                                 Order  -> DeliveryOrder, PickupOrder
 *                                 Notification -> EmailNotification
 *  Composition                  : Order <>-> OrderItem  (OrderItem dies with Order)
 *                                 Cart  <>-> CartItem   (CartItem dies with Cart)
 *  Aggregation                  : Menu  <>-- Category   (Category is independent)
 *                                 Category <>-- MenuItem
 *                                 Customer <>-- Cart
 *                                 Order <>-- Customer, Payment
 */
public class App {
    public static void main(String[] args) {

        // ── 1. BUILD MENU ─────────────────────────────────────────────────────
        Menu menu = new Menu("M001", "Quick Bites Restaurant");

        Category burgers = new Category("CAT1", "Burgers");
        MenuItem classic = new MenuItem("I001", "Classic Burger",  "Beef patty, lettuce, tomato", 8.99);
        MenuItem cheese  = new MenuItem("I002", "Cheese Burger",   "Classic + cheddar cheese",   9.99);
        MenuItem veggie  = new MenuItem("I003", "Veggie Burger",   "Plant-based patty",           8.49);
        burgers.addMenuItem(classic);
        burgers.addMenuItem(cheese);
        burgers.addMenuItem(veggie);

        Category drinks = new Category("CAT2", "Drinks");
        MenuItem cola   = new MenuItem("I004", "Cola",    "350ml chilled",   1.99);
        MenuItem juice  = new MenuItem("I005", "OJ",      "Fresh orange",    2.49);
        drinks.addMenuItem(cola);
        drinks.addMenuItem(juice);

        // Customization options (Aggregation on MenuItem)
        Customization extraCheese  = new Customization("C001", "Extra Cheese", 0.50);
        Customization noPickles    = new Customization("C002", "No Pickles",   0.00);
        Customization largePortion = new Customization("C003", "Upsize",       1.00);
        classic.addCustomization(extraCheese);
        classic.addCustomization(noPickles);
        cheese.addCustomization(largePortion);

        menu.addCategory(burgers);
        menu.addCategory(drinks);

        // ── 2. BROWSE MENU ────────────────────────────────────────────────────
        menu.displayFullMenu();

        // ── 3. CUSTOMER REGISTERS ─────────────────────────────────────────────
        // Generalization: Customer IS-A Person
        Customer customer = new Customer("U001", "Alice Smith",
                                         "alice@example.com", "+1-555-0101");
        customer.displayProfile();

        // ── 4. ADD ITEMS TO CART (with customizations) ────────────────────────
        // Aggregation: Customer has a Cart; Composition: Cart owns CartItems
        customer.addToCart(classic, 2,
                Arrays.asList(extraCheese, noPickles));   // customized
        customer.addToCart(cheese, 1,
                Collections.singletonList(largePortion));
        customer.addToCart(cola, 2,
                Collections.emptyList());

        // ── 5. VIEW CART WITH TOTAL ───────────────────────────────────────────
        customer.getCart().displayCart();

        // ── 6. APPLY PROMO CODE ───────────────────────────────────────────────
        PromoCode promo = new PromoCode("SAVE10", 10.0,
                LocalDate.now().plusDays(7), 100);
        customer.getCart().applyPromoCode(promo);
        customer.getCart().displayCart();   // shows discounted total

        // ── 7A. PLACE DELIVERY ORDER ──────────────────────────────────────────
        // Generalization: DeliveryOrder IS-A Order
        // Composition   : DeliveryOrder owns Address & OrderItems
        Address address = new Address("42 Maple Ave", "Springfield", "IL", "62701");
        Payment payment1 = new Payment("PAY001", Payment.PaymentMethod.CREDIT_CARD,
                customer.getCart().getTotalPrice());

        Order deliveryOrder = customer.placeDeliveryOrder(address, payment1);
        deliveryOrder.displayOrder();

        // Assign a delivery driver (Aggregation: Driver exists independently)
        DeliveryDriver driver = new DeliveryDriver(
                "D001", "Bob Jones", "bob@driver.com", "+1-555-0202", "Bicycle");
        ((DeliveryOrder) deliveryOrder).assignDriver(driver);

        // ── 8. SEND CONFIRMATION EMAIL ────────────────────────────────────────
        // Generalization: EmailNotification IS-A Notification
        OrderConfirmation confirmation1 = new OrderConfirmation(deliveryOrder);
        confirmation1.sendConfirmationEmail();

        System.out.println("\n============================================");
        System.out.println("  PICKUP ORDER DEMO");
        System.out.println("============================================");

        // ── 7B. PLACE PICKUP ORDER ────────────────────────────────────────────
        // Re-add items to fresh cart (previous cart was cleared after order)
        customer.addToCart(veggie, 1, Collections.emptyList());
        customer.addToCart(juice,  1, Collections.emptyList());
        customer.getCart().displayCart();

        Payment payment2 = new Payment("PAY002", Payment.PaymentMethod.DIGITAL_WALLET,
                customer.getCart().getTotalPrice());

        // Generalization: PickupOrder IS-A Order
        Order pickupOrder = customer.placePickupOrder("Main Street Branch", payment2);
        pickupOrder.displayOrder();

        OrderConfirmation confirmation2 = new OrderConfirmation(pickupOrder);
        confirmation2.sendConfirmationEmail();
    }
}

