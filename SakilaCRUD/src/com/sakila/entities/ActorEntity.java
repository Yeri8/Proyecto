
package com.sakila.entities;

import com.sakila.models.Actor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActorEntity {
    private static final String SELECT_ALL_ACTORS = "SELECT actor_id, first_name, last_name FROM actor LIMIT ? OFFSET ?";
    private static final String SELECT_ACTOR_BY_ID = "SELECT actor_id, first_name, last_name FROM actor WHERE actor_id = ?";
    private static final String INSERT_ACTOR = "INSERT INTO actor (first_name, last_name) VALUES (?, ?)";
    private static final String UPDATE_ACTOR = "UPDATE actor SET first_name = ?, last_name = ? WHERE actor_id = ?";
    private static final String DELETE_ACTOR = "DELETE FROM actor WHERE actor_id = ?";

    // Método para agregar un actor
    public boolean add(Actor actor, Connection conn) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(INSERT_ACTOR)) {
            stmt.setString(1, actor.getFirstName());
            stmt.setString(2, actor.getLastName());
            return stmt.executeUpdate() > 0;
        }
    }

    // Método para actualizar un actor
    public boolean update(int actorId, Actor actor, Connection conn) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(UPDATE_ACTOR)) {
            stmt.setString(1, actor.getFirstName());
            stmt.setString(2, actor.getLastName());
            stmt.setInt(3, actorId);
            return stmt.executeUpdate() > 0;
        }
    }

    // Método para eliminar un actor
    public boolean delete(int actorId, Connection conn) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(DELETE_ACTOR)) {
            stmt.setInt(1, actorId);
            return stmt.executeUpdate() > 0;
        }
    }

    // Método para obtener todos los actores con paginación
    public List<Actor> getAll(int limit, int offset, Connection conn) throws SQLException {
        List<Actor> actors = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_ACTORS)) {
            stmt.setInt(1, limit);
            stmt.setInt(2, offset);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    actors.add(new Actor(
                        rs.getInt("actor_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                    ));
                }
            }
        }
        return actors;
    }

    // Método para obtener un actor por su ID
    public Actor getById(int actorId, Connection conn) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(SELECT_ACTOR_BY_ID)) {
            stmt.setInt(1, actorId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Actor(
                        rs.getInt("actor_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                    );
                }
            }
        }
        return null;  // Si no encuentra el actor
    }
}

