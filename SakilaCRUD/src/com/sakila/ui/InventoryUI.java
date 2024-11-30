
package com.sakila.ui;

import com.sakila.controllers.InventoryController;
import com.sakila.models.Inventory;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InventoryUI {
    private final InventoryController inventoryController;
    private final Scanner scanner;

    public InventoryUI(InventoryController inventoryController) {
        this.inventoryController = inventoryController;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        int option = -1;
        do {
            try {
                System.out.println("\n--- Gestión de Inventario ---");
                System.out.println("1. Ver todo el inventario");
                System.out.println("2. Buscar inventario por ID");
                System.out.println("3. Agregar nuevo ítem al inventario");
                System.out.println("4. Actualizar ítem del inventario");
                System.out.println("5. Eliminar ítem del inventario");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                option = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer
                switch (option) {
                    case 1 -> listInventory();
                    case 2 -> findInventoryById();
                    case 3 -> addInventoryItem();
                    case 4 -> updateInventoryItem();
                    case 5 -> deleteInventoryItem();
                    case 0 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción inválida. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Intente nuevamente.");
                scanner.nextLine(); // Limpiar buffer
            }
        } while (option != 0);
    }

    private int readInt(String message) {
        int value = -1;
        while (value < 0) {
            try {
                System.out.print(message);
                value = scanner.nextInt();
                if (value < 0) System.out.println("El número debe ser positivo.");
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida.");
            } finally {
                scanner.nextLine(); // Limpiar buffer
            }
        }
        return value;
    }

    private void listInventory() {
        System.out.println("\n--- Lista de Inventario ---");
        inventoryController.getAllInventory().forEach(item -> 
            System.out.println("ID: " + item.getId() + ", Movie ID: " + item.getMovieId() + ", Store ID: " + item.getStoreId())
        );
    }

    private void findInventoryById() {
        int id = readInt("Ingrese el ID del ítem: ");
        Inventory inventory = inventoryController.getInventoryById(id);
        if (inventory != null) {
            System.out.println("ID: " + inventory.getId() + ", Movie ID: " + inventory.getMovieId() + ", Store ID: " + inventory.getStoreId());
        } else {
            System.out.println("Ítem no encontrado.");
        }
    }

    private void addInventoryItem() {
        int id = readInt("Ingrese el ID único del ítem: ");
        int movieId = readInt("Ingrese el ID de la película: ");
        int storeId = readInt("Ingrese el ID de la tienda: ");
        Inventory inventory = new Inventory(id, movieId, storeId);
        System.out.println(inventoryController.addInventory(inventory)
                ? "Ítem agregado exitosamente."
                : "Error: El ID ya existe.");
    }

    private void updateInventoryItem() {
        int id = readInt("Ingrese el ID del ítem a actualizar: ");
        Inventory inventory = inventoryController.getInventoryById(id);
        if (inventory != null) {
            int newMovieId = readInt("Ingrese el nuevo ID de la película: ");
            int newStoreId = readInt("Ingrese el nuevo ID de la tienda: ");
            Inventory updatedInventory = new Inventory(id, newMovieId, newStoreId);
            System.out.println(inventoryController.updateInventory(id, updatedInventory)
                    ? "Ítem actualizado exitosamente."
                    : "Error al actualizar el ítem.");
        } else {
            System.out.println("Ítem no encontrado.");
        }
    }

    private void deleteInventoryItem() {
        int id = readInt("Ingrese el ID del ítem a eliminar: ");
        System.out.println(inventoryController.deleteInventory(id)
                ? "Ítem eliminado exitosamente."
                : "Ítem no encontrado.");
    }
}


