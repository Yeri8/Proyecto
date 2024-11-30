
package com.sakila.models;

public class Inventory {
    private int id;
    private int movieId;
    private int storeId;

    public Inventory(int id, int movieId, int storeId) {
        this.id = id;
        this.movieId = movieId;
        this.storeId = storeId;
    }

    public int getId() {
        return id;
    }

    public int getMovieId() {
        return movieId;
    }

    public int getStoreId() {
        return storeId;
    }
}

