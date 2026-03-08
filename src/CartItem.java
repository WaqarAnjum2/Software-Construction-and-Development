import java.util.List;

// Composition: CartItem IS PART OF Cart — it cannot exist without a Cart
// CartItem references a MenuItem (Aggregation — item exists independently)
public class CartItem {

    private MenuItem menuItem;                    // Aggregation — MenuItem is shared
    private int quantity;
    private List<Customization> customizations;   // selected customizations
    private double unitPrice;                     // base + customization costs

    // Package-private constructor — only Cart creates CartItems (Composition)
    CartItem(MenuItem menuItem, int quantity, List<Customization> customizations) {
        this.menuItem       = menuItem;
        this.quantity       = quantity;
        this.customizations = customizations;
        this.unitPrice      = calculateUnitPrice();
    }

    private double calculateUnitPrice() {
        double price = menuItem.getBasePrice();
        for (Customization c : customizations) {
            price += c.getAdditionalCost();
        }
        return price;
    }

    public MenuItem getMenuItem()           { return menuItem; }
    public int getQuantity()                { return quantity; }
    public List<Customization> getCustomizations() { return customizations; }
    public double getUnitPrice()            { return unitPrice; }
    public double getSubtotal()             { return unitPrice * quantity; }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void display() {
        System.out.printf("  %-20s x%d  $%.2f%n",
                menuItem.getName(), quantity, getSubtotal());
        for (Customization c : customizations) {
            System.out.println("      + " + c);
        }
    }
}
