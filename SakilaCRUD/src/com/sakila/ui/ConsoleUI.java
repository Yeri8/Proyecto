
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
    // Controlador para gestionar actores y escáner para leer entradas del usuario
    private final ActorController actorController;
    private final Scanner scanner;

    // Constructor: inicializa el controlador y el escáner
    public ConsoleUI() {
        actorController = new ActorController();  // Crear un controlador de actores
        scanner = new Scanner(System.in);  // Crear un objeto Scanner para leer la entrada
    }

    // Método principal para iniciar la interfaz de usuario
    public static void main(String[] args) {
        ConsoleUI ui = new ConsoleUI();
        ui.showMenu();  // Mostrar el menú de opciones
    }

    // Método para mostrar el menú y gestionar la elección del usuario
    public void showMenu() {
        int choice = 0;  // Variable para almacenar la opción seleccionada por el usuario

        // Intentar conectar con la base de datos
        try {
            // Registrar el driver de MySQL (en caso de versiones antiguas del conector JDBC)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Intentar establecer la conexión con la base de datos
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "password")) {
                do {
                    // Mostrar las opciones del menú
                    System.out.println("\nSeleccione una opción:");
                    System.out.println("1. Agregar Actor");
                    System.out.println("2. Obtener Actor");
                    System.out.println("3. Actualizar Actor");
                    System.out.println("4. Eliminar Actor");
                    System.out.println("5. Mostrar todos los Actores");
                    System.out.println("6. Salir");
                    choice = getIntInput();  // Leer la opción seleccionada

                    // Ejecutar la acción correspondiente a la opción elegida
                    switch (choice) {
                        case 1:
                            agregarActor(conn);  // Llamar al método para agregar actor
                            break;
                        case 2:
                            obtenerActor(conn);  // Llamar al método para obtener un actor
                            break;
                        case 3:
                            actualizarActor(conn);  // Llamar al método para actualizar actor
                            break;
                        case 4:
                            eliminarActor(conn);  // Llamar al método para eliminar actor
                            break;
                        case 5:
                            mostrarActores(conn);  // Llamar al método para mostrar actores
                            break;
                        case 6:
                            System.out.println("Saliendo...");  // Salir del programa
                            break;
                        default:
                            System.out.println("Opción inválida. Intente de nuevo.");  // Opción no válida
                    }
                } while (choice != 6);  // Continuar mostrando el menú hasta que el usuario elija salir
            } catch (SQLException e) {
                System.out.println("Error al conectar con la base de datos: " + e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se pudo cargar el driver de MySQL: " + e.getMessage());
        }
    }

    // Método para agregar un nuevo actor a la base de datos
    private void agregarActor(Connection conn) {
        System.out.print("Ingrese nombre: ");
        String firstName = scanner.nextLine();  // Leer nombre
        System.out.print("Ingrese apellido: ");
        String lastName = scanner.nextLine();  // Leer apellido
        Actor actor = new Actor(firstName, lastName);  // Crear objeto Actor

        try {
            boolean success = actorController.addActor(actor, conn);  // Intentar agregar el actor
            if (success) {
                System.out.println("Actor agregado exitosamente.");
            } else {
                System.out.println("Error al agregar el actor.");
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar el actor: " + e.getMessage());
        }
    }

    // Método para obtener un actor de la base de datos por su ID
    private void obtenerActor(Connection conn) {
        System.out.print("Ingrese el ID del actor a buscar: ");
        int id = getIntInput();  // Leer el ID del actor

        try {
            Actor actor = actorController.getActorById(id, conn);  // Buscar el actor por ID
            if (actor != null) {
                System.out.println("Actor encontrado: " + actor.getFirstName() + " " + actor.getLastName());
            } else {
                System.out.println("Actor no encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar el actor: " + e.getMessage());
        }
    }

    // Método para actualizar la información de un actor
    private void actualizarActor(Connection conn) {
        System.out.print("Ingrese el ID del actor a actualizar: ");
        int id = getIntInput();  // Leer el ID del actor
        System.out.print("Ingrese nuevo nombre: ");
        String firstName = scanner.nextLine();  // Leer el nuevo nombre
        System.out.print("Ingrese nuevo apellido: ");
        String lastName = scanner.nextLine();  // Leer el nuevo apellido

        Actor actor = new Actor(id, firstName, lastName);  // Crear objeto Actor con nuevos datos
        try {
            boolean success = actorController.updateActor(id, actor, conn);  // Intentar actualizar el actor
            if (success) {
                System.out.println("Actor actualizado exitosamente.");
            } else {
                System.out.println("Error al actualizar el actor.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar el actor: " + e.getMessage());
        }
    }

    // Método para eliminar un actor de la base de datos
    private void eliminarActor(Connection conn) {
        System.out.print("Ingrese el ID del actor a eliminar: ");
        int id = getIntInput();  // Leer el ID del actor a eliminar
        try {
            boolean success = actorController.deleteActor(id, conn);  // Intentar eliminar el actor
            if (success) {
                System.out.println("Actor eliminado exitosamente.");
            } else {
                System.out.println("Error al eliminar el actor.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el actor: " + e.getMessage());
        }
    }

    // Método para mostrar todos los actores de la base de datos
    private void mostrarActores(Connection conn) {
        try {
            List<Actor> actors = actorController.getAllActors(10, 0, conn);  // Obtener actores con paginación
            if (actors.isEmpty()) {
                System.out.println("No hay actores disponibles.");
            } else {
                System.out.println("Lista de Actores:");
                // Mostrar los nombres y apellidos de los actores
                actors.forEach(actor -> System.out.println(actor.getFirstName() + " " + actor.getLastName()));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de actores: " + e.getMessage());
        }
    }

    // Método auxiliar para leer un número entero con manejo de errores
    private int getIntInput() {
        while (true) {
            try {
                return scanner.nextInt();  // Leer un número entero
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                scanner.nextLine();  // Limpiar el buffer de entrada
            }
        }
    }
}

