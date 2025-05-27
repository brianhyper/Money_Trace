package PersonalFinanceManager.Models;

import java.io.Serializable;

public class Debt implements Serializable {
    private String description;
    private double amount;
    private boolean isLoaned; // true = loaned, false = owed
    private String person;

    public Debt(String description, double amount, boolean isLoaned, String person) {
        this.description = description;
        this.amount = amount;
        this.isLoaned = isLoaned;
        this.person = person;
    }

    public String getDescription() { return description; }
    public double getAmount() { return amount; }
    public boolean isLoaned() { return isLoaned; }
    public String getPerson() { return person; }
}
