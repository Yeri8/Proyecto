

package com.sakila.models;

public class Payment {
    private int id;           // Identificador único del pago
    private int customerId;   // ID del cliente que realiza el pago
    private double amount;    // Monto del pago

    // Constructor
    public Payment(int id, int customerId, double amount) {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Payment{id=" + id + ", customerId=" + customerId + ", amount=" + amount + '}';
    }
}
