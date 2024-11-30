

package com.sakila.data;

import com.sakila.models.Inventory;
import java.util.List;
import java.util.ArrayList;

public class InventoryDataContext {
    private List<Inventory> inventoryList;

    public InventoryDataContext() {
        // Inicializa el inventario, puedes obtenerlo de una base de datos o un archivo
        this.inventoryList = new ArrayList<>();
    }

    // Método para obtener todos los inventarios
    public List<Inventory> getAll() {
        return new ArrayList<>(inventoryList);  // Devuelve una copia de la lista para evitar modificaciones externas
    }

    // Método para obtener un inventario por ID
    public Inventory get(int id) {
        // Verifica si el ID es válido y si la lista contiene elementos
        return inventoryList.stream()
                .filter(inv -> inv.getId() == id)  // Asegúrate de que Inventory tenga un método getId()
                .findFirst()
                .orElse(null);  // Si no encuentra el ítem, devuelve null
    }

    // Método para agregar un nuevo ítem al inventario
    public boolean post(Inventory inventory) {
        if (inventory != null) {
            return inventoryList.add(inventory);  // Agrega el ítem al inventario, retorna si la operación fue exitosa
        } else {
            System.out.println("El inventario proporcionado es nulo.");
            return false;  // No agrega el ítem si es nulo
        }
    }

    // Método para actualizar un ítem del inventario
    public boolean put(Inventory inventory) {
        if (inventory != null) {
            for (int i = 0; i < inventoryList.size(); i++) {
                if (inventoryList.get(i).getId() == inventory.getId()) {
                    inventoryList.set(i, inventory);  // Reemplaza el ítem con el nuevo
                    return true;
                }
            }
            System.out.println("No se encontró un inventario con ID: " + inventory.getId());
            return false;  // Si no encuentra el ítem, retorna false
        } else {
            System.out.println("El inventario proporcionado es nulo.");
            return false;  // Si el ítem es nulo, no se realiza la actualización
        }
    }

    // Método para eliminar un ítem del inventario
    public boolean delete(int id) {
        boolean removed = inventoryList.removeIf(inv -> inv.getId() == id);  // Elimina el ítem por ID
        if (!removed) {
            System.out.println("No se encontró un inventario con ID: " + id);
        }
        return removed;  // Retorna si el ítem fue eliminado correctamente
    }
}

