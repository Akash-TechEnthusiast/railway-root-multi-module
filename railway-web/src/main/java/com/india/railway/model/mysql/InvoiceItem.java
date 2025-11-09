package com.india.railway.model.mysql;

public class InvoiceItem {
    private String description;
    private double amount;

    // Constructor
    public InvoiceItem(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    // Getters
    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }
}
