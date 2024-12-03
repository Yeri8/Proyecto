
package com.sakila.ui;

import com.sakila.controllers.StoreController;
import com.sakila.models.Store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StoreUI {
    private final StoreController storeController; // Controlador que maneja la lógica de negocio de las tiendas
    private final Scanner scanner; // Escáner para leer la entrada del usuario

    // Constructor que inicializa el controlador de tiendas y el escáner
    public StoreUI(StoreController storeController) {
        this.storeController = storeController;
        this.scanner = new Scanner(System.in);
    }

    // Muestra el menú principal de la gestión de tiendas
    public void displayMenu() {
        int option; // Variable para almacenar la opción seleccionada por el usuario

        // Intento de conexión a la base de datos
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "password")) {
            do {
                // Muestra el menú de opciones
                System.out.println("\n--- Gestión de Tiendas ---");
                System.out.println("1. Ver todas las tiendas");
                System.out.println("2. Buscar tienda por ID");
                System.out.println("3. Agregar nueva tienda");
                System.out.println("4. Actualizar tienda");
                System.out.println("5. Eliminar tienda");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                option = scanner.nextInt(); // Lee la opción del usuario
                scanner.nextLine(); // Limpiar el buffer después de leer un número

                // Ejecuta la acción correspondiente a la opción seleccionada
                switch (option) {
                    case 1 -> listStores(conn); // Ver todas las tiendas
                    case 2 -> findStoreById(conn); // Buscar tienda por ID
                    case 3 -> addStore(conn); // Agregar una nueva tienda
                    case 4 -> updateStore(conn); // Actualizar una tienda
                    case 5 -> deleteStore(conn); // Eliminar una tienda
                    case 0 -> System.out.println("Saliendo..."); // Salir del menú
                    default -> System.out.println("Opción inválida. Intente nuevamente."); // Opción inválida
                }
            } while (option != 0); // El ciclo continúa hasta que el usuario elige salir
        } catch (SQLException e) {
            // Si ocurre un error en la conexión a la base de datos, se muestra un mensaje de error
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    // Muestra una lista de todas las tiendas
    private void listStores(Connection conn) {
        System.out.println("\n--- Lista de Tiendas ---");
        // Se obtiene una lista de tiendas, con paginación (límite de 10 registros)
        List<Store> stores = storeController.getAllStores(10, 0, conn); 
        if (stores.isEmpty()) {
            System.out.println("No hay tiendas disponibles.");
        } else {
            // Muestra todas las tiendas obtenidas
            stores.forEach(System.out::println);
        }
    }

    // Busca una tienda por su ID
    private void findStoreById(Connection conn) {
        System.out.print("Ingrese el ID de la tienda: ");
        int id = scanner.nextInt(); // Lee el ID de la tienda
        scanner.nextLine(); // Limpiar buffer

        // Busca la tienda por ID utilizando el controlador
        Store store = storeController.getStoreById(id, conn); 
        if (store != null) {
            System.out.println("\nDetalles de la tienda:");
            // Muestra los detalles de la tienda encontrada
            System.out.println(store);
        } else {
            System.out.println("Tienda no encontrada."); // Si no se encuentra la tienda, muestra un mensaje
        }
    }

    // Agrega una nueva tienda
    private void addStore(Connection conn) {
        // Solicita los datos para agregar una nueva tienda
        System.out.print("Ingrese el ID del gerente de la tienda: ");
        int managerStaffId = scanner.nextInt(); 
        scanner.nextLine(); // Limpiar buffer
        System.out.print("Ingrese la dirección de la tienda: ");
        String address = scanner.nextLine(); 

        // Crea un objeto de tienda con los datos proporcionados (ID se genera automáticamente)
        Store store = new Store(0, managerStaffId, address);  
        // Llama al controlador para agregar la tienda en la base de datos
        boolean success = storeController.addStore(store, conn);
        if (success) {
            System.out.println("Tienda agregada exitosamente.");
        } else {
            System.out.println("Error al agregar la tienda."); // Si ocurre un error al agregar, muestra un mensaje
        }
    }

    // Actualiza una tienda existente
    private void updateStore(Connection conn) {
        System.out.print("Ingrese el ID de la tienda a actualizar: ");
        int id = scanner.nextInt(); // Lee el ID de la tienda a actualizar
        scanner.nextLine(); // Limpiar buffer
        System.out.print("Ingrese el nuevo ID del gerente: ");
        int managerStaffId = scanner.nextInt(); // Lee el nuevo ID del gerente
        scanner.nextLine(); // Limpiar buffer
        System.out.print("Ingrese la nueva dirección de la tienda: ");
        String address = scanner.nextLine(); // Lee la nueva dirección de la tienda

        // Crea una nueva tienda con los datos actualizados
        Store store = new Store(id, managerStaffId, address);
        // Llama al controlador para actualizar la tienda en la base de datos
        boolean success = storeController.updateStore(id, store, conn); 
        if (success) {
            System.out.println("Tienda actualizada exitosamente.");
        } else {
            System.out.println("Error al actualizar la tienda."); // Si ocurre un error, muestra un mensaje
        }
    }

    // Elimina una tienda por su ID
    private void deleteStore(Connection conn) {
        System.out.print("Ingrese el ID de la tienda a eliminar: ");
        int id = scanner.nextInt(); // Lee el ID de la tienda a eliminar
        scanner.nextLine(); // Limpiar buffer

        // Llama al controlador para eliminar la tienda en la base de datos
        boolean success = storeController.deleteStore(id, conn); 
        if (success) {
            System.out.println("Tienda eliminada exitosamente.");
        } else {
            System.out.println("Error al eliminar la tienda."); // Si ocurre un error, muestra un mensaje
        }
    }
}

