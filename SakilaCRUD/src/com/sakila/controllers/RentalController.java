
package com.sakila.controllers;

import com.sakila.models.Rental;
import com.sakila.entities.RentalEntity;

import java.util.List;

public class RentalController {

    private final RentalEntity rentalEntity;

    public RentalController() {
        this.rentalEntity = new RentalEntity();
    }

    // Obtener todas las rentas con paginación
    public List<Rental> getAllRentals(int limit, int offset) {
        return rentalEntity.getAll(limit, offset);
    }

    // Obtener una renta por ID
    public Rental getRentalById(int id) {
        return rentalEntity.getById(id);
    }

    // Agregar una nueva renta
    public String addRental(Rental rental) {
        if (rentalEntity.add(rental)) {
            return "Renta agregada con éxito.";
        }
        return "Error al agregar renta.";
    }

    // Actualizar una renta
    public String updateRental(int id, Rental rental) {
        if (rentalEntity.update(id, rental)) {
            return "Renta actualizada con éxito.";
        }
        return "Error al actualizar renta.";
    }

    // Eliminar una renta
    public String deleteRental(int id) {
        if (rentalEntity.delete(id)) {
            return "Renta eliminada con éxito.";
        }
        return "Error al eliminar renta.";
    }
}
