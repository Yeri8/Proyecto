
package com.sakila.ui;

import com.sakila.controllers.MovieController;
import com.sakila.models.Movie;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Interfaz para gestionar películas.
 */
public class MovieUI {
    private final MovieController movieController;  // Controlador para manejar las operaciones de película
    private final Scanner scanner;  // Objeto Scanner para leer la entrada del usuario

    public MovieUI(MovieController movieController) {
        this.movieController = movieController;
        this.scanner = new Scanner(System.in);  // Inicializamos el escáner para leer entradas
    }

    /**
     * Muestra el menú principal y maneja las opciones del usuario.
     */
    public void displayMenu() {
        int option = -1;  // Variable para almacenar la opción seleccionada por el usuario
        do {
            try {
                // Mostrar el menú de opciones
                System.out.println("\n--- Gestión de Películas ---");
                System.out.println("1. Ver todas las películas");
                System.out.println("2. Buscar película por ID");
                System.out.println("3. Agregar nueva película");
                System.out.println("4. Actualizar película");
                System.out.println("5. Eliminar película");
                System.out.println("6. Salir");
                System.out.print("Seleccione una opción: ");
                option = scanner.nextInt();  // Leer la opción seleccionada por el usuario
                scanner.nextLine();  // Limpiar el buffer del scanner

                // Procesar la opción seleccionada
                switch (option) {
                    case 1:
                        listMovies();  // Mostrar todas las películas
                        break;
                    case 2:
                        findMovieById();  // Buscar una película por ID
                        break;
                    case 3:
                        addMovie();  // Agregar una nueva película
                        break;
                    case 4:
                        updateMovie();  // Actualizar una película existente
                        break;
                    case 5:
                        deleteMovie();  // Eliminar una película
                        break;
                    case 6:
                        System.out.println("¡Hasta luego!");  // Salir del menú
                        break;
                    default:
                        System.out.println("Opción no válida.");  // Manejar opciones inválidas
                }
            } catch (InputMismatchException e) {
                // Capturar error si la entrada no es válida
                System.out.println("Entrada no válida. Intente nuevamente.");
                scanner.nextLine(); // Limpiar el buffer en caso de error
            }
        } while (option != 6);  // Continuar mostrando el menú hasta que el usuario elija salir
    }

    /**
     * Muestra todas las películas en la base de datos.
     */
    private void listMovies() {
        List<Movie> movies = movieController.getAllMovies();  // Obtener todas las películas del controlador
        if (movies.isEmpty()) {
            System.out.println("No hay películas disponibles.");  // Si no hay películas, mostrar mensaje
        } else {
            movies.forEach(System.out::println);  // Imprimir cada película en la lista
        }
    }

    /**
     * Busca una película por su ID e imprime los detalles.
     */
    private void findMovieById() {
        System.out.print("Ingrese el ID de la película: ");
        int id = scanner.nextInt();  // Leer el ID de la película
        scanner.nextLine();  // Limpiar el buffer

        Movie movie = movieController.getMovieById(id);  // Buscar la película por ID
        if (movie != null) {
            System.out.println(movie);  // Si la película existe, mostrarla
        } else {
            System.out.println("Película no encontrada.");  // Si no se encuentra, mostrar mensaje
        }
    }

    /**
     * Permite al usuario agregar una nueva película.
     */
    private void addMovie() {
        // Pedir los datos de la nueva película
        System.out.print("Ingrese el título de la película: ");
        String title = scanner.nextLine();
        System.out.print("Ingrese la descripción de la película: ");
        String description = scanner.nextLine();
        System.out.print("Ingrese la categoría de la película: ");
        String category = scanner.nextLine();

        // Crear una nueva instancia de Movie con los datos proporcionados
        Movie movie = new Movie(0, title, description, category);  // El ID se generará automáticamente

        // Intentar agregar la película y mostrar mensaje de éxito o error
        boolean success = movieController.addMovie(movie);
        if (success) {
            System.out.println("Película agregada exitosamente.");
        } else {
            System.out.println("Error al agregar la película.");
        }
    }

    /**
     * Permite al usuario actualizar una película existente.
     */
    private void updateMovie() {
        System.out.print("Ingrese el ID de la película a actualizar: ");
        int id = scanner.nextInt();  // Leer el ID de la película
        scanner.nextLine();  // Limpiar el buffer

        Movie movie = movieController.getMovieById(id);  // Buscar la película por ID
        if (movie != null) {
            // Solicitar al usuario los nuevos datos para actualizar la película
            System.out.print("Ingrese el nuevo título de la película: ");
            movie.setTitle(scanner.nextLine());
            System.out.print("Ingrese la nueva descripción de la película: ");
            movie.setDescription(scanner.nextLine());
            System.out.print("Ingrese la nueva categoría de la película: ");
            movie.setCategory(scanner.nextLine());

            // Intentar actualizar la película y mostrar mensaje de éxito o error
            boolean success = movieController.updateMovie(id, movie);
            if (success) {
                System.out.println("Película actualizada exitosamente.");
            } else {
                System.out.println("Error al actualizar la película.");
            }
        } else {
            System.out.println("Película no encontrada.");  // Si no se encuentra la película
        }
    }

    /**
     * Permite al usuario eliminar una película existente.
     */
    private void deleteMovie() {
        System.out.print("Ingrese el ID de la película a eliminar: ");
        int id = scanner.nextInt();  // Leer el ID de la película
        scanner.nextLine();  // Limpiar el buffer

        // Intentar eliminar la película y mostrar mensaje de éxito o error
        boolean success = movieController.deleteMovie(id);
        if (success) {
            System.out.println("Película eliminada exitosamente.");
        } else {
            System.out.println("Error al eliminar la película.");  // Si no se puede eliminar
        }
    }
}

