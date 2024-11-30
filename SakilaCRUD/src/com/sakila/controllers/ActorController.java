

package com.sakila.controllers;

import com.sakila.models.Actor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActorController {

    // Obtener todos los actores (con paginación)
    public List<Actor> getAllActors(int limit, int offset, Connection conn) throws SQLException {
        List<Actor> actors = new ArrayList<>();
        String query = "SELECT actor_id, first_name, last_name FROM actor LIMIT ? OFFSET ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, limit);
            stmt.setInt(2, offset);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Actor actor = new Actor(rs.getInt("actor_id"), rs.getString("first_name"), rs.getString("last_name"));
                actors.add(actor);
            }
        }
        return actors;
    }

    // Obtener un actor por ID
    public Actor getActorById(int id, Connection conn) throws SQLException {
        String query = "SELECT actor_id, first_name, last_name FROM actor WHERE actor_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Actor(rs.getInt("actor_id"), rs.getString("first_name"), rs.getString("last_name"));
            }
        }
        return null;
    }

    // Agregar un nuevo actor
    public boolean addActor(Actor actor, Connection conn) throws SQLException {
        String query = "INSERT INTO actor (first_name, last_name) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, actor.getFirstName());
            stmt.setString(2, actor.getLastName());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Actualizar un actor
    public boolean updateActor(int id, Actor actor, Connection conn) throws SQLException {
        String query = "UPDATE actor SET first_name = ?, last_name = ? WHERE actor_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, actor.getFirstName());
            stmt.setString(2, actor.getLastName());
            stmt.setInt(3, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Eliminar un actor
    public boolean deleteActor(int id, Connection conn) throws SQLException {
        String query = "DELETE FROM actor WHERE actor_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}

