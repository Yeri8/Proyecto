
package com.sakila.ui;

import com.sakila.controllers.StoreController;
import com.sakila.models.Store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StoreUI {
    private final StoreController storeController;
    private final Scanner scanner;

    public StoreUI(StoreController storeController) {
        this.storeController = storeController;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        int option;
        // Crear conexión a la base de datos
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "password")) {
            do {
                System.out.println("\n--- Gestión de Tiendas ---");
                System.out.println("1. Ver todas las tiendas");
                System.out.println("2. Buscar tienda por ID");
                System.out.println("3. Agregar nueva tienda");
                System.out.println("4. Actualizar tienda");
                System.out.println("5. Eliminar tienda");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                option = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer
                switch (option) {
                    case 1 -> listStores(conn);
                    case 2 -> findStoreById(conn);
                    case 3 -> addStore(conn);
                    case 4 -> updateStore(conn);
                    case 5 -> deleteStore(conn);
                    case 0 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción inválida. Intente nuevamente.");
                }
            } while (option != 0);
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    private void listStores(Connection conn) {
        System.out.println("\n--- Lista de Tiendas ---");
        List<Store> stores = storeController.getAllStores(10, 0, conn); // Paginación
        if (stores.isEmpty()) {
            System.out.println("No hay tiendas disponibles.");
        } else {
            stores.forEach(System.out::println);
        }
    }

    private void findStoreById(Connection conn) {
        System.out.print("Ingrese el ID de la tienda: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        Store store = storeController.getStoreById(id, conn); // Buscar tienda por ID
        if (store != null) {
            System.out.println("\nDetalles de la tienda:");
            System.out.println(store);
        } else {
            System.out.println("Tienda no encontrada.");
        }
    }

    private void addStore(Connection conn) {
        System.out.print("Ingrese el ID del gerente de la tienda: ");
        int managerStaffId = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        System.out.print("Ingrese la dirección de la tienda: ");
        String address = scanner.nextLine();

        Store store = new Store(0, managerStaffId, address);  // El ID se genera automáticamente
        boolean success = storeController.addStore(store, conn); // Agregar tienda
        if (success) {
            System.out.println("Tienda agregada exitosamente.");
        } else {
            System.out.println("Error al agregar la tienda.");
        }
    }

    private void updateStore(Connection conn) {
        System.out.print("Ingrese el ID de la tienda a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        System.out.print("Ingrese el nuevo ID del gerente: ");
        int managerStaffId = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        System.out.print("Ingrese la nueva dirección de la tienda: ");
        String address = scanner.nextLine();

        Store store = new Store(id, managerStaffId, address);
        boolean success = storeController.updateStore(id, store, conn); // Actualizar tienda
        if (success) {
            System.out.println("Tienda actualizada exitosamente.");
        } else {
            System.out.println("Error al actualizar la tienda.");
        }
    }

    private void deleteStore(Connection conn) {
        System.out.print("Ingrese el ID de la tienda a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        boolean success = storeController.deleteStore(id, conn); // Eliminar tienda
        if (success) {
            System.out.println("Tienda eliminada exitosamente.");
        } else {
            System.out.println("Error al eliminar la tienda.");
        }
    }
}

