
package com.sakila.models;

/**
 * Clase que representa a un cliente en el sistema de alquiler de películas.
 * La clase contiene los atributos básicos de un cliente como su ID, nombre, apellido y correo electrónico.
 */
public class Customer {
    private int customerId;  // Identificador único del cliente
    private String firstName; // Nombre del cliente
    private String lastName;  // Apellido del cliente
    private String email;     // Correo electrónico del cliente

    /**
     * Constructor que inicializa todos los atributos de la clase.
     * @param customerId el identificador único del cliente
     * @param firstName el nombre del cliente
     * @param lastName el apellido del cliente
     * @param email el correo electrónico del cliente
     */
    public Customer(int customerId, String firstName, String lastName, String email) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * Obtiene el ID del cliente.
     * @return el ID del cliente
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Establece el ID del cliente.
     * @param customerId el nuevo ID para el cliente
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Obtiene el nombre del cliente.
     * @return el nombre del cliente
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Establece el nombre del cliente.
     * @param firstName el nuevo nombre para el cliente
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Obtiene el apellido del cliente.
     * @return el apellido del cliente
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Establece el apellido del cliente.
     * @param lastName el nuevo apellido para el cliente
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Obtiene el correo electrónico del cliente.
     * @return el correo electrónico del cliente
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del cliente.
     * @param email el nuevo correo electrónico para el cliente
     */
    public void setEmail(String email) {
        this.email = email;
    }
}

