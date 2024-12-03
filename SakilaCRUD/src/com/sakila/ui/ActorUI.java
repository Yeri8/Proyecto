
package com.sakila.ui;

import com.sakila.controllers.ActorController;
import com.sakila.models.Actor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ActorUI {
    // Atributos: controlador para gestionar actores y scanner para leer la entrada del usuario
    private final ActorController actorController;
    private final Scanner scanner;

    // Constructor: inicializa el controlador de actores y el scanner
    public ActorUI(ActorController actorController) {
        this.actorController = actorController;
        this.scanner = new Scanner(System.in);
    }

    // Método para mostrar el menú principal
    public void displayMenu() {
        int option;
        try {
            // Registrar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Crear la conexión a la base de datos
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "Enmanuel0712")) {
                // Bucle que muestra el menú y ejecuta las operaciones elegidas
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
                    // Ejecutar la opción seleccionada
                    switch (option) {
                        case 1 -> listActors(conn); // Ver todos los actores
                        case 2 -> findActorById(conn); // Buscar actor por ID
                        case 3 -> addActor(conn); // Agregar nuevo actor
                        case 4 -> updateActor(conn); // Actualizar actor
                        case 5 -> deleteActor(conn); // Eliminar actor
                        case 0 -> System.out.println("Saliendo..."); // Salir
                        default -> System.out.println("Opción inválida. Intente nuevamente.");
                    }
                } while (option != 0); // Continuar mostrando el menú hasta que elija salir
            } catch (SQLException e) {
                System.out.println("Error al conectar con la base de datos: " + e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se pudo cargar el driver de MySQL: " + e.getMessage());
        }
    }

    // Método para listar todos los actores
    private void listActors(Connection conn) {
        System.out.println("\n--- Lista de Actores ---");
        try {
            // Obtener los actores con paginación (10 actores por página)
            List<Actor> actors = actorController.getAllActors(10, 0, conn);
            if (actors.isEmpty()) {
                System.out.println("No hay actores disponibles.");
            } else {
                // Mostrar cada actor en la lista
                actors.forEach(System.out::println);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de actores: " + e.getMessage());
        }
    }

    // Método para buscar un actor por su ID
    private void findActorById(Connection conn) {
        System.out.print("Ingrese el ID del actor: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        try {
            // Buscar el actor por ID usando el controlador
            Actor actor = actorController.getActorById(id, conn);
            if (actor != null) {
                System.out.println("\nDetalles del actor:");
                System.out.println(actor); // Mostrar detalles del actor
            } else {
                System.out.println("Actor no encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar el actor: " + e.getMessage());
        }
    }

    // Método para agregar un nuevo actor
    private void addActor(Connection conn) {
        System.out.print("Ingrese el nombre del actor: ");
        String firstName = scanner.nextLine();
        System.out.print("Ingrese el apellido del actor: ");
        String lastName = scanner.nextLine();

        // Crear un nuevo objeto Actor (el ID será generado automáticamente)
        Actor actor = new Actor(0, firstName, lastName);  
        try {
            // Intentar agregar el actor a la base de datos usando el controlador
            boolean success = actorController.addActor(actor, conn);
            if (success) {
                System.out.println("Actor agregado exitosamente.");
            } else {
                System.out.println("Error al agregar el actor.");
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar el actor: " + e.getMessage());
        }
    }

    // Método para actualizar un actor
    private void updateActor(Connection conn) {
        System.out.print("Ingrese el ID del actor a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        System.out.print("Ingrese el nuevo nombre del actor: ");
        String firstName = scanner.nextLine();
        System.out.print("Ingrese el nuevo apellido del actor: ");
        String lastName = scanner.nextLine();

        // Crear un objeto Actor con los nuevos datos
        Actor actor = new Actor(id, firstName, lastName);
        try {
            // Intentar actualizar el actor en la base de datos
            boolean success = actorController.updateActor(id, actor, conn);
            if (success) {
                System.out.println("Actor actualizado exitosamente.");
            } else {
                System.out.println("Error al actualizar el actor.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar el actor: " + e.getMessage());
        }
    }

    // Método para eliminar un actor
    private void deleteActor(Connection conn) {
        System.out.print("Ingrese el ID del actor a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        try {
            // Intentar eliminar el actor usando el controlador
            boolean success = actorController.deleteActor(id, conn);
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
