package PersonalFinanceManager.Models;

import java.io.Serializable;

public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;

    private String description;
    private String category;
    private double amount;
    private long date;
    private boolean isIncome;
    private boolean isRecurring;
    private int recurringPeriod; // in days

    public Transaction(String description, String category, double amount, long date) {
        this.description = description;
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.isIncome = false; // Default to expense
        this.isRecurring = false;
        this.recurringPeriod = 0;
    }

    // Getters and setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public boolean isIncome() {
        return isIncome;
    }

    public void setIncome(boolean income) {
        isIncome = income;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    public void setRecurring(boolean recurring) {
        isRecurring = recurring;
    }

    public int getRecurringPeriod() {
        return recurringPeriod;
    }

    public void setRecurringPeriod(int recurringPeriod) {
        this.recurringPeriod = recurringPeriod;
    }

    @Override
    public String toString() {
        return description + " - " + (isIncome ? "+" : "-") + amount + " (" + category + ")";
    }
}
