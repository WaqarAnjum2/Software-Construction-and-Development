import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Aggregation: Menu HAS-A list of Categories
// Categories can exist independently of a specific Menu instance
public class Menu {

    private String menuId;
    private String restaurantName;
    private List<Category> categories;  // Aggregation

    public Menu(String menuId, String restaurantName) {
        this.menuId         = menuId;
        this.restaurantName = restaurantName;
        this.categories     = new ArrayList<>();
    }

    public String getMenuId()         { return menuId; }
    public String getRestaurantName() { return restaurantName; }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public List<Category> getCategories() {
        return Collections.unmodifiableList(categories);
    }

    public Category findCategoryByName(String name) {
        for (Category c : categories) {
            if (c.getCategoryName().equalsIgnoreCase(name)) return c;
        }
        return null;
    }

    public MenuItem findItemById(String itemId) {
        for (Category c : categories) {
            for (MenuItem item : c.getMenuItems()) {
                if (item.getItemId().equals(itemId)) return item;
            }
        }
        return null;
    }

    public void displayFullMenu() {
        System.out.println("========== " + restaurantName + " Menu ==========");
        for (Category c : categories) {
            c.display();
        }
        System.out.println("==========================================");
    }
}
