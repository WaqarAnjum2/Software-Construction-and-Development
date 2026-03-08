import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Composition: Cart owns CartItems — CartItems are destroyed when Cart is cleared/destroyed
// Aggregation: Cart is associated with a Customer (Customer owns the Cart reference)
public class Cart {

    private Customer customer;              // Aggregation back-reference
    private List<CartItem> items;           // Composition — CartItems live inside Cart
    private PromoCode appliedPromoCode;

    // Package-private — Cart is created by Customer (Aggregation boundary)
    Cart(Customer customer) {
        this.customer = customer;
        this.items    = new ArrayList<>();
    }

    // --- Item management ---
    public void addItem(MenuItem menuItem, int quantity, List<Customization> customizations) {
        // If same item already in cart (no customizations diff), increase qty
        for (CartItem existing : items) {
            if (existing.getMenuItem().getItemId().equals(menuItem.getItemId())
                    && existing.getCustomizations().equals(customizations)) {
                existing.setQuantity(existing.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(menuItem, quantity, customizations));  // Composition
    }

    public void removeItem(MenuItem menuItem) {
        items.removeIf(ci -> ci.getMenuItem().getItemId().equals(menuItem.getItemId()));
    }

    public void clear() {
        items.clear();
        appliedPromoCode = null;
    }

    // --- Promo code ---
    public void applyPromoCode(PromoCode promoCode) {
        if (promoCode.isValid()) {
            this.appliedPromoCode = promoCode;
            System.out.println("Promo code applied: " + promoCode.getCode()
                    + " — " + promoCode.getDiscountPercent() + "% off");
        } else {
            System.out.println("Invalid or expired promo code.");
        }
    }

    // --- Pricing ---
    public double getSubtotal() {
        double total = 0;
        for (CartItem item : items) total += item.getSubtotal();
        return total;
    }

    public double getDiscount() {
        if (appliedPromoCode == null) return 0;
        return getSubtotal() * appliedPromoCode.getDiscountPercent() / 100.0;
    }

    public double getTotalPrice() {
        return getSubtotal() - getDiscount();
    }

    public boolean isEmpty() { return items.isEmpty(); }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public PromoCode getAppliedPromoCode() { return appliedPromoCode; }

    // --- Display ---
    public void displayCart() {
        System.out.println("\n========== Cart for " + customer.getName() + " ==========");
        if (items.isEmpty()) {
            System.out.println("  Cart is empty.");
            return;
        }
        for (CartItem ci : items) ci.display();
        System.out.println("------------------------------------------");
        System.out.printf("Subtotal : $%.2f%n", getSubtotal());
        if (appliedPromoCode != null)
            System.out.printf("Discount : -$%.2f (%s)%n",
                    getDiscount(), appliedPromoCode.getCode());
        System.out.printf("Total    : $%.2f%n", getTotalPrice());
        System.out.println("==========================================");
    }
}
