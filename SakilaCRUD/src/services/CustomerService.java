
package services;

import com.sakila.entities.CustomerEntity;
import com.sakila.models.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CustomerService {

    private final CustomerEntity customerEntity;

    // Constructor
    public CustomerService() {
        this.customerEntity = new CustomerEntity();
    }

    // Obtener todos los clientes
    public List<Customer> getAllCustomers(int limit, int offset, Connection conn) throws SQLException {
        if (limit <= 0 || offset < 0) {
            throw new IllegalArgumentException("Limit debe ser mayor que 0 y Offset no puede ser negativo.");
        }
        if (conn == null || conn.isClosed()) {
            throw new SQLException("La conexión a la base de datos no está disponible.");
        }
        return customerEntity.getAll(limit, offset, conn); // Error potencial: "customerEntity.getAll" podría no existir o no estar implementado correctamente.
    }

    // Buscar cliente por ID
    public Customer getCustomerById(int id, Connection conn) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo.");
        }
        if (conn == null || conn.isClosed()) {
            throw new SQLException("La conexión a la base de datos no está disponible.");
        }
        return customerEntity.getById(id, conn); // Error potencial: "customerEntity.getById" podría no estar implementado o no aceptar parámetros correctamente.
    }

    // Agregar cliente
    public boolean addCustomer(Customer customer, Connection conn) throws SQLException {
        if (customer == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }
        if (conn == null || conn.isClosed()) {
            throw new SQLException("La conexión a la base de datos no está disponible.");
        }
        return customerEntity.add(customer, conn); // Error potencial: "customerEntity.add" podría no existir o no funcionar correctamente.
    }

    // Actualizar cliente
    public boolean updateCustomer(int id, Customer customer, Connection conn) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo.");
        }
        if (customer == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }
        if (conn == null || conn.isClosed()) {
            throw new SQLException("La conexión a la base de datos no está disponible.");
        }
        return customerEntity.update(id, customer, conn); // Error potencial: "customerEntity.update" podría no estar implementado o no ser compatible.
    }

    // Eliminar cliente
    public boolean deleteCustomer(int id, Connection conn) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo.");
        }
        if (conn == null || conn.isClosed()) {
            throw new SQLException("La conexión a la base de datos no está disponible.");
        }
        return customerEntity.delete(id, conn); // Error potencial: "customerEntity.delete" podría no estar implementado o no ser compatible.
    }
}
