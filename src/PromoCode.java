import java.time.LocalDate;

// PromoCode is an independent value object used by Cart (Aggregation)
public class PromoCode {

    private String code;
    private double discountPercent;
    private LocalDate expiryDate;
    private int usageLimit;
    private int usageCount;

    public PromoCode(String code, double discountPercent, LocalDate expiryDate, int usageLimit) {
        this.code            = code;
        this.discountPercent = discountPercent;
        this.expiryDate      = expiryDate;
        this.usageLimit      = usageLimit;
        this.usageCount      = 0;
    }

    // Convenience constructor for demo — 10% off, expires in 30 days, unlimited use
    public PromoCode(String code) {
        this(code, 10.0, LocalDate.now().plusDays(30), Integer.MAX_VALUE);
    }

    public String getCode()            { return code; }
    public double getDiscountPercent() { return discountPercent; }
    public LocalDate getExpiryDate()   { return expiryDate; }

    public boolean isValid() {
        return usageCount < usageLimit
                && !LocalDate.now().isAfter(expiryDate);
    }

    public void redeem() {
        if (isValid()) usageCount++;
    }

    @Override
    public String toString() {
        return code + " (" + discountPercent + "% off, expires " + expiryDate + ")";
    }
}
