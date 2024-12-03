

package com.sakila.main;

import com.sakila.controllers.RentalController;
import com.sakila.models.Rental;

public class Main {
    public static void main(String[] args) {
        // Crear una instancia del controlador de rentas
        RentalController rentalController = new RentalController();

        // Agregar rentas con customerId e inventoryId
        rentalController.addRental(new Rental(0, 1, 100));  // Rentar a cliente 1 con inventario 100
        rentalController.addRental(new Rental(0, 2, 101));  // Rentar a cliente 2 con inventario 101
        rentalController.addRental(new Rental(0, 3, 102));  // Rentar a cliente 3 con inventario 102

        // Obtener todas las rentas con paginación
        System.out.println("Todas las rentas (limit=2, offset=0):");
        // Llamar al controlador para obtener rentas con un límite de 2 y un desplazamiento (offset) de 0
        rentalController.getAllRentals(2, 0).forEach(rental ->
                // Imprimir las rentas obtenidas con su ID y el customerId
                System.out.println("ID: " + rental.getId() + ", Customer ID: " + rental.getCustomerId()));

        // Obtener una renta por ID
        System.out.println("\nRenta con ID 1:");
        // Llamar al controlador para obtener la renta con ID 1
        Rental rental = rentalController.getRentalById(1);
        if (rental != null) {
            // Si la renta existe, imprimir sus detalles
            System.out.println("ID: " + rental.getId() + ", Customer ID: " + rental.getCustomerId());
        } else {
            // Si no se encuentra la renta, mostrar un mensaje
            System.out.println("Renta no encontrada.");
        }

        // Actualizar una renta con ID 2
        System.out.println("\nActualizando renta con ID 2...");
        // Crear una nueva renta con los datos actualizados
        String updateResult = rentalController.updateRental(2, new Rental(2, 99, 200));
        // Imprimir el resultado de la actualización
        System.out.println(updateResult);

        // Eliminar una renta con ID 3
        System.out.println("\nEliminando renta con ID 3...");
        // Llamar al controlador para eliminar la renta con ID 3
        String deleteResult = rentalController.deleteRental(3);
        // Imprimir el resultado de la eliminación
        System.out.println(deleteResult);

        // Obtener todas las rentas después de las modificaciones
        System.out.println("\nTodas las rentas después de modificaciones:");
        // Obtener todas las rentas con paginación y mostrar los resultados
        rentalController.getAllRentals(10, 0).forEach(r ->
                System.out.println("ID: " + r.getId() + ", Customer ID: " + r.getCustomerId()));
    }
}
