
package com.sakila.ui;

import com.sakila.controllers.PaymentController;
import com.sakila.models.Payment;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Interfaz para gestionar pagos.
 */
public class PaymentUI {
    private final PaymentController paymentController;
    private final Scanner scanner;

    public PaymentUI(PaymentController paymentController) {
        this.paymentController = paymentController;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        int option = -1; // Valor inicial para asegurar que el ciclo entre.
        do {
            try {
                System.out.println("\n--- Gestión de Pagos ---");
                System.out.println("1. Ver todos los pagos");
                System.out.println("2. Buscar pago por ID");
                System.out.println("3. Registrar nuevo pago");
                System.out.println("4. Actualizar pago");
                System.out.println("5. Eliminar pago");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                option = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                // Validación para asegurarse de que la opción esté dentro de los valores permitidos.
                if (option < 0 || option > 5) {
                    System.out.println("Opción inválida. Intente nuevamente.");
                    continue; // Continuar el ciclo si la opción no es válida.
                }

                // Llamadas a los métodos según la opción seleccionada.
                switch (option) {
                    case 1 -> listPayments();
                    case 2 -> findPaymentById();
                    case 3 -> addPayment();
                    case 4 -> updatePayment();
                    case 5 -> deletePayment();
                    case 0 -> System.out.println("Saliendo...");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Intente nuevamente.");
                scanner.nextLine(); // Limpiar buffer en caso de error.
            }
        } while (option != 0); // Continuar hasta que se elija la opción 0 para salir.
    }

    private void listPayments() {
        System.out.println("\n--- Lista de Pagos ---");
        paymentController.getAllPayments().forEach(System.out::println);
    }

    private void findPaymentById() {
        System.out.print("Ingrese el ID del pago: ");
        int id = scanner.nextInt();
        Payment payment = paymentController.getPaymentById(id);
        if (payment != null) {
            System.out.println(payment);
        } else {
            System.out.println("Pago no encontrado.");
        }
    }

    private void addPayment() {
        try {
            System.out.print("Ingrese el ID del cliente: ");
            int customerId = scanner.nextInt();
            System.out.print("Ingrese el monto del pago: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Limpiar buffer
            Payment payment = new Payment(0, customerId, amount);
            if (paymentController.addPayment(payment)) {
                System.out.println("Pago registrado exitosamente.");
            } else {
                System.out.println("Error al registrar el pago.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada no válida para monto o ID. Intente nuevamente.");
            scanner.nextLine(); // Limpiar buffer
        }
    }

    private void updatePayment() {
        try {
            System.out.print("Ingrese el ID del pago a actualizar: ");
            int id = scanner.nextInt();
            System.out.print("Ingrese el nuevo ID del cliente: ");
            int customerId = scanner.nextInt();
            System.out.print("Ingrese el nuevo monto del pago: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Limpiar buffer
            Payment payment = new Payment(id, customerId, amount);
            if (paymentController.updatePayment(id, payment)) {
                System.out.println("Pago actualizado exitosamente.");
            } else {
                System.out.println("Error al actualizar el pago.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada no válida para monto o ID. Intente nuevamente.");
            scanner.nextLine(); // Limpiar buffer
        }
    }

    private void deletePayment() {
        try {
            System.out.print("Ingrese el ID del pago a eliminar: ");
            int id = scanner.nextInt();
            if (paymentController.deletePayment(id)) {
                System.out.println("Pago eliminado exitosamente.");
            } else {
                System.out.println("Error al eliminar el pago.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada no válida para ID. Intente nuevamente.");
            scanner.nextLine(); // Limpiar buffer
        }
    }
}

