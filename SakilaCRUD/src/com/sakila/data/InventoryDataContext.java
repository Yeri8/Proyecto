
package com.sakila.data;

import com.sakila.models.Inventory;
import java.util.List;
import java.util.ArrayList;

public class InventoryDataContext {
    private List<Inventory> inventoryList;

    // Constructor de la clase InventoryDataContext
    // Inicializa la lista de inventarios. En una implementación real, 
    // los datos pueden provenir de una base de datos o un archivo.
    public InventoryDataContext() {
        this.inventoryList = new ArrayList<>();
    }

    // Método para obtener todos los inventarios
    // Devuelve una copia de la lista para evitar modificaciones externas.
    public List<Inventory> getAll() {
        return new ArrayList<>(inventoryList);  // Devuelve una nueva lista con los elementos actuales del inventario
    }

    // Método para obtener un inventario por ID
    // Busca un inventario en la lista utilizando su ID.
    public Inventory get(int id) {
        // Filtra la lista de inventarios y busca el primero cuyo ID coincida
        return inventoryList.stream()
                .filter(inv -> inv.getId() == id)  // Asegúrate de que Inventory tenga un método getId()
                .findFirst()  // Encuentra el primer elemento que cumpla la condición
                .orElse(null);  // Si no encuentra el ítem, devuelve null
    }

    // Método para agregar un nuevo ítem al inventario
    // Este método recibe un objeto Inventory y lo agrega a la lista.
    public boolean post(Inventory inventory) {
        // Verifica que el objeto inventory no sea nulo antes de agregarlo a la lista
        if (inventory != null) {
            return inventoryList.add(inventory);  // Agrega el inventario a la lista y retorna si fue exitoso
        } else {
            System.out.println("El inventario proporcionado es nulo.");
            return false;  // Si el inventario es nulo, no se agrega y retorna false
        }
    }

    // Método para actualizar un ítem del inventario
    // Este método reemplaza un inventario existente por el nuevo basado en el ID.
    public boolean put(Inventory inventory) {
        // Verifica que el objeto inventory no sea nulo
        if (inventory != null) {
            // Recorre la lista de inventarios para encontrar el elemento con el mismo ID
            for (int i = 0; i < inventoryList.size(); i++) {
                if (inventoryList.get(i).getId() == inventory.getId()) {
                    // Reemplaza el inventario existente por el nuevo
                    inventoryList.set(i, inventory);
                    return true;  // Retorna true si la actualización fue exitosa
                }
            }
            System.out.println("No se encontró un inventario con ID: " + inventory.getId());
            return false;  // Si no se encuentra el inventario, retorna false
        } else {
            System.out.println("El inventario proporcionado es nulo.");
            return false;  // Si el inventario es nulo, no se actualiza y retorna false
        }
    }

    // Método para eliminar un ítem del inventario
    // Este método elimina un inventario de la lista basado en su ID.
    public boolean delete(int id) {
        // Usa removeIf para eliminar el inventario cuyo ID coincida
        boolean removed = inventoryList.removeIf(inv -> inv.getId() == id);
        if (!removed) {
            System.out.println("No se encontró un inventario con ID: " + id);
        }
        return removed;  // Retorna true si el inventario fue eliminado, false si no se encontró
    }
}

