

package com.sakila.controllers;

import com.sakila.models.Inventory;
import java.util.ArrayList;
import java.util.List;

public class InventoryController {
    private final List<Inventory> inventoryList;

    public InventoryController() {
        this.inventoryList = new ArrayList<>();
    }

    // Método para agregar un inventario
    public boolean addInventory(Inventory inventory) {
        if (inventory != null) {
            inventoryList.add(inventory);
            return true;
        }
        return false;
    }

    // Método para obtener todos los inventarios
    public List<Inventory> getAllInventory() {
        return inventoryList;
    }

    // Método para obtener un inventario por ID
    public Inventory getInventoryById(int id) {
        return inventoryList.stream()
                .filter(inv -> inv.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Método para actualizar un inventario
    public boolean updateInventory(int id, Inventory inventory) {
        for (int i = 0; i < inventoryList.size(); i++) {
            if (inventoryList.get(i).getId() == id) {
                inventoryList.set(i, inventory);
                return true;
            }
        }
        return false;
    }

    // Método para eliminar un inventario por ID
    public boolean deleteInventory(int id) {
        return inventoryList.removeIf(inv -> inv.getId() == id);
    }
}

