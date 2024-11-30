
package com.sakila.entities;

import com.sakila.models.Store;
import interfaces.CRUDOperations;

import java.sql.*;
import java.util.ArrayList;

public class StoreEntity implements CRUDOperations<Store> {

    @Override
    public ArrayList<Store> getAll(int limit, int offset, Connection conn) throws SQLException {
        ArrayList<Store> stores = new ArrayList<>();
        String query = "SELECT * FROM store LIMIT ? OFFSET ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, limit);
            stmt.setInt(2, offset);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
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

    @Override
    public Store getById(int id, Connection conn) throws SQLException {
        String query = "SELECT * FROM store WHERE store_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Store(
                    rs.getInt("store_id"),
                    rs.getInt("manager_staff_id"),
                    rs.getString("address")
                );
            }
        }
        return null;
    }

    @Override
    public boolean add(Store store, Connection conn) throws SQLException {
        String query = "INSERT INTO store (manager_staff_id, address) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, store.getManagerStaffId());
            stmt.setString(2, store.getAddress());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean update(int id, Store store, Connection conn) throws SQLException {
        String query = "UPDATE store SET manager_staff_id = ?, address = ? WHERE store_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, store.getManagerStaffId());
            stmt.setString(2, store.getAddress());
            stmt.setInt(3, id);
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(int id, Connection conn) throws SQLException {
        String query = "DELETE FROM store WHERE store_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
}

