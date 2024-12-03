
package com.sakila.entities;

import com.sakila.models.Rental;

import java.util.ArrayList;
import java.util.List;

public class RentalEntity {

    private List<Rental> rentals;  // Lista que almacenará todas las rentas

    // Constructor que inicializa la lista de rentas
    public RentalEntity() {
        this.rentals = new ArrayList<>();
    }

    // Método para obtener todas las rentas con paginación
    public List<Rental> getAll(int limit, int offset) {
        int start = Math.min(offset, rentals.size()); // Asegura que el inicio esté dentro del rango de la lista
        int end = Math.min(offset + limit, rentals.size()); // Asegura que el fin no exceda el tamaño de la lista
        return rentals.subList(start, end); // Retorna la sublista con los elementos dentro del rango de paginación
    }

    // Método para obtener una renta por su ID
    public Rental getById(int id) {
        // Busca la renta con el ID dado
        return rentals.stream()
                      .filter(rental -> rental.getId() == id)  // Filtra por el ID de la renta
                      .findFirst()  // Toma el primer elemento encontrado
                      .orElse(null);  // Si no se encuentra, retorna null
    }

    // Método para agregar una nueva renta
    public boolean add(Rental rental) {
        rental.setId(rentals.size() + 1);  // Asigna un ID único basado en el tamaño actual de la lista
        return rentals.add(rental);  // Agrega la nueva renta a la lista
    }

    // Método para actualizar una renta existente
    public boolean update(int id, Rental rental) {
        Rental existingRental = getById(id);  // Obtiene la renta existente por ID
        if (existingRental != null) {
            // Actualiza los atributos de la renta
            existingRental.setCustomerId(rental.getCustomerId());
            existingRental.setInventoryId(rental.getInventoryId());
            return true;  // Si se actualizó, retorna true
        }
        return false;  // Retorna false si no se encontró la renta
    }

    // Método para eliminar una renta por ID
    public boolean delete(int id) {
        Rental rental = getById(id);  // Busca la renta con el ID dado
        if (rental != null) {
            return rentals.remove(rental);  // Elimina la renta de la lista y retorna true si se eliminó
        }
        return false;  // Retorna false si no se encontró la renta
    }
}
