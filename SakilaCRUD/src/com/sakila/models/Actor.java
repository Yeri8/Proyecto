
package com.sakila.models;

/**
 * Clase que representa un Actor en el sistema.
 * Un actor tiene un identificador único, un nombre y un apellido.
 */
public class Actor {
    // Atributos de la clase Actor
    private int actorId;     // ID único del actor
    private String firstName; // Primer nombre del actor
    private String lastName;  // Apellido del actor

    /**
     * Constructor que recibe todos los parámetros.
     * @param actorId el ID único del actor.
     * @param firstName el primer nombre del actor.
     * @param lastName el apellido del actor.
     */
    public Actor(int actorId, String firstName, String lastName) {
        this.actorId = actorId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Constructor para crear un actor con nombre y apellido, sin especificar un ID (útil al agregar nuevos actores).
     * @param firstName el primer nombre del actor.
     * @param lastName el apellido del actor.
     */
    public Actor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Métodos getters y setters para acceder y modificar los atributos del actor

    /**
     * Obtiene el ID del actor.
     * @return el ID del actor.
     */
    public int getActorId() {
        return actorId;
    }

    /**
     * Establece el ID del actor.
     * @param actorId el nuevo ID del actor.
     */
    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    /**
     * Obtiene el primer nombre del actor.
     * @return el primer nombre del actor.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Establece el primer nombre del actor.
     * @param firstName el nuevo primer nombre del actor.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Obtiene el apellido del actor.
     * @return el apellido del actor.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Establece el apellido del actor.
     * @param lastName el nuevo apellido del actor.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Devuelve una representación en cadena del objeto Actor.
     * @return una cadena con los detalles del actor.
     */
    @Override
    public String toString() {
        return "Actor{id=" + actorId + ", firstName='" + firstName + "', lastName='" + lastName + "'}";
    }
}

