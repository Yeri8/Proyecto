
package com.sakila.ui;

import com.sakila.controllers.CustomerController;
import com.sakila.models.Customer;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CustomerUI {
    // Instancia del escáner para leer la entrada del usuario
    private static final Scanner scanner = new Scanner(System.in);
    
    // Instancia del controlador de clientes que maneja la lógica de negocio
    private static final CustomerController customerController = new CustomerController();

    public static void main(String[] args) {
        // Intentamos establecer la conexión con la base de datos utilizando el método getConnection
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("Bienvenido a la gestión de clientes.");

            // Solicitar al usuario los parámetros de paginación
            System.out.print("Ingrese el límite de clientes a mostrar: ");
            int limit = Integer.parseInt(scanner.nextLine());  // Leer el límite de clientes

            System.out.print("Ingrese el offset de clientes a mostrar: ");
            int offset = Integer.parseInt(scanner.nextLine());  // Leer el offset de clientes

            // Obtener la lista de clientes con la paginación especificada
            List<Customer> customers = customerController.getAllCustomers(limit, offset, conn);

            // Verificar si la lista de clientes está vacía
            if (customers.isEmpty()) {
                System.out.println("No se encontraron clientes.");
            } else {
                // Si hay clientes, los imprimimos en consola
                for (Customer customer : customers) {
                    System.out.println(customer);  // Asume que la clase Customer tiene un método toString() adecuado
                }
            }

        // Manejo de excepciones para errores de conexión a la base de datos
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        // Manejo de excepciones para entradas no numéricas cuando se solicitan los parámetros de paginación
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un número válido.");
        }
    }
}

