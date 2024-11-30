
package com.sakila.data;

import com.sakila.models.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de DataContext para manejar operaciones CRUD sobre películas en una base de datos.
 */
public class MovieDataContext implements DataContext<Movie> {

    private static final String URL = "jdbc:mysql://localhost:3306/sakila"; // Cambia el URL y la base de datos según tu configuración
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    // Establece la conexión con la base de datos
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public List<Movie> getAll() {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT * FROM film"; // Cambié 'movie' por 'film'

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("film_id");  // Cambié 'movie_id' por 'film_id'
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                movies.add(new Movie(id, title, description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    @Override
    public Movie get(int id) {
        Movie movie = null;
        String query = "SELECT * FROM film WHERE film_id = ?"; // Cambié 'movie' por 'film'

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                movie = new Movie(id, title, description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movie;
    }

    @Override
    public boolean post(Movie movie) {
        String query = "INSERT INTO film (title, description) VALUES (?, ?)"; // Cambié 'movie' por 'film'

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, movie.getTitle());
            statement.setString(2, movie.getDescription());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean put(int id, Movie movie) {
        String query = "UPDATE film SET title = ?, description = ? WHERE film_id = ?"; // Cambié 'movie' por 'film'

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, movie.getTitle());
            statement.setString(2, movie.getDescription());
            statement.setInt(3, id);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM film WHERE film_id = ?"; // Cambié 'movie' por 'film'

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

