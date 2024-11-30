
package com.sakila.ui;

import com.sakila.controllers.ActorController;
import com.sakila.models.Actor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final ActorController actorController;
    private final Scanner scanner;

    public ConsoleUI() {
        actorController = new ActorController();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        ConsoleUI ui = new ConsoleUI();
        ui.showMenu();
    }

    public void showMenu() {
        int choice = 0;
        // Crear conexión a la base de datos
        try {
            // Registrar el driver de MySQL (en caso de versiones anteriores del conector JDBC)
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "password")) {
                do {
                    System.out.println("\nSeleccione una opción:");
                    System.out.println("1. Agregar Actor");
                    System.out.println("2. Obtener Actor");
                    System.out.println("3. Actualizar Actor");
                    System.out.println("4. Eliminar Actor");
                    System.out.println("5. Mostrar todos los Actores");
                    System.out.println("6. Salir");
                    choice = getIntInput();

                    switch (choice) {
                        case 1:
                            agregarActor(conn);
                            break;
                        case 2:
                            obtenerActor(conn);
                            break;
                        case 3:
                            actualizarActor(conn);
                            break;
                        case 4:
                            eliminarActor(conn);
                            break;
                        case 5:
                            mostrarActores(conn);
                            break;
                        case 6:
                            System.out.println("Saliendo...");
                            break;
                        default:
                            System.out.println("Opción inválida. Intente de nuevo.");
                    }
                } while (choice != 6);
            } catch (SQLException e) {
                System.out.println("Error al conectar con la base de datos: " + e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se pudo cargar el driver de MySQL: " + e.getMessage());
        }
    }

    private void agregarActor(Connection conn) {
        System.out.print("Ingrese nombre: ");
        String firstName = scanner.nextLine();
        System.out.print("Ingrese apellido: ");
        String lastName = scanner.nextLine();
        Actor actor = new Actor(firstName, lastName);
        try {
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

    private void obtenerActor(Connection conn) {
        System.out.print("Ingrese el ID del actor a buscar: ");
        int id = getIntInput();
        try {
            Actor actor = actorController.getActorById(id, conn);
            if (actor != null) {
                System.out.println("Actor encontrado: " + actor.getFirstName() + " " + actor.getLastName());
            } else {
                System.out.println("Actor no encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar el actor: " + e.getMessage());
        }
    }

    private void actualizarActor(Connection conn) {
        System.out.print("Ingrese el ID del actor a actualizar: ");
        int id = getIntInput();
        System.out.print("Ingrese nuevo nombre: ");
        String firstName = scanner.nextLine();
        System.out.print("Ingrese nuevo apellido: ");
        String lastName = scanner.nextLine();
        Actor actor = new Actor(id, firstName, lastName);
        try {
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

    private void eliminarActor(Connection conn) {
        System.out.print("Ingrese el ID del actor a eliminar: ");
        int id = getIntInput();
        try {
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

    private void mostrarActores(Connection conn) {
        try {
            List<Actor> actors = actorController.getAllActors(10, 0, conn); // Paginación
            if (actors.isEmpty()) {
                System.out.println("No hay actores disponibles.");
            } else {
                System.out.println("Lista de Actores:");
                actors.forEach(actor -> System.out.println(actor.getFirstName() + " " + actor.getLastName()));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de actores: " + e.getMessage());
        }
    }

    private int getIntInput() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                scanner.nextLine(); // Limpiar el buffer
            }
        }
    }
}

