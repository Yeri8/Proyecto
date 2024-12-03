
package com.sakila.controllers;

import com.sakila.models.Actor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActorController {

    // Obtener todos los actores con paginación: límite y desplazamiento
    public List<Actor> getAllActors(int limit, int offset, Connection conn) throws SQLException {
        List<Actor> actors = new ArrayList<>();
        String query = "SELECT actor_id, first_name, last_name FROM actor LIMIT ? OFFSET ?";  // Query con paginación
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, limit);  // Se establece el límite de resultados
            stmt.setInt(2, offset); // Se establece el desplazamiento
            ResultSet rs = stmt.executeQuery();  // Ejecutar la consulta
            while (rs.next()) {
                // Crear un actor y añadirlo a la lista
                Actor actor = new Actor(rs.getInt("actor_id"), rs.getString("first_name"), rs.getString("last_name"));
                actors.add(actor);
            }
        }
        return actors;  // Retornar la lista de actores
    }

    // Obtener un actor por su ID
    public Actor getActorById(int id, Connection conn) throws SQLException {
        String query = "SELECT actor_id, first_name, last_name FROM actor WHERE actor_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);  // Establecer el ID del actor
            ResultSet rs = stmt.executeQuery();  // Ejecutar la consulta
            if (rs.next()) {
                // Retornar el actor encontrado
                return new Actor(rs.getInt("actor_id"), rs.getString("first_name"), rs.getString("last_name"));
            }
        }
        return null;  // Retornar null si no se encuentra el actor
    }

    // Agregar un nuevo actor a la base de datos
    public boolean addActor(Actor actor, Connection conn) throws SQLException {
        String query = "INSERT INTO actor (first_name, last_name) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, actor.getFirstName());  // Establecer el nombre del actor
            stmt.setString(2, actor.getLastName());   // Establecer el apellido del actor
            int rowsAffected = stmt.executeUpdate();  // Ejecutar la actualización
            return rowsAffected > 0;  // Retornar true si se insertó correctamente
        }
    }

    // Actualizar los datos de un actor en la base de datos
    public boolean updateActor(int id, Actor actor, Connection conn) throws SQLException {
        String query = "UPDATE actor SET first_name = ?, last_name = ? WHERE actor_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, actor.getFirstName());  // Establecer el nuevo nombre
            stmt.setString(2, actor.getLastName());   // Establecer el nuevo apellido
            stmt.setInt(3, id);  // Establecer el ID del actor a actualizar
            int rowsAffected = stmt.executeUpdate();  // Ejecutar la actualización
            return rowsAffected > 0;  // Retornar true si la actualización fue exitosa
        }
    }

    // Eliminar un actor de la base de datos por su ID
    public boolean deleteActor(int id, Connection conn) throws SQLException {
        String query = "DELETE FROM actor WHERE actor_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);  // Establecer el ID del actor a eliminar
            int rowsAffected = stmt.executeUpdate();  // Ejecutar la eliminación
            return rowsAffected > 0;  // Retornar true si la eliminación fue exitosa
        }
    }
}

