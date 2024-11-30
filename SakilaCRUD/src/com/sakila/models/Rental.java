
package com.sakila.models;

public class Rental {
    private int id;
    private int customerId;
    private int inventoryId;

    // Constructor completo
    public Rental(int id, int customerId, int inventoryId) {
        this.id = id;
        this.customerId = customerId;
        this.inventoryId = inventoryId;
    }

    // Constructor adicional (sin id)
    public Rental(int customerId, int inventoryId) {
        this.id = 0; // Valor predeterminado para el ID (se sobrescribirá si es necesario)
        this.customerId = customerId;
        this.inventoryId = inventoryId;
    }

    // Getters y setters
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

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }
}
