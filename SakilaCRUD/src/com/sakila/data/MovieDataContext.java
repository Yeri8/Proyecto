
package com.sakila.data;

import com.sakila.models.Movie;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de DataContext para manejar operaciones CRUD sobre películas en una base de datos.
 */
public class MovieDataContext implements DataContext<Movie> {

    // Configuración de la conexión a la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/sakila"; // URL de la base de datos
    private static final String USER = "root"; // Usuario de la base de datos
    private static final String PASSWORD = "root"; // Contraseña de la base de datos

    // Método para establecer la conexión con la base de datos
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD); // Establece la conexión con los parámetros especificados
    }

    /**
     * Obtiene todos los registros de películas desde la base de datos.
     * 
     * @return Una lista de objetos Movie.
     */
    @Override
    public List<Movie> getAll() {
        List<Movie> movies = new ArrayList<>(); // Lista donde se almacenarán las películas
        String query = "SELECT * FROM film"; // Consulta SQL para obtener todas las películas

        // Usamos try-with-resources para asegurarnos de que los recursos se cierren automáticamente
        try (Connection connection = getConnection(); 
             Statement statement = connection.createStatement(); 
             ResultSet resultSet = statement.executeQuery(query)) {

            // Procesamos los resultados de la consulta y los agregamos a la lista
            while (resultSet.next()) {
                int id = resultSet.getInt("film_id"); // Obtenemos el ID de la película
                String title = resultSet.getString("title"); // Obtenemos el título de la película
                String description = resultSet.getString("description"); // Obtenemos la descripción de la película
                movies.add(new Movie(id, title, description)); // Creamos un objeto Movie y lo agregamos a la lista
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime el error si ocurre una excepción
        }
        return movies; // Devuelve la lista de películas
    }

    /**
     * Obtiene una película específica por su ID desde la base de datos.
     * 
     * @param id El ID de la película.
     * @return El objeto Movie correspondiente o null si no se encuentra.
     */
    @Override
    public Movie get(int id) {
        Movie movie = null; // Inicializamos el objeto Movie como null
        String query = "SELECT * FROM film WHERE film_id = ?"; // Consulta SQL con un parámetro para el ID de la película

        try (Connection connection = getConnection(); 
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id); // Establecemos el valor del parámetro ID en la consulta
            ResultSet resultSet = statement.executeQuery(); // Ejecutamos la consulta

            if (resultSet.next()) {
                String title = resultSet.getString("title"); // Obtenemos el título
                String description = resultSet.getString("description"); // Obtenemos la descripción
                movie = new Movie(id, title, description); // Creamos el objeto Movie con los datos obtenidos
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
        return movie; // Devuelve la película encontrada o null si no existe
    }

    /**
     * Inserta una nueva película en la base de datos.
     * 
     * @param movie El objeto Movie a insertar.
     * @return true si la inserción fue exitosa, false si hubo un error.
     */
    @Override
    public boolean post(Movie movie) {
        String query = "INSERT INTO film (title, description) VALUES (?, ?)"; // Consulta SQL para insertar una película

        try (Connection connection = getConnection(); 
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Establecemos los parámetros para la consulta
            statement.setString(1, movie.getTitle());
            statement.setString(2, movie.getDescription());

            int rowsInserted = statement.executeUpdate(); // Ejecutamos la inserción
            return rowsInserted > 0; // Si se insertaron filas, la inserción fue exitosa
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
        return false; // Retorna false si la inserción falla
    }

    /**
     * Actualiza los datos de una película existente en la base de datos.
     * 
     * @param id El ID de la película a actualizar.
     * @param movie El objeto Movie con los nuevos datos.
     * @return true si la actualización fue exitosa, false si hubo un error.
     */
    @Override
    public boolean put(int id, Movie movie) {
        String query = "UPDATE film SET title = ?, description = ? WHERE film_id = ?"; // Consulta SQL para actualizar una película

        try (Connection connection = getConnection(); 
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Establecemos los parámetros para la consulta
            statement.setString(1, movie.getTitle());
            statement.setString(2, movie.getDescription());
            statement.setInt(3, id);

            int rowsUpdated = statement.executeUpdate(); // Ejecutamos la actualización
            return rowsUpdated > 0; // Si se actualizaron filas, la actualización fue exitosa
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
        return false; // Retorna false si la actualización falla
    }

    /**
     * Elimina una película de la base de datos.
     * 
     * @param id El ID de la película a eliminar.
     * @return true si la eliminación fue exitosa, false si hubo un error.
     */
    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM film WHERE film_id = ?"; // Consulta SQL para eliminar una película

        try (Connection connection = getConnection(); 
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id); // Establecemos el parámetro ID para la consulta

            int rowsDeleted = statement.executeUpdate(); // Ejecutamos la eliminación
            return rowsDeleted > 0; // Si se eliminaron filas, la eliminación fue exitosa
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
        return false; // Retorna false si la eliminación falla
    }
}

