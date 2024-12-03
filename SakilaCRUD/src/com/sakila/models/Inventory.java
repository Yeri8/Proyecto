package com.sakila.models;

public class Inventory {
    private int id;           // ID del inventario
    private int movieId;      // ID de la película asociada
    private int storeId;      // ID de la tienda donde está el inventario

    // Constructor que inicializa los valores de id, movieId y storeId
    public Inventory(int id, int movieId, int storeId) {
        this.id = id;           // Asigna el valor del ID
        this.movieId = movieId; // Asigna el valor del ID de la película
        this.storeId = storeId; // Asigna el valor del ID de la tienda
    }

    // Método getter para obtener el ID del inventario
    public int getId() {
        return id;  // Retorna el valor del ID
    }

    // Método getter para obtener el ID de la película asociada
    public int getMovieId() {
        return movieId;  // Retorna el valor del ID de la película
    }

    // Método getter para obtener el ID de la tienda
    public int getStoreId() {
        return storeId;  // Retorna el valor del ID de la tienda
    }

    // Método setter para modificar el ID del inventario
    public void setId(int id) {
        this.id = id;  // Asigna el nuevo valor al ID
    }

    // Método setter para modificar el ID de la película
    public void setMovieId(int movieId) {
        this.movieId = movieId;  // Asigna el nuevo valor al ID de la película
    }

    // Método setter para modificar el ID de la tienda
    public void setStoreId(int storeId) {
        this.storeId = storeId;  // Asigna el nuevo valor al ID de la tienda
    }

    // Sobrescritura del método toString para representar el objeto como una cadena
    @Override
    public String toString() {
        // Devuelve una cadena con los valores de id, movieId y storeId
        return "ID: " + id + ", Movie ID: " + movieId + ", Store ID: " + storeId;
    }
}
