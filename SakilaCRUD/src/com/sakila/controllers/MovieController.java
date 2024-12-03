
package com.sakila.controllers;

import com.sakila.models.Movie;
import com.sakila.data.MovieEntity;
import java.util.List;

public class MovieController {
    // Atributo que se utiliza para interactuar con la capa de datos de las películas
    private final MovieEntity movieEntity;

    // Constructor de la clase que recibe una instancia de MovieEntity para manejar la lógica de datos
    public MovieController(MovieEntity movieEntity) {
        this.movieEntity = movieEntity;
    }

    // Método para obtener todas las películas
    public List<Movie> getAllMovies() {
        // Llama al método 'getAll' de MovieEntity para obtener la lista completa de películas
        return movieEntity.getAll();
    }

    // Método para obtener una película por su ID
    public Movie getMovieById(int id) {
        // Llama al método 'get' de MovieEntity para obtener una película específica por ID
        return movieEntity.get(id);
    }

    // Método para agregar una nueva película
    public boolean addMovie(Movie movie) {
        // Llama al método 'post' de MovieEntity para agregar una nueva película
        return movieEntity.post(movie);
    }

    // Método para actualizar los detalles de una película
    public boolean updateMovie(int id, Movie movie) {
        // Llama al método 'put' de MovieEntity para actualizar los detalles de la película con el ID dado
        return movieEntity.put(id, movie);
    }

    // Método para eliminar una película por su ID
    public boolean deleteMovie(int id) {
        // Llama al método 'delete' de MovieEntity para eliminar una película por su ID
        return movieEntity.delete(id);
    }
}
