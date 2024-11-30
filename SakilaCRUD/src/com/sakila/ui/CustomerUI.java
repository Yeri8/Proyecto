
package com.sakila.ui;

import com.sakila.controllers.CustomerController;
import com.sakila.models.Customer;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CustomerUI {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CustomerController customerController = new CustomerController();

    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("Bienvenido a la gestión de clientes.");

            // Solicitar los parámetros de paginación
            System.out.print("Ingrese el límite de clientes a mostrar: ");
            int limit = Integer.parseInt(scanner.nextLine());

            System.out.print("Ingrese el offset de clientes a mostrar: ");
            int offset = Integer.parseInt(scanner.nextLine());

            // Obtener los clientes
            List<Customer> customers = customerController.getAllCustomers(limit, offset, conn);

            // Verificar si se obtuvieron clientes
            if (customers.isEmpty()) {
                System.out.println("No se encontraron clientes.");
            } else {
                // Imprimir los clientes en consola
                for (Customer customer : customers) {
                    System.out.println(customer);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un número válido.");
        }
    }
}


