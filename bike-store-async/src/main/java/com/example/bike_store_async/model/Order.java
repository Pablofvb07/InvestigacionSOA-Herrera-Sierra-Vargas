package com.example.bike_store_async.model;

import java.io.Serializable;

public class Order implements Serializable {
    private String orderId;
    private String customerEmail;
    private double amount;

    // Getters y setters
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
