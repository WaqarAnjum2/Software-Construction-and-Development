// Abstract base class — Generalization root
// Customer and DeliveryDriver inherit from Person
public abstract class Person {
    private String personId;
    private String name;
    private String email;
    private String phone;

    public Person(String personId, String name, String email, String phone) {
        this.personId = personId;
        this.name     = name;
        this.email    = email;
        this.phone    = phone;
    }

    public String getPersonId() { return personId; }
    public String getName()     { return name; }
    public String getEmail()    { return email; }
    public String getPhone()    { return phone; }

    public void setName(String name)   { this.name  = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }

    // Template method — each subclass decides how to display profile
    public abstract void displayProfile();
}
