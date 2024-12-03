
package com.sakila.entities;

import com.sakila.models.Customer;
import interfaces.CRUDOperations;

import java.sql.*;
import java.util.ArrayList;

public class CustomerEntity implements CRUDOperations<Customer> {

    // Método para obtener todos los clientes con paginación
    @Override
    public ArrayList<Customer> getAll(int limit, int offset, Connection conn) throws SQLException {
        ArrayList<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customer LIMIT ? OFFSET ?"; // Consulta SQL con LIMIT y OFFSET para paginación
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, limit);  // Establece el límite de resultados
            stmt.setInt(2, offset); // Establece el desplazamiento (paginación)
            try (ResultSet rs = stmt.executeQuery()) {
                // Itera sobre los resultados y crea objetos Customer
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
        return customers; // Retorna la lista de clientes obtenidos
    }

    // Método para obtener un cliente específico por ID
    @Override
    public Customer getById(int id, Connection conn) throws SQLException {
        String query = "SELECT * FROM customer WHERE customer_id = ?"; // Consulta SQL para obtener un cliente por ID
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id); // Establece el parámetro para el ID del cliente
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) { // Si existe el cliente, lo devuelve como objeto Customer
                    return new Customer(
                            rs.getInt("customer_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("email")
                    );
                }
            }
        }
        return null; // Retorna null si no se encuentra el cliente
    }

    // Método para agregar un nuevo cliente a la base de datos
    @Override
    public boolean add(Customer customer, Connection conn) throws SQLException {
        String query = "INSERT INTO customer (first_name, last_name, email) VALUES (?, ?, ?)"; // Consulta SQL para insertar un nuevo cliente
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, customer.getFirstName());  // Establece el primer nombre
            stmt.setString(2, customer.getLastName());   // Establece el apellido
            stmt.setString(3, customer.getEmail());      // Establece el correo electrónico
            return stmt.executeUpdate() > 0; // Retorna true si la inserción fue exitosa (se insertó al menos una fila)
        }
    }

    // Método para actualizar un cliente existente
    @Override
    public boolean update(int id, Customer customer, Connection conn) throws SQLException {
        String query = "UPDATE customer SET first_name = ?, last_name = ?, email = ? WHERE customer_id = ?"; // Consulta SQL para actualizar un cliente por ID
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, customer.getFirstName());  // Establece el primer nombre
            stmt.setString(2, customer.getLastName());   // Establece el apellido
            stmt.setString(3, customer.getEmail());      // Establece el correo electrónico
            stmt.setInt(4, id);                          // Establece el ID del cliente a actualizar
            return stmt.executeUpdate() > 0; // Retorna true si la actualización fue exitosa (se actualizó al menos una fila)
        }
    }

    // Método para eliminar un cliente de la base de datos
    @Override
    public boolean delete(int id, Connection conn) throws SQLException {
        String query = "DELETE FROM customer WHERE customer_id = ?"; // Consulta SQL para eliminar un cliente por ID
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id); // Establece el ID del cliente a eliminar
            return stmt.executeUpdate() > 0; // Retorna true si la eliminación fue exitosa (se eliminó al menos una fila)
        }
    }
}

