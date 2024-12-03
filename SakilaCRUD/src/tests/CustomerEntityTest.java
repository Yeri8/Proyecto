
package tests;

import com.sakila.entities.CustomerEntity;
import com.sakila.models.Customer;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.util.ArrayList;

public class CustomerEntityTest {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {  // Establece una conexión a la base de datos usando la utilidad DatabaseConnection
            CustomerEntity customerEntity = new CustomerEntity();  // Crea una instancia de CustomerEntity para interactuar con la base de datos

            // Test de obtener todos los clientes
            System.out.println("Obteniendo todos los clientes:");
            // Llama al método getAll() para obtener una lista de clientes con paginación (limite 10, desplazamiento 0)
            ArrayList<Customer> customers = customerEntity.getAll(10, 0, conn);
            // Imprime cada cliente obtenido
            for (Customer customer : customers) {
                System.out.println(customer);
            }

            // Test de obtener cliente por ID
            System.out.println("\nObteniendo cliente con ID 1:");
            // Llama al método getById() para obtener un cliente por su ID (en este caso, ID 1)
            Customer customer = customerEntity.getById(1, conn);
            // Imprime los detalles del cliente obtenido
            System.out.println(customer);

            // Test de agregar cliente
            System.out.println("\nAgregando nuevo cliente:");
            // Crea un nuevo objeto Customer con datos de ejemplo (ID 0 indica que es un nuevo cliente)
            Customer newCustomer = new Customer(0, "John", "Doe", "johndoe@example.com");
            // Llama al método add() para agregar el nuevo cliente a la base de datos
            boolean added = customerEntity.add(newCustomer, conn);
            // Imprime si el cliente fue agregado exitosamente
            System.out.println("Cliente agregado: " + added);

            // Test de actualizar cliente
            System.out.println("\nActualizando cliente con ID 1:");
            // Crea un objeto Customer con el ID 1 (cliente existente) para actualizar sus datos
            Customer updatedCustomer = new Customer(1, "Jane", "Doe", "janedoe@example.com");
            // Llama al método update() para actualizar los detalles del cliente con ID 1
            boolean updated = customerEntity.update(1, updatedCustomer, conn);
            // Imprime si el cliente fue actualizado exitosamente
            System.out.println("Cliente actualizado: " + updated);

            // Test de eliminar cliente
            System.out.println("\nEliminando cliente con ID 1:");
            // Llama al método delete() para eliminar el cliente con ID 1
            boolean deleted = customerEntity.delete(1, conn);
            // Imprime si el cliente fue eliminado exitosamente
            System.out.println("Cliente eliminado: " + deleted);

        } catch (Exception e) {
            e.printStackTrace();  // Si ocurre una excepción, imprime el stack trace para obtener detalles sobre el error
        }
    }
}
