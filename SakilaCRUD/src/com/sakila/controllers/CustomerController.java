
package com.sakila.controllers;

import com.sakila.entities.CustomerEntity;
import com.sakila.models.Customer;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CustomerController {

    private CustomerEntity customerEntity = new CustomerEntity();

    // Método para obtener todos los clientes
    public List<Customer> getAllCustomers(int limit, int offset, Connection conn) throws SQLException {
        return customerEntity.getAll(limit, offset, conn);
    }

    // Método para obtener un cliente por ID
    public Customer getCustomerById(int id, Connection conn) throws SQLException {
        return customerEntity.getById(id, conn);
    }
}
