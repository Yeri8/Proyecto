
package com.sakila.models;

public class Actor {
    private int actorId;
    private String firstName;
    private String lastName;

    // Constructor con todos los parámetros
    public Actor(int actorId, String firstName, String lastName) {
        this.actorId = actorId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Constructor solo con nombre y apellido (para agregar actores nuevos)
    public Actor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Métodos getters y setters
    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Actor{id=" + actorId + ", firstName='" + firstName + "', lastName='" + lastName + "'}";
    }
}

