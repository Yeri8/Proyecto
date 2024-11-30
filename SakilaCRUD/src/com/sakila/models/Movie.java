
package com.sakila.models;

/**
 * Representa una película.
 */
public class Movie {
    private int id;
    private String title;
    private String description;
    private String category;  // Si estás utilizando una categoría, también la puedes agregar aquí

    // Constructor vacío (para crear un objeto vacío o para setters)
    public Movie() {
    }

    // Constructor con tres parámetros: id, title, description
    public Movie(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    // Constructor con cuatro parámetros: id, title, description, category
    public Movie(int id, String title, String description, String category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
    }

    // Getters y setters (si es necesario)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Método toString() para representar la película como texto
    @Override
    public String toString() {
        return "Movie{id=" + id + ", title='" + title + "', description='" + description + "', category='" + category + "'}";
    }
}

