package com.example.budgetbuddy;

public class DataModel {
    private double amount;
    private String spend_type, description;
    private int id;
    private String fieldName;

    public DataModel(int id, double amount, String spend_type, String description) {
        this.id = id;
        this.amount = amount;
        this.spend_type = spend_type;
        this.description = description;
    }
    public DataModel(double amount, String spend_type, String fieldName) {
        this.amount = amount;
        this.spend_type = spend_type;
        this.fieldName = fieldName;
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
    public String getFieldName() {
        return fieldName;
    }
    public String getDescription() {
        return description;
    }

}
