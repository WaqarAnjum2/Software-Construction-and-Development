import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Aggregation: Category HAS-A list of MenuItems
// MenuItems can exist independently (they are not destroyed with the Category)
public class Category {

    private String categoryId;
    private String categoryName;
    private List<MenuItem> menuItems;   // Aggregation

    public Category(String categoryId, String categoryName) {
        this.categoryId   = categoryId;
        this.categoryName = categoryName;
        this.menuItems    = new ArrayList<>();
    }

    public String getCategoryId()   { return categoryId; }
    public String getCategoryName() { return categoryName; }

    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }

    public void removeMenuItem(MenuItem item) {
        menuItems.remove(item);
    }

    public List<MenuItem> getMenuItems() {
        return Collections.unmodifiableList(menuItems);
    }

    public List<MenuItem> getAvailableItems() {
        List<MenuItem> available = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (item.isAvailable()) available.add(item);
        }
        return available;
    }

    public void display() {
        System.out.println("\n--- " + categoryName + " ---");
        for (MenuItem item : menuItems) {
            item.display();
        }
    }
}
