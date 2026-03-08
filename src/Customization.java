// Customization represents an add-on/modification option for a MenuItem
// e.g., "Extra Cheese +$0.50", "No Onions", "Large Size +$1.00"
public class Customization {

    private String customizationId;
    private String name;
    private double additionalCost;

    public Customization(String customizationId, String name, double additionalCost) {
        this.customizationId = customizationId;
        this.name            = name;
        this.additionalCost  = additionalCost;
    }

    public String getCustomizationId() { return customizationId; }
    public String getName()            { return name; }
    public double getAdditionalCost()  { return additionalCost; }

    @Override
    public String toString() {
        if (additionalCost > 0)
            return name + " (+$" + String.format("%.2f", additionalCost) + ")";
        return name;
    }
}
