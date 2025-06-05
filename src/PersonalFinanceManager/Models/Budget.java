package PersonalFinanceManager.Models;

import java.io.Serializable;

public class Budget implements Serializable {
    private static final long serialVersionUID = 1L;

    private String category;
    private double limit;
    private double spent;
    private long startDate;
    private long endDate;
    private String frequency; // "Monthly", "Weekly", etc.

    public Budget(String category, double limit, long startDate, long endDate, String frequency) {
        this.category = category;
        this.limit = limit;
        this.spent = 0;
        this.startDate = startDate;
        this.endDate = endDate;
        this.frequency = frequency;
    }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getLimit() { return limit; }
    public void setLimit(double limit) { this.limit = limit; }

    public double getSpent() { return spent; }
    public void setSpent(double spent) { this.spent = spent; }
    public void addSpent(double amount) { this.spent += amount; }

    public long getStartDate() { return startDate; }
    public void setStartDate(long startDate) { this.startDate = startDate; }

    public long getEndDate() { return endDate; }
    public void setEndDate(long endDate) { this.endDate = endDate; }

    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }

    public double getRemainingAmount() { return limit - spent; }

    public double getSpentPercentage() { return (spent / limit) * 100; }

    // rememberto add this mf to logic
    public boolean isExceeded() {
        return spent > limit;
    }
}
