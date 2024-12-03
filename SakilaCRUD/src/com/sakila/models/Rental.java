
package com.sakila.models;

/**
 * Clase que representa un alquiler en el sistema.
 * Un alquiler tiene un ID único, un ID de cliente y un ID de inventario asociados.
 */
public class Rental {
    // Atributos de la clase Rental
    private int id;            // ID único del alquiler
    private int customerId;    // ID del cliente que realiza el alquiler
    private int inventoryId;   // ID del inventario relacionado con el alquiler

    /**
     * Constructor completo que recibe todos los parámetros.
     * @param id el ID del alquiler.
     * @param customerId el ID del cliente que realiza el alquiler.
     * @param inventoryId el ID del inventario relacionado con el alquiler.
     */
    public Rental(int id, int customerId, int inventoryId) {
        this.id = id;
        this.customerId = customerId;
        this.inventoryId = inventoryId;
    }

    /**
     * Constructor adicional que no requiere un ID (útil al crear un alquiler sin ID asignado).
     * @param customerId el ID del cliente que realiza el alquiler.
     * @param inventoryId el ID del inventario relacionado con el alquiler.
     */
    public Rental(int customerId, int inventoryId) {
        this.id = 0; // Valor predeterminado para el ID (se sobrescribirá si es necesario)
        this.customerId = customerId;
        this.inventoryId = inventoryId;
    }

    // Métodos getters y setters para acceder y modificar los atributos de Rental

    /**
     * Obtiene el ID del alquiler.
     * @return el ID del alquiler.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del alquiler.
     * @param id el nuevo ID del alquiler.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el ID del cliente.
     * @return el ID del cliente.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Establece el ID del cliente.
     * @param customerId el nuevo ID del cliente.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Obtiene el ID del inventario.
     * @return el ID del inventario.
     */
    public int getInventoryId() {
        return inventoryId;
    }

    /**
     * Establece el ID del inventario.
     * @param inventoryId el nuevo ID del inventario.
     */
    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }
}
