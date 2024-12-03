
package com.sakila.controllers;

import com.sakila.models.Payment;
import java.util.List;
import java.util.ArrayList;

public class PaymentController {

    // Lista que almacena los pagos
    private final List<Payment> payments = new ArrayList<>();

    // Método para obtener todos los pagos
    public List<Payment> getAllPayments() {
        // Retorna la lista completa de pagos
        return payments;
    }

    // Método para obtener un pago por ID
    public Payment getPaymentById(int id) {
        // Recorre la lista de pagos para buscar el pago con el ID correspondiente
        for (Payment payment : payments) {
            if (payment.getId() == id) {
                return payment;  // Retorna el pago si se encuentra
            }
        }
        return null;  // Si no se encuentra, retorna null
    }

    // Método para agregar un nuevo pago
    public boolean addPayment(Payment payment) {
        // Agrega un nuevo pago a la lista y retorna true si la operación fue exitosa
        return payments.add(payment);
    }

    // Método para actualizar un pago
    public boolean updatePayment(int id, Payment updatedPayment) {
        // Busca el pago en la lista por su ID y lo actualiza si se encuentra
        for (int i = 0; i < payments.size(); i++) {
            if (payments.get(i).getId() == id) {
                payments.set(i, updatedPayment);  // Reemplaza el pago antiguo por el nuevo
                return true;  // Retorna true si se actualiza correctamente
            }
        }
        return false;  // Si no se encuentra el pago, retorna false
    }

    // Método para eliminar un pago
    public boolean deletePayment(int id) {
        // Elimina el pago con el ID especificado y retorna true si se elimina correctamente
        return payments.removeIf(payment -> payment.getId() == id);
    }
}
