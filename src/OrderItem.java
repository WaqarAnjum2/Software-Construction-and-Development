import java.util.List;

// Composition: OrderItem IS PART OF Order — lifecycle is tied to its Order
// It is a snapshot of a CartItem at the time the order was placed
public class OrderItem {

    private String itemName;
    private int quantity;
    private double unitPrice;
    private double subtotal;
    private List<Customization> customizations;

    // Package-private — only Order creates OrderItems (Composition)
    OrderItem(CartItem cartItem) {
        this.itemName       = cartItem.getMenuItem().getName();
        this.quantity       = cartItem.getQuantity();
        this.unitPrice      = cartItem.getUnitPrice();
        this.subtotal       = cartItem.getSubtotal();
        this.customizations = cartItem.getCustomizations();
    }

    public String getItemName()    { return itemName; }
    public int getQuantity()       { return quantity; }
    public double getUnitPrice()   { return unitPrice; }
    public double getSubtotal()    { return subtotal; }

    public void display() {
        System.out.printf("  %-20s x%d  $%.2f%n", itemName, quantity, subtotal);
        for (Customization c : customizations) {
            System.out.println("      + " + c);
        }
    }
}
