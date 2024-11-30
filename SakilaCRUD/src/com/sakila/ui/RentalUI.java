
package com.sakila.ui;

import com.sakila.controllers.RentalController;
import com.sakila.models.Rental;
import java.util.List;
import java.util.Scanner;

public class RentalUI {
    private final RentalController rentalController;
    private final Scanner scanner;

    public RentalUI(RentalController rentalController) {
        this.rentalController = rentalController;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        int option;
        do {
            System.out.println("\n--- Gestión de Rentas ---");
            System.out.println("1. Ver todas las rentas");
            System.out.println("2. Buscar renta por ID");
            System.out.println("3. Agregar nueva renta");
            System.out.println("4. Actualizar renta");
            System.out.println("5. Eliminar renta");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
            switch (option) {
                case 1 -> listRentals();
                case 2 -> findRentalById();
                case 3 -> addRental();
                case 4 -> updateRental();
                case 5 -> deleteRental();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (option != 0);
    }

    private void listRentals() {
        System.out.println("\n--- Lista de Rentas ---");
        int limit = readInt("Ingrese el límite de registros a mostrar: ");
        int offset = readInt("Ingrese el offset (inicio): ");

        List<Rental> rentals = rentalController.getAllRentals(limit, offset);
        if (rentals.isEmpty()) {
            System.out.println("No hay rentas disponibles.");
        } else {
            rentals.forEach(System.out::println);
        }
    }

    private void findRentalById() {
        int id = readInt("Ingrese el ID de la renta: ");
        Rental rental = rentalController.getRentalById(id);
        if (rental != null) {
            System.out.println("\nDetalles de la renta:");
            System.out.println(rental);
        } else {
            System.out.println("Renta no encontrada.");
        }
    }

    private void addRental() {
        int customerId = readInt("Ingrese el ID del cliente: ");
        int inventoryId = readInt("Ingrese el ID del inventario: ");

        Rental rental = new Rental(customerId, inventoryId); // rentalId se genera automáticamente
        String message = rentalController.addRental(rental);
        System.out.println(message);
    }

    private void updateRental() {
        int id = readInt("Ingrese el ID de la renta a actualizar: ");
        int customerId = readInt("Ingrese el nuevo ID del cliente: ");
        int inventoryId = readInt("Ingrese el nuevo ID del inventario: ");

        Rental rental = new Rental(id, customerId, inventoryId);
        String message = rentalController.updateRental(id, rental);
        System.out.println(message);
    }

    private void deleteRental() {
        int id = readInt("Ingrese el ID de la renta a eliminar: ");
        String message = rentalController.deleteRental(id);
        System.out.println(message);
    }

    private int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor ingresa un número válido.");
            scanner.next(); // Limpiar el buffer
            System.out.print(prompt);
        }
        return scanner.nextInt();
    }
}

