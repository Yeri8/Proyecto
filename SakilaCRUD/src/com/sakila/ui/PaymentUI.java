
package com.sakila.ui;

import com.sakila.controllers.PaymentController;
import com.sakila.models.Payment;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Interfaz para gestionar pagos.
 * Esta clase proporciona una interfaz de usuario basada en consola
 * para realizar operaciones sobre pagos.
 */
public class PaymentUI {
    private final PaymentController paymentController;  // Controlador que maneja la lógica de pagos
    private final Scanner scanner;  // Objeto Scanner para leer la entrada del usuario

    // Constructor para inicializar el controlador y el scanner
    public PaymentUI(PaymentController paymentController) {
        this.paymentController = paymentController;
        this.scanner = new Scanner(System.in);  // Inicializamos el escáner para leer entradas
    }

    /**
     * Muestra el menú principal para la gestión de pagos y gestiona la selección del usuario.
     * El ciclo continúa hasta que el usuario selecciona la opción de salir (0).
     */
    public void displayMenu() {
        int option = -1;  // Variable para almacenar la opción seleccionada por el usuario
        do {
            try {
                // Mostrar el menú con las opciones disponibles
                System.out.println("\n--- Gestión de Pagos ---");
                System.out.println("1. Ver todos los pagos");
                System.out.println("2. Buscar pago por ID");
                System.out.println("3. Registrar nuevo pago");
                System.out.println("4. Actualizar pago");
                System.out.println("5. Eliminar pago");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                option = scanner.nextInt();  // Leer la opción seleccionada por el usuario
                scanner.nextLine();  // Limpiar el buffer

                // Validación para asegurarse de que la opción esté dentro de los valores permitidos
                if (option < 0 || option > 5) {
                    System.out.println("Opción inválida. Intente nuevamente.");
                    continue;  // Continuar el ciclo si la opción no es válida
                }

                // Ejecutar el método correspondiente según la opción seleccionada
                switch (option) {
                    case 1 -> listPayments();  // Ver todos los pagos
                    case 2 -> findPaymentById();  // Buscar pago por ID
                    case 3 -> addPayment();  // Registrar nuevo pago
                    case 4 -> updatePayment();  // Actualizar pago
                    case 5 -> deletePayment();  // Eliminar pago
                    case 0 -> System.out.println("Saliendo...");  // Salir del menú
                }
            } catch (InputMismatchException e) {
                // Capturar error si la entrada no es válida
                System.out.println("Entrada no válida. Intente nuevamente.");
                scanner.nextLine();  // Limpiar el buffer en caso de error
            }
        } while (option != 0);  // Continuar el ciclo hasta que el usuario seleccione la opción 0 para salir
    }

    /**
     * Muestra todos los pagos registrados.
     */
    private void listPayments() {
        System.out.println("\n--- Lista de Pagos ---");
        // Obtener y mostrar todos los pagos utilizando el controlador
        paymentController.getAllPayments().forEach(System.out::println);
    }

    /**
     * Busca un pago por su ID e imprime los detalles.
     */
    private void findPaymentById() {
        System.out.print("Ingrese el ID del pago: ");
        int id = scanner.nextInt();  // Leer el ID del pago
        Payment payment = paymentController.getPaymentById(id);  // Buscar el pago por ID
        if (payment != null) {
            System.out.println(payment);  // Mostrar la información del pago si se encuentra
        } else {
            System.out.println("Pago no encontrado.");  // Si el pago no existe
        }
    }

    /**
     * Permite al usuario registrar un nuevo pago.
     * El usuario ingresa el ID del cliente y el monto del pago.
     */
    private void addPayment() {
        try {
            System.out.print("Ingrese el ID del cliente: ");
            int customerId = scanner.nextInt();  // Leer el ID del cliente
            System.out.print("Ingrese el monto del pago: ");
            double amount = scanner.nextDouble();  // Leer el monto del pago
            scanner.nextLine();  // Limpiar el buffer

            // Crear un objeto Payment con los datos proporcionados
            Payment payment = new Payment(0, customerId, amount);

            // Intentar agregar el pago utilizando el controlador y mostrar el resultado
            if (paymentController.addPayment(payment)) {
                System.out.println("Pago registrado exitosamente.");
            } else {
                System.out.println("Error al registrar el pago.");
            }
        } catch (InputMismatchException e) {
            // Capturar error si la entrada no es válida
            System.out.println("Entrada no válida para monto o ID. Intente nuevamente.");
            scanner.nextLine();  // Limpiar el buffer
        }
    }

    /**
     * Permite al usuario actualizar un pago existente.
     * El usuario ingresa el ID del pago, el nuevo ID del cliente y el monto.
     */
    private void updatePayment() {
        try {
            System.out.print("Ingrese el ID del pago a actualizar: ");
            int id = scanner.nextInt();  // Leer el ID del pago a actualizar
            System.out.print("Ingrese el nuevo ID del cliente: ");
            int customerId = scanner.nextInt();  // Leer el nuevo ID del cliente
            System.out.print("Ingrese el nuevo monto del pago: ");
            double amount = scanner.nextDouble();  // Leer el nuevo monto del pago
            scanner.nextLine();  // Limpiar el buffer

            // Crear un objeto Payment con los nuevos datos
            Payment payment = new Payment(id, customerId, amount);

            // Intentar actualizar el pago utilizando el controlador y mostrar el resultado
            if (paymentController.updatePayment(id, payment)) {
                System.out.println("Pago actualizado exitosamente.");
            } else {
                System.out.println("Error al actualizar el pago.");
            }
        } catch (InputMismatchException e) {
            // Capturar error si la entrada no es válida
            System.out.println("Entrada no válida para monto o ID. Intente nuevamente.");
            scanner.nextLine();  // Limpiar el buffer
        }
    }

    /**
     * Permite al usuario eliminar un pago existente.
     * El usuario ingresa el ID del pago a eliminar.
     */
    private void deletePayment() {
        try {
            System.out.print("Ingrese el ID del pago a eliminar: ");
            int id = scanner.nextInt();  // Leer el ID del pago a eliminar
            // Intentar eliminar el pago utilizando el controlador y mostrar el resultado
            if (paymentController.deletePayment(id)) {
                System.out.println("Pago eliminado exitosamente.");
            } else {
                System.out.println("Error al eliminar el pago.");
            }
        } catch (InputMismatchException e) {
            // Capturar error si la entrada no es válida
            System.out.println("Entrada no válida para ID. Intente nuevamente.");
            scanner.nextLine();  // Limpiar el buffer
        }
    }
}

