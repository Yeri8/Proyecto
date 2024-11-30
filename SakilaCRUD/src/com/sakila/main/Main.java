

package com.sakila.main;

import com.sakila.controllers.RentalController;
import com.sakila.models.Rental;

public class Main {
    public static void main(String[] args) {
        RentalController rentalController = new RentalController();

        // Agregar rentas
        rentalController.addRental(new Rental(0, 1, 100));
        rentalController.addRental(new Rental(0, 2, 101));
        rentalController.addRental(new Rental(0, 3, 102));

        // Obtener todas las rentas (paginación)
        System.out.println("Todas las rentas (limit=2, offset=0):");
        rentalController.getAllRentals(2, 0).forEach(rental ->
                System.out.println("ID: " + rental.getId() + ", Customer ID: " + rental.getCustomerId()));

        // Obtener una renta por ID
        System.out.println("\nRenta con ID 1:");
        Rental rental = rentalController.getRentalById(1);
        if (rental != null) {
            System.out.println("ID: " + rental.getId() + ", Customer ID: " + rental.getCustomerId());
        } else {
            System.out.println("Renta no encontrada.");
        }

        // Actualizar una renta
        System.out.println("\nActualizando renta con ID 2...");
        String updateResult = rentalController.updateRental(2, new Rental(2, 99, 200));
        System.out.println(updateResult);

        // Eliminar una renta
        System.out.println("\nEliminando renta con ID 3...");
        String deleteResult = rentalController.deleteRental(3);
        System.out.println(deleteResult);

        // Obtener todas las rentas nuevamente
        System.out.println("\nTodas las rentas después de modificaciones:");
        rentalController.getAllRentals(10, 0).forEach(r ->
                System.out.println("ID: " + r.getId() + ", Customer ID: " + r.getCustomerId()));
    }
}
