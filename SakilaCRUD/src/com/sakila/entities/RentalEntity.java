
package com.sakila.entities;

import com.sakila.models.Rental;

import java.util.ArrayList;
import java.util.List;

public class RentalEntity {

    private List<Rental> rentals;

    public RentalEntity() {
        this.rentals = new ArrayList<>();
    }

    // Método para obtener todas las rentas con paginación
    public List<Rental> getAll(int limit, int offset) {
        int start = Math.min(offset, rentals.size()); // Aseguramos que el inicio esté dentro del rango
        int end = Math.min(offset + limit, rentals.size()); // Aseguramos que el fin no exceda el tamaño de la lista
        return rentals.subList(start, end); // Sublista con los resultados paginados
    }

    // Método para obtener una renta por su ID
    public Rental getById(int id) {
        return rentals.stream()
                      .filter(rental -> rental.getId() == id)
                      .findFirst()
                      .orElse(null);
    }

    // Método para agregar una nueva renta
    public boolean add(Rental rental) {
        rental.setId(rentals.size() + 1); // Asignar ID automáticamente
        return rentals.add(rental);
    }

    // Método para actualizar una renta existente
    public boolean update(int id, Rental rental) {
        Rental existingRental = getById(id);
        if (existingRental != null) {
            existingRental.setCustomerId(rental.getCustomerId());
            existingRental.setInventoryId(rental.getInventoryId());
            return true;
        }
        return false;
    }

    // Método para eliminar una renta por ID
    public boolean delete(int id) {
        Rental rental = getById(id);
        if (rental != null) {
            return rentals.remove(rental);
        }
        return false;
    }
}

