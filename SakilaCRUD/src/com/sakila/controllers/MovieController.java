
package com.sakila.controllers;

import com.sakila.models.Movie;
import com.sakila.data.MovieEntity;

import java.util.List;

public class MovieController {
    private final MovieEntity movieEntity;

    public MovieController(MovieEntity movieEntity) {
        this.movieEntity = movieEntity;
    }

    public List<Movie> getAllMovies() {
        return movieEntity.getAll();
    }

    public Movie getMovieById(int id) {
        return movieEntity.get(id);
    }

    public boolean addMovie(Movie movie) {
        return movieEntity.post(movie);
    }

    public boolean updateMovie(int id, Movie movie) {
        return movieEntity.put(id, movie);
    }

    public boolean deleteMovie(int id) {
        return movieEntity.delete(id);
    }
}

