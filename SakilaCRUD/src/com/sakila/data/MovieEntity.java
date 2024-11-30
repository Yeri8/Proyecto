
package com.sakila.data;

import com.sakila.models.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieEntity {
    private final Connection connection;

    public MovieEntity(Connection connection) {
        this.connection = connection;
    }

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
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
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
        return null;
    }

    public boolean post(Movie movie) {
        String queryFilm = "INSERT INTO film (title, description) VALUES (?, ?)";
        String queryCategory = """
            INSERT INTO film_category (film_id, category_id)
            VALUES (?, (SELECT category_id FROM category WHERE name = ?))
        """;

        try (PreparedStatement stmtFilm = connection.prepareStatement(queryFilm, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement stmtCategory = connection.prepareStatement(queryCategory)) {

            // Insertar en film y obtener ID generado
            stmtFilm.setString(1, movie.getTitle());
            stmtFilm.setString(2, movie.getDescription());
            int rows = stmtFilm.executeUpdate();
            if (rows == 0) return false;

            try (ResultSet rs = stmtFilm.getGeneratedKeys()) {
                if (rs.next()) {
                    int filmId = rs.getInt(1);

                    // Insertar en film_category
                    stmtCategory.setInt(1, filmId);
                    stmtCategory.setString(2, movie.getCategory());
                    stmtCategory.executeUpdate();
                }
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar película: " + e.getMessage());
        }
        return false;
    }

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

            // Actualizar título y descripción en la tabla film
            stmtFilm.setString(1, movie.getTitle());
            stmtFilm.setString(2, movie.getDescription());
            stmtFilm.setInt(3, id);
            stmtFilm.executeUpdate();

            // Insertar o actualizar la categoría
            stmtCategory.setInt(1, id);  // Establecer film_id
            stmtCategory.setString(2, movie.getCategory());  // Establecer category name para el INSERT
            stmtCategory.setString(3, movie.getCategory());  // Establecer category name para el ON CONFLICT (para actualización)
            stmtCategory.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar película con ID " + id + ": " + e.getMessage());
        }
        return false;
    }

    public boolean delete(int id) {
        String queryFilmCategory = "DELETE FROM film_category WHERE film_id = ?";
        String queryFilm = "DELETE FROM film WHERE film_id = ?";

        try (PreparedStatement stmtFilmCategory = connection.prepareStatement(queryFilmCategory);
             PreparedStatement stmtFilm = connection.prepareStatement(queryFilm)) {

            // Eliminar de film_category
            stmtFilmCategory.setInt(1, id);
            stmtFilmCategory.executeUpdate();

            // Eliminar de film
            stmtFilm.setInt(1, id);
            int rowsFilm = stmtFilm.executeUpdate();
            return rowsFilm > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar película con ID " + id + ": " + e.getMessage());
        }
        return false;
    }
}

