
package tests;

import com.sakila.entities.CustomerEntity;
import com.sakila.models.Customer;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.util.ArrayList;

public class CustomerEntityTest {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            CustomerEntity customerEntity = new CustomerEntity();

            // Test de obtener todos los clientes
            System.out.println("Obteniendo todos los clientes:");
            ArrayList<Customer> customers = customerEntity.getAll(10, 0, conn);
            for (Customer customer : customers) {
                System.out.println(customer);
            }

            // Test de obtener cliente por ID
            System.out.println("\nObteniendo cliente con ID 1:");
            Customer customer = customerEntity.getById(1, conn);
            System.out.println(customer);

            // Test de agregar cliente
            System.out.println("\nAgregando nuevo cliente:");
            Customer newCustomer = new Customer(0, "John", "Doe", "johndoe@example.com");
            boolean added = customerEntity.add(newCustomer, conn);
            System.out.println("Cliente agregado: " + added);

            // Test de actualizar cliente
            System.out.println("\nActualizando cliente con ID 1:");
            Customer updatedCustomer = new Customer(1, "Jane", "Doe", "janedoe@example.com");
            boolean updated = customerEntity.update(1, updatedCustomer, conn);
            System.out.println("Cliente actualizado: " + updated);

            // Test de eliminar cliente
            System.out.println("\nEliminando cliente con ID 1:");
            boolean deleted = customerEntity.delete(1, conn);
            System.out.println("Cliente eliminado: " + deleted);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
