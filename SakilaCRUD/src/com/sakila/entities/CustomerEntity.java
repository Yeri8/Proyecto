
package com.sakila.entities;

import com.sakila.models.Customer;
import interfaces.CRUDOperations;

import java.sql.*;
import java.util.ArrayList;

public class CustomerEntity implements CRUDOperations<Customer> {

    @Override
    public ArrayList<Customer> getAll(int limit, int offset, Connection conn) throws SQLException {
        ArrayList<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customer LIMIT ? OFFSET ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, limit);
            stmt.setInt(2, offset);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    customers.add(new Customer(
                            rs.getInt("customer_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("email")
                    ));
                }
            }
        }
        return customers;
    }

    @Override
    public Customer getById(int id, Connection conn) throws SQLException {
        String query = "SELECT * FROM customer WHERE customer_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Customer(
                            rs.getInt("customer_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("email")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public boolean add(Customer customer, Connection conn) throws SQLException {
        String query = "INSERT INTO customer (first_name, last_name, email) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getEmail());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean update(int id, Customer customer, Connection conn) throws SQLException {
        String query = "UPDATE customer SET first_name = ?, last_name = ?, email = ? WHERE customer_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getEmail());
            stmt.setInt(4, id);
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(int id, Connection conn) throws SQLException {
        String query = "DELETE FROM customer WHERE customer_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
}

