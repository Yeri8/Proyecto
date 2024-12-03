package com.sakila.ui;

import com.sakila.controllers.InventoryController;
import com.sakila.models.Inventory;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InventoryUI {
    private final InventoryController inventoryController; // Controlador que maneja las operaciones de inventario
    private final Scanner scanner; // Escáner para leer la entrada del usuario

    // Constructor que recibe el controlador de inventario como parámetro
    public InventoryUI(InventoryController inventoryController) {
        this.inventoryController = inventoryController; // Asigna el controlador de inventario
        this.scanner = new Scanner(System.in); // Inicializa el escáner para leer entradas del usuario
    }

    // Método para mostrar el menú de opciones al usuario
    public void displayMenu() {
        int option = -1; // Opción inicial fuera del rango válido
        do {
            try {
                // Muestra el menú de opciones disponibles
                System.out.println("\n--- Gestión de Inventario ---");
                System.out.println("1. Ver todo el inventario");
                System.out.println("2. Buscar inventario por ID");
                System.out.println("3. Agregar nuevo ítem al inventario");
                System.out.println("4. Actualizar ítem del inventario");
                System.out.println("5. Eliminar ítem del inventario");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                
                // Lee la opción seleccionada por el usuario
                option = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer del scanner

                // Se ejecuta la acción correspondiente a la opción seleccionada
                switch (option) {
                    case 1 -> listInventory(); // Ver todo el inventario
                    case 2 -> findInventoryById(); // Buscar inventario por ID
                    case 3 -> addInventoryItem(); // Agregar nuevo ítem al inventario
                    case 4 -> updateInventoryItem(); // Actualizar ítem del inventario
                    case 5 -> deleteInventoryItem(); // Eliminar ítem del inventario
                    case 0 -> System.out.println("Saliendo..."); // Salir del menú
                    default -> System.out.println("Opción inválida. Intente nuevamente."); // Opción no válida
                }
            } catch (InputMismatchException e) {
                // Si la entrada no es válida, se captura la excepción y se pide al usuario que intente nuevamente
                System.out.println("Entrada no válida. Intente nuevamente.");
                scanner.nextLine(); // Limpiar buffer
            }
        } while (option != 0); // El menú se repetirá hasta que el usuario seleccione la opción de salir
    }

    // Método para leer un valor entero de forma segura
    private int readInt(String message) {
        int value = -1; // Valor inicial inválido
        while (value < 0) { // El valor debe ser positivo
            try {
                // Solicita al usuario que ingrese un valor
                System.out.print(message);
                value = scanner.nextInt();
                if (value < 0) {
                    // Si el número es negativo, muestra un mensaje de error
                    System.out.println("El número debe ser positivo.");
                }
            } catch (InputMismatchException e) {
                // Si la entrada no es un número válido, muestra un mensaje de error
                System.out.println("Entrada no válida.");
            } finally {
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        }
        return value; // Devuelve el valor ingresado por el usuario
    }

    // Método para mostrar todos los ítems del inventario
    private void listInventory() {
        System.out.println("\n--- Lista de Inventario ---");
        // Obtiene todos los ítems del inventario y los muestra
        inventoryController.getAllInventory().forEach(item -> 
            System.out.println("ID: " + item.getId() + ", Movie ID: " + item.getMovieId() + ", Store ID: " + item.getStoreId())
        );
    }

    // Método para buscar un ítem de inventario por su ID
    private void findInventoryById() {
        int id = readInt("Ingrese el ID del ítem: "); // Leer el ID del ítem
        // Obtiene el ítem usando el controlador de inventario
        Inventory inventory = inventoryController.getInventoryById(id);
        if (inventory != null) {
            // Si el ítem existe, se muestra su información
            System.out.println("ID: " + inventory.getId() + ", Movie ID: " + inventory.getMovieId() + ", Store ID: " + inventory.getStoreId());
        } else {
            // Si el ítem no existe, se muestra un mensaje de error
            System.out.println("Ítem no encontrado.");
        }
    }

    // Método para agregar un nuevo ítem al inventario
    private void addInventoryItem() {
        // Se leen los valores del nuevo ítem
        int id = readInt("Ingrese el ID único del ítem: ");
        int movieId = readInt("Ingrese el ID de la película: ");
        int storeId = readInt("Ingrese el ID de la tienda: ");
        
        // Se crea el nuevo objeto de inventario con los valores proporcionados
        Inventory inventory = new Inventory(id, movieId, storeId);
        
        // Se llama al controlador para agregar el ítem al inventario y se muestra un mensaje de éxito o error
        System.out.println(inventoryController.addInventory(inventory)
                ? "Ítem agregado exitosamente."
                : "Error: El ID ya existe.");
    }

    // Método para actualizar un ítem de inventario
    private void updateInventoryItem() {
        // Se lee el ID del ítem a actualizar
        int id = readInt("Ingrese el ID del ítem a actualizar: ");
        // Obtiene el ítem existente usando el controlador
        Inventory inventory = inventoryController.getInventoryById(id);
        if (inventory != null) {
            // Si el ítem existe, se solicitan los nuevos valores
            int newMovieId = readInt("Ingrese el nuevo ID de la película: ");
            int newStoreId = readInt("Ingrese el nuevo ID de la tienda: ");
            
            // Se crea el objeto actualizado de inventario
            Inventory updatedInventory = new Inventory(id, newMovieId, newStoreId);
            
            // Se llama al controlador para actualizar el ítem y se muestra un mensaje de éxito o error
            System.out.println(inventoryController.updateInventory(id, updatedInventory)
                    ? "Ítem actualizado exitosamente."
                    : "Error al actualizar el ítem.");
        } else {
            // Si el ítem no existe, se muestra un mensaje de error
            System.out.println("Ítem no encontrado.");
        }
    }

    // Método para eliminar un ítem de inventario
    private void deleteInventoryItem() {
        // Se lee el ID del ítem a eliminar
        int id = readInt("Ingrese el ID del ítem a eliminar: ");
        // Se llama al controlador para eliminar el ítem y se muestra un mensaje de éxito o error
        System.out.println(inventoryController.deleteInventory(id)
                ? "Ítem eliminado exitosamente."
                : "Ítem no encontrado.");
    }
}
