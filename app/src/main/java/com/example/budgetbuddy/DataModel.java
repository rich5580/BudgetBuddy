package com.example.budgetbuddy;

public class DataModel {
    private double amount;
    private String spend_type, description;
    private int id;

    public DataModel(int id, double amount, String spend_type, String description) {
        this.id = id;
        this.amount = amount;
        this.spend_type = spend_type;
        this.description = description;
    }

    public DataModel() {
    }

    @Override
    public String toString() {
        return "Spend Type: " + spend_type +
                ", Amount: " + amount;
    }

    public int getId() {
        return id;
    }
    public double getAmount() {
        return amount;
    }
    public String getSpend_type() {
        return spend_type;
    }
    public String getDescription() {
        return description;
    }

}
