
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
    private final MovieController movieController;
    private final Scanner scanner;

    public MovieUI(MovieController movieController) {
        this.movieController = movieController;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        int option = -1;
        do {
            try {
                System.out.println("\n--- Gestión de Películas ---");
                System.out.println("1. Ver todas las películas");
                System.out.println("2. Buscar película por ID");
                System.out.println("3. Agregar nueva película");
                System.out.println("4. Actualizar película");
                System.out.println("5. Eliminar película");
                System.out.println("6. Salir");
                System.out.print("Seleccione una opción: ");
                option = scanner.nextInt();
                scanner.nextLine();  // Limpiar el buffer del scanner

                switch (option) {
                    case 1:
                        listMovies();
                        break;
                    case 2:
                        findMovieById();
                        break;
                    case 3:
                        addMovie();
                        break;
                    case 4:
                        updateMovie();
                        break;
                    case 5:
                        deleteMovie();
                        break;
                    case 6:
                        System.out.println("¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Intente nuevamente.");
                scanner.nextLine(); // Limpiar el buffer
            }
        } while (option != 6);
    }

    private void listMovies() {
        List<Movie> movies = movieController.getAllMovies();
        if (movies.isEmpty()) {
            System.out.println("No hay películas disponibles.");
        } else {
            movies.forEach(System.out::println);
        }
    }

    private void findMovieById() {
        System.out.print("Ingrese el ID de la película: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer

        Movie movie = movieController.getMovieById(id);
        if (movie != null) {
            System.out.println(movie);
        } else {
            System.out.println("Película no encontrada.");
        }
    }

    private void addMovie() {
        System.out.print("Ingrese el título de la película: ");
        String title = scanner.nextLine();
        System.out.print("Ingrese la descripción de la película: ");
        String description = scanner.nextLine();
        System.out.print("Ingrese la categoría de la película: ");
        String category = scanner.nextLine();

        Movie movie = new Movie(0, title, description, category);  // El ID se generará automáticamente
        boolean success = movieController.addMovie(movie);
        if (success) {
            System.out.println("Película agregada exitosamente.");
        } else {
            System.out.println("Error al agregar la película.");
        }
    }

    private void updateMovie() {
        System.out.print("Ingrese el ID de la película a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer

        Movie movie = movieController.getMovieById(id);
        if (movie != null) {
            System.out.print("Ingrese el nuevo título de la película: ");
            movie.setTitle(scanner.nextLine());
            System.out.print("Ingrese la nueva descripción de la película: ");
            movie.setDescription(scanner.nextLine());
            System.out.print("Ingrese la nueva categoría de la película: ");
            movie.setCategory(scanner.nextLine());

            boolean success = movieController.updateMovie(id, movie);
            if (success) {
                System.out.println("Película actualizada exitosamente.");
            } else {
                System.out.println("Error al actualizar la película.");
            }
        } else {
            System.out.println("Película no encontrada.");
        }
    }

    private void deleteMovie() {
        System.out.print("Ingrese el ID de la película a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer

        boolean success = movieController.deleteMovie(id);
        if (success) {
            System.out.println("Película eliminada exitosamente.");
        } else {
            System.out.println("Error al eliminar la película.");
        }
    }
}

