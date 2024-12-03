package com.sakila.controllers;

import com.sakila.models.Inventory;
import java.util.ArrayList;
import java.util.List;

public class InventoryController {
    // Lista que almacena los ítems de inventario
    private List<Inventory> inventoryList;

    // Constructor que inicializa una lista vacía de inventario
    public InventoryController() {
        this.inventoryList = new ArrayList<>();
    }

    // Método para obtener todos los ítems del inventario
    public List<Inventory> getAllInventory() {
        return inventoryList; // Devuelve la lista completa de inventarios
    }

    // Método para obtener un ítem por su ID
    public Inventory getInventoryById(int id) {
        // Itera a través de la lista de inventarios
        for (Inventory inventory : inventoryList) {
            // Si encuentra el ítem con el ID especificado, lo devuelve
            if (inventory.getId() == id) {
                return inventory;
            }
        }
        // Si no se encuentra el ítem, devuelve null
        return null;
    }

    // Método para agregar un ítem al inventario
    public boolean addInventory(Inventory inventory) {
        // Recorre la lista para verificar si ya existe un ítem con el mismo ID
        for (Inventory item : inventoryList) {
            // Si el ID ya existe, retorna false para indicar que no se puede agregar
            if (item.getId() == inventory.getId()) {
                return false; // ID duplicado
            }
        }
        // Si el ID no existe, agrega el ítem a la lista y retorna true
        inventoryList.add(inventory);
        return true;
    }

    // Método para actualizar un ítem del inventario
    public boolean updateInventory(int id, Inventory updatedInventory) {
        // Itera a través de la lista de inventarios
        for (int i = 0; i < inventoryList.size(); i++) {
            // Si encuentra el ítem con el ID especificado, lo actualiza
            if (inventoryList.get(i).getId() == id) {
                inventoryList.set(i, updatedInventory); // Reemplaza el ítem existente
                return true; // Retorna true para indicar que la actualización fue exitosa
            }
        }
        // Si no se encuentra el ítem con el ID especificado, retorna false
        return false;
    }

    // Método para eliminar un ítem del inventario
    public boolean deleteInventory(int id) {
        // Utiliza removeIf para eliminar el ítem con el ID especificado
        return inventoryList.removeIf(inventory -> inventory.getId() == id);
    }
}
