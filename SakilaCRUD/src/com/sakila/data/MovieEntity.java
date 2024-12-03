
package com.sakila.data;

import com.sakila.models.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieEntity {
    private final Connection connection;

    // Constructor que recibe una conexión a la base de datos
    public MovieEntity(Connection connection) {
        this.connection = connection;
    }

    // Obtener todas las películas con su categoría asociada
    public List<Movie> getAll() {
        List<Movie> movies = new ArrayList<>();
        String query = """
            SELECT film.film_id, film.title, film.description, 
                   COALESCE(category.name, 'No Category') AS category
            FROM film
            LEFT JOIN film_category ON film.film_id = film_category.film_id
            LEFT JOIN category ON film_category.category_id = category.category_id
        """;

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            // Itera sobre los resultados y crea los objetos Movie
            while (rs.next()) {
                Movie movie = new Movie();
                movie.setId(rs.getInt("film_id"));
                movie.setTitle(rs.getString("title"));
                movie.setDescription(rs.getString("description"));
                movie.setCategory(rs.getString("category"));
                movies.add(movie);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el inventario: " + e.getMessage());
        }
        return movies;
    }

    // Obtener una película específica por ID
    public Movie get(int id) {
        String query = """
            SELECT film.film_id, film.title, film.description, 
                   COALESCE(category.name, 'No Category') AS category
            FROM film
            LEFT JOIN film_category ON film.film_id = film_category.film_id
            LEFT JOIN category ON film_category.category_id = category.category_id
            WHERE film.film_id = ?
        """;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id); // Establece el parámetro para el ID de la película
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Si se encuentra la película, la asigna a un objeto Movie
                    Movie movie = new Movie();
                    movie.setId(rs.getInt("film_id"));
                    movie.setTitle(rs.getString("title"));
                    movie.setDescription(rs.getString("description"));
                    movie.setCategory(rs.getString("category"));
                    return movie;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la película con ID " + id + ": " + e.getMessage());
        }
        return null; // Retorna null si no se encuentra la película
    }

    // Crear una nueva película en la base de datos
    public boolean post(Movie movie) {
        String queryFilm = "INSERT INTO film (title, description) VALUES (?, ?)";
        String queryCategory = """
            INSERT INTO film_category (film_id, category_id)
            VALUES (?, (SELECT category_id FROM category WHERE name = ?))
        """;

        try (PreparedStatement stmtFilm = connection.prepareStatement(queryFilm, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement stmtCategory = connection.prepareStatement(queryCategory)) {

            // Insertar en la tabla film y obtener el ID generado
            stmtFilm.setString(1, movie.getTitle());
            stmtFilm.setString(2, movie.getDescription());
            int rows = stmtFilm.executeUpdate();
            if (rows == 0) return false; // Si no se insertó ninguna fila, retorna false

            // Obtener el ID generado para la película
            try (ResultSet rs = stmtFilm.getGeneratedKeys()) {
                if (rs.next()) {
                    int filmId = rs.getInt(1);

                    // Insertar la relación en film_category
                    stmtCategory.setInt(1, filmId); // Establece el film_id
                    stmtCategory.setString(2, movie.getCategory()); // Establece la categoría
                    stmtCategory.executeUpdate();
                }
            }
            return true; // Retorna true si la inserción fue exitosa
        } catch (SQLException e) {
            System.err.println("Error al insertar película: " + e.getMessage());
        }
        return false; // Retorna false si hubo un error
    }

    // Actualizar una película existente
    public boolean put(int id, Movie movie) {
        String queryFilm = "UPDATE film SET title = ?, description = ? WHERE film_id = ?";
        String queryCategory = """
            INSERT INTO film_category (film_id, category_id)
            VALUES (?, (SELECT category_id FROM category WHERE name = ?))
            ON CONFLICT (film_id) 
            DO UPDATE SET category_id = (SELECT category_id FROM category WHERE name = ?)
        """;

        try (PreparedStatement stmtFilm = connection.prepareStatement(queryFilm);
             PreparedStatement stmtCategory = connection.prepareStatement(queryCategory)) {

            // Actualizar la información de la película en la tabla film
            stmtFilm.setString(1, movie.getTitle());
            stmtFilm.setString(2, movie.getDescription());
            stmtFilm.setInt(3, id);
            stmtFilm.executeUpdate();

            // Insertar o actualizar la categoría en film_category
            stmtCategory.setInt(1, id);  // Establece el film_id
            stmtCategory.setString(2, movie.getCategory());  // Establece el nombre de la categoría para el INSERT
            stmtCategory.setString(3, movie.getCategory());  // Establece el nombre de la categoría para el ON CONFLICT (para actualización)
            stmtCategory.executeUpdate();
            return true; // Retorna true si la actualización fue exitosa
        } catch (SQLException e) {
            System.err.println("Error al actualizar película con ID " + id + ": " + e.getMessage());
        }
        return false; // Retorna false si hubo un error
    }

    // Eliminar una película de la base de datos
    public boolean delete(int id) {
        String queryFilmCategory = "DELETE FROM film_category WHERE film_id = ?";
        String queryFilm = "DELETE FROM film WHERE film_id = ?";

        try (PreparedStatement stmtFilmCategory = connection.prepareStatement(queryFilmCategory);
             PreparedStatement stmtFilm = connection.prepareStatement(queryFilm)) {

            // Eliminar la relación de film_category
            stmtFilmCategory.setInt(1, id);
            stmtFilmCategory.executeUpdate();

            // Eliminar la película de la tabla film
            stmtFilm.setInt(1, id);
            int rowsFilm = stmtFilm.executeUpdate();
            return rowsFilm > 0; // Si se eliminó alguna fila, retorna true
        } catch (SQLException e) {
            System.err.println("Error al eliminar película con ID " + id + ": " + e.getMessage());
        }
        return false; // Retorna false si hubo un error al eliminar
    }
}

