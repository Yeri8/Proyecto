
package com.sakila.entities;

import com.sakila.models.Store;
import interfaces.CRUDOperations;

import java.sql.*;
import java.util.ArrayList;

/**
 * Implementación de las operaciones CRUD para la entidad Store.
 * Esta clase maneja la interacción con la base de datos para crear, leer, actualizar y eliminar registros de tiendas (store).
 */
public class StoreEntity implements CRUDOperations<Store> {

    /**
     * Obtiene todos los registros de tiendas desde la base de datos con límites y desplazamientos para la paginación.
     * @param limit el número máximo de registros a recuperar.
     * @param offset el desplazamiento de la consulta (para paginación).
     * @param conn la conexión a la base de datos.
     * @return una lista de tiendas recuperadas desde la base de datos.
     * @throws SQLException si ocurre un error durante la ejecución de la consulta.
     */
    @Override
    public ArrayList<Store> getAll(int limit, int offset, Connection conn) throws SQLException {
        ArrayList<Store> stores = new ArrayList<>();
        // Consulta SQL para obtener tiendas con limitación y desplazamiento
        String query = "SELECT * FROM store LIMIT ? OFFSET ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            // Establece los parámetros de la consulta (limit y offset)
            stmt.setInt(1, limit);
            stmt.setInt(2, offset);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Crea una nueva instancia de Store con los datos del resultado
                Store store = new Store(
                    rs.getInt("store_id"),
                    rs.getInt("manager_staff_id"),
                    rs.getString("address")
                );
                stores.add(store);
            }
        }
        return stores;
    }

    /**
     * Obtiene un registro de tienda por su ID desde la base de datos.
     * @param id el ID de la tienda a recuperar.
     * @param conn la conexión a la base de datos.
     * @return un objeto Store con los datos de la tienda, o null si no se encuentra.
     * @throws SQLException si ocurre un error durante la ejecución de la consulta.
     */
    @Override
    public Store getById(int id, Connection conn) throws SQLException {
        String query = "SELECT * FROM store WHERE store_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            // Establece el parámetro ID en la consulta
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Si se encuentra la tienda, crea y devuelve el objeto Store
                return new Store(
                    rs.getInt("store_id"),
                    rs.getInt("manager_staff_id"),
                    rs.getString("address")
                );
            }
        }
        // Si no se encuentra la tienda, devuelve null
        return null;
    }

    /**
     * Agrega una nueva tienda a la base de datos.
     * @param store el objeto Store a agregar.
     * @param conn la conexión a la base de datos.
     * @return true si la tienda fue agregada correctamente, false en caso contrario.
     * @throws SQLException si ocurre un error durante la ejecución de la consulta.
     */
    @Override
    public boolean add(Store store, Connection conn) throws SQLException {
        String query = "INSERT INTO store (manager_staff_id, address) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            // Establece los parámetros para la inserción
            stmt.setInt(1, store.getManagerStaffId());
            stmt.setString(2, store.getAddress());
            // Ejecuta la actualización y devuelve true si se insertó al menos un registro
            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Actualiza los datos de una tienda en la base de datos.
     * @param id el ID de la tienda a actualizar.
     * @param store el objeto Store con los nuevos datos.
     * @param conn la conexión a la base de datos.
     * @return true si la tienda fue actualizada correctamente, false en caso contrario.
     * @throws SQLException si ocurre un error durante la ejecución de la consulta.
     */
    @Override
    public boolean update(int id, Store store, Connection conn) throws SQLException {
        String query = "UPDATE store SET manager_staff_id = ?, address = ? WHERE store_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            // Establece los parámetros para la actualización
            stmt.setInt(1, store.getManagerStaffId());
            stmt.setString(2, store.getAddress());
            stmt.setInt(3, id);
            // Ejecuta la actualización y devuelve true si se actualizó al menos un registro
            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Elimina una tienda de la base de datos.
     * @param id el ID de la tienda a eliminar.
     * @param conn la conexión a la base de datos.
     * @return true si la tienda fue eliminada correctamente, false en caso contrario.
     * @throws SQLException si ocurre un error durante la ejecución de la consulta.
     */
    @Override
    public boolean delete(int id, Connection conn) throws SQLException {
        String query = "DELETE FROM store WHERE store_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            // Establece el parámetro ID en la consulta
            stmt.setInt(1, id);
            // Ejecuta la eliminación y devuelve true si se eliminó al menos un registro
            return stmt.executeUpdate() > 0;
        }
    }
}
