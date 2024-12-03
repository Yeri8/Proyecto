
package com.sakila.controllers;

import com.sakila.models.Rental;
import com.sakila.entities.RentalEntity;

import java.util.List;

public class RentalController {

    // Instancia de RentalEntity para interactuar con los datos de las rentas
    private final RentalEntity rentalEntity;

    // Constructor que inicializa la entidad RentalEntity
    public RentalController() {
        this.rentalEntity = new RentalEntity();
    }

    // Método para obtener todas las rentas con paginación
    public List<Rental> getAllRentals(int limit, int offset) {
        // Llama al método getAll de RentalEntity para obtener las rentas con los límites y desplazamiento indicados
        return rentalEntity.getAll(limit, offset);
    }

    // Método para obtener una renta por ID
    public Rental getRentalById(int id) {
        // Llama al método getById de RentalEntity para obtener una renta específica por su ID
        return rentalEntity.getById(id);
    }

    // Método para agregar una nueva renta
    public String addRental(Rental rental) {
        // Llama al método add de RentalEntity para agregar la renta
        if (rentalEntity.add(rental)) {
            return "Renta agregada con éxito.";  // Retorna mensaje de éxito si la renta se agrega correctamente
        }
        return "Error al agregar renta.";  // Si algo falla, retorna mensaje de error
    }

    // Método para actualizar una renta
    public String updateRental(int id, Rental rental) {
        // Llama al método update de RentalEntity para actualizar la renta con el ID especificado
        if (rentalEntity.update(id, rental)) {
            return "Renta actualizada con éxito.";  // Retorna mensaje de éxito si la renta se actualiza correctamente
        }
        return "Error al actualizar renta.";  // Si algo falla, retorna mensaje de error
    }

    // Método para eliminar una renta
    public String deleteRental(int id) {
        // Llama al método delete de RentalEntity para eliminar la renta con el ID especificado
        if (rentalEntity.delete(id)) {
            return "Renta eliminada con éxito.";  // Retorna mensaje de éxito si la renta se elimina correctamente
        }
        return "Error al eliminar renta.";  // Si algo falla, retorna mensaje de error
    }
}
