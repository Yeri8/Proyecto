
package com.sakila.ui;

import com.sakila.controllers.RentalController;
import com.sakila.models.Rental;
import java.util.List;
import java.util.Scanner;

public class RentalUI {
    private final RentalController rentalController; // Controlador que maneja la lógica de negocio de las rentas
    private final Scanner scanner; // Escáner para leer la entrada del usuario

    // Constructor que inicializa el controlador de rentas y el escáner
    public RentalUI(RentalController rentalController) {
        this.rentalController = rentalController;
        this.scanner = new Scanner(System.in);
    }

    // Muestra el menú principal de la gestión de rentas
    public void displayMenu() {
        int option; // Variable para almacenar la opción seleccionada por el usuario
        do {
            // Muestra el menú de opciones
            System.out.println("\n--- Gestión de Rentas ---");
            System.out.println("1. Ver todas las rentas");
            System.out.println("2. Buscar renta por ID");
            System.out.println("3. Agregar nueva renta");
            System.out.println("4. Actualizar renta");
            System.out.println("5. Eliminar renta");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt(); // Lee la opción del usuario
            scanner.nextLine(); // Limpiar el buffer después de leer un número

            // Ejecuta la acción correspondiente a la opción seleccionada
            switch (option) {
                case 1 -> listRentals(); // Listar todas las rentas
                case 2 -> findRentalById(); // Buscar renta por ID
                case 3 -> addRental(); // Agregar una nueva renta
                case 4 -> updateRental(); // Actualizar una renta existente
                case 5 -> deleteRental(); // Eliminar una renta
                case 0 -> System.out.println("Saliendo..."); // Salir del menú
                default -> System.out.println("Opción inválida. Intente nuevamente."); // Opción inválida
            }
        } while (option != 0); // El ciclo continúa hasta que el usuario elige salir
    }

    // Muestra una lista de todas las rentas, con límites y desplazamiento (paginación)
    private void listRentals() {
        System.out.println("\n--- Lista de Rentas ---");
        int limit = readInt("Ingrese el límite de registros a mostrar: "); // Lee el límite de registros
        int offset = readInt("Ingrese el offset (inicio): "); // Lee el valor de desplazamiento (página)

        // Obtiene las rentas desde el controlador usando el límite y el desplazamiento
        List<Rental> rentals = rentalController.getAllRentals(limit, offset);
        if (rentals.isEmpty()) {
            System.out.println("No hay rentas disponibles.");
        } else {
            // Muestra todas las rentas obtenidas
            rentals.forEach(System.out::println);
        }
    }

    // Busca una renta por su ID
    private void findRentalById() {
        int id = readInt("Ingrese el ID de la renta: "); // Lee el ID de la renta
        Rental rental = rentalController.getRentalById(id); // Busca la renta por ID en el controlador
        if (rental != null) {
            // Muestra los detalles de la renta encontrada
            System.out.println("\nDetalles de la renta:");
            System.out.println(rental);
        } else {
            System.out.println("Renta no encontrada."); // Si no se encuentra, muestra un mensaje de error
        }
    }

    // Agrega una nueva renta
    private void addRental() {
        int customerId = readInt("Ingrese el ID del cliente: "); // Lee el ID del cliente
        int inventoryId = readInt("Ingrese el ID del inventario: "); // Lee el ID del inventario

        // Crea una nueva renta con los datos proporcionados
        Rental rental = new Rental(customerId, inventoryId); 
        // Llama al controlador para agregar la renta y muestra el mensaje correspondiente
        String message = rentalController.addRental(rental);
        System.out.println(message);
    }

    // Actualiza una renta existente
    private void updateRental() {
        int id = readInt("Ingrese el ID de la renta a actualizar: "); // Lee el ID de la renta a actualizar
        int customerId = readInt("Ingrese el nuevo ID del cliente: "); // Lee el nuevo ID del cliente
        int inventoryId = readInt("Ingrese el nuevo ID del inventario: "); // Lee el nuevo ID del inventario

        // Crea una nueva renta con los datos actualizados
        Rental rental = new Rental(id, customerId, inventoryId);
        // Llama al controlador para actualizar la renta y muestra el mensaje correspondiente
        String message = rentalController.updateRental(id, rental);
        System.out.println(message);
    }

    // Elimina una renta por su ID
    private void deleteRental() {
        int id = readInt("Ingrese el ID de la renta a eliminar: "); // Lee el ID de la renta a eliminar
        // Llama al controlador para eliminar la renta y muestra el mensaje correspondiente
        String message = rentalController.deleteRental(id);
        System.out.println(message);
    }

    // Método auxiliar para leer un número entero con validación
    private int readInt(String prompt) {
        System.out.print(prompt); // Muestra el mensaje de solicitud
        while (!scanner.hasNextInt()) { // Verifica si la entrada es un número entero
            System.out.println("Por favor ingresa un número válido.");
            scanner.next(); // Limpiar el buffer en caso de error
            System.out.print(prompt); // Solicita la entrada nuevamente
        }
        return scanner.nextInt(); // Devuelve el número leído
    }
}
