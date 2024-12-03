
package com.sakila.controllers;

import com.sakila.entities.CustomerEntity; // Importa la clase que maneja la base de datos
import com.sakila.models.Customer; // Importa el modelo de cliente
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CustomerController {

    private CustomerEntity customerEntity = new CustomerEntity(); // Instancia de CustomerEntity que gestiona las operaciones de base de datos para el cliente

    // Método para obtener todos los clientes con paginación
    public List<Customer> getAllCustomers(int limit, int offset, Connection conn) throws SQLException {
        return customerEntity.getAll(limit, offset, conn); // Llama al método getAll de CustomerEntity para obtener la lista de clientes
    }

    // Método para obtener un cliente por ID
    public Customer getCustomerById(int id, Connection conn) throws SQLException {
        return customerEntity.getById(id, conn); // Llama al método getById de CustomerEntity para obtener un cliente por su ID
    }
}
