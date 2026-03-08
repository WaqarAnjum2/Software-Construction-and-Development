import java.util.ArrayList;
import java.util.List;

// Aggregation: MenuItem HAS-A list of Customization options
// MenuItem can exist independently of any Category or Cart
public class MenuItem {

    private String itemId;
    private String name;
    private String description;
    private double basePrice;
    private boolean isAvailable;
    private List<Customization> availableCustomizations;  // Aggregation

    public MenuItem(String itemId, String name, String description, double basePrice) {
        this.itemId                  = itemId;
        this.name                    = name;
        this.description             = description;
        this.basePrice               = basePrice;
        this.isAvailable             = true;
        this.availableCustomizations = new ArrayList<>();
    }

    public String getItemId()         { return itemId; }
    public String getName()           { return name; }
    public String getDescription()    { return description; }
    public double getBasePrice()      { return basePrice; }
    public boolean isAvailable()      { return isAvailable; }

    public List<Customization> getAvailableCustomizations() {
        return availableCustomizations;
    }

    public void addCustomization(Customization c) {
        availableCustomizations.add(c);
    }

    public void setAvailable(boolean available) { this.isAvailable = available; }
    public void setBasePrice(double price)      { this.basePrice   = price; }

    public void display() {
        System.out.printf("  [%s] %-20s $%.2f  %s%n",
                itemId, name, basePrice,
                isAvailable ? "" : "(Unavailable)");
    }
}
