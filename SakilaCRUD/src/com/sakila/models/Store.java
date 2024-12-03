
package com.sakila.models;

public class Store {
    private int storeId;           // Identificador único de la tienda
    private int managerStaffId;    // ID del empleado que gestiona la tienda
    private String address;        // Dirección de la tienda

    // Constructor con todos los parámetros
    public Store(int storeId, int managerStaffId, String address) {
        this.storeId = storeId;
        this.managerStaffId = managerStaffId;
        this.address = address;
    }

    // Getters y Setters
    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getManagerStaffId() {
        return managerStaffId;
    }

    public void setManagerStaffId(int managerStaffId) {
        this.managerStaffId = managerStaffId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // ToString para representar la tienda de forma legible
    @Override
    public String toString() {
        return "Store{id=" + storeId + ", managerStaffId=" + managerStaffId + ", address='" + address + "'}";
    }
}

