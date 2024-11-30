
package com.sakila.ui;

import com.sakila.controllers.ActorController;
import com.sakila.models.Actor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ActorUI {
    private final ActorController actorController;
    private final Scanner scanner;

    public ActorUI(ActorController actorController) {
        this.actorController = actorController;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        int option;
        try {
            // Registrar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Crear la conexión a la base de datos
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "Enmanuel0712")) {
                do {
                    System.out.println("\n--- Gestión de Actores ---");
                    System.out.println("1. Ver todos los actores");
                    System.out.println("2. Buscar actor por ID");
                    System.out.println("3. Agregar nuevo actor");
                    System.out.println("4. Actualizar actor");
                    System.out.println("5. Eliminar actor");
                    System.out.println("0. Salir");
                    System.out.print("Seleccione una opción: ");
                    option = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    switch (option) {
                        case 1 -> listActors(conn);
                        case 2 -> findActorById(conn);
                        case 3 -> addActor(conn);
                        case 4 -> updateActor(conn);
                        case 5 -> deleteActor(conn);
                        case 0 -> System.out.println("Saliendo...");
                        default -> System.out.println("Opción inválida. Intente nuevamente.");
                    }
                } while (option != 0);
            } catch (SQLException e) {
                System.out.println("Error al conectar con la base de datos: " + e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se pudo cargar el driver de MySQL: " + e.getMessage());
        }
    }

    private void listActors(Connection conn) {
        System.out.println("\n--- Lista de Actores ---");
        try {
            List<Actor> actors = actorController.getAllActors(10, 0, conn); // Paginación
            if (actors.isEmpty()) {
                System.out.println("No hay actores disponibles.");
            } else {
                actors.forEach(System.out::println);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de actores: " + e.getMessage());
        }
    }

    private void findActorById(Connection conn) {
        System.out.print("Ingrese el ID del actor: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        try {
            Actor actor = actorController.getActorById(id, conn); // Buscar actor por ID
            if (actor != null) {
                System.out.println("\nDetalles del actor:");
                System.out.println(actor);
            } else {
                System.out.println("Actor no encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar el actor: " + e.getMessage());
        }
    }

    private void addActor(Connection conn) {
        System.out.print("Ingrese el nombre del actor: ");
        String firstName = scanner.nextLine();
        System.out.print("Ingrese el apellido del actor: ");
        String lastName = scanner.nextLine();

        Actor actor = new Actor(0, firstName, lastName);  // El ID se genera automáticamente
        try {
            boolean success = actorController.addActor(actor, conn); // Agregar actor
            if (success) {
                System.out.println("Actor agregado exitosamente.");
            } else {
                System.out.println("Error al agregar el actor.");
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar el actor: " + e.getMessage());
        }
    }

    private void updateActor(Connection conn) {
        System.out.print("Ingrese el ID del actor a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        System.out.print("Ingrese el nuevo nombre del actor: ");
        String firstName = scanner.nextLine();
        System.out.print("Ingrese el nuevo apellido del actor: ");
        String lastName = scanner.nextLine();

        Actor actor = new Actor(id, firstName, lastName);
        try {
            boolean success = actorController.updateActor(id, actor, conn); // Actualizar actor
            if (success) {
                System.out.println("Actor actualizado exitosamente.");
            } else {
                System.out.println("Error al actualizar el actor.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar el actor: " + e.getMessage());
        }
    }

    private void deleteActor(Connection conn) {
        System.out.print("Ingrese el ID del actor a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        try {
            boolean success = actorController.deleteActor(id, conn); // Eliminar actor
            if (success) {
                System.out.println("Actor eliminado exitosamente.");
            } else {
                System.out.println("Error al eliminar el actor.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el actor: " + e.getMessage());
        }
    }
}

