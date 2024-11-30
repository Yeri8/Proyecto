
package com.sakila.controllers;

import com.sakila.models.Payment;

import java.util.List;
import java.util.ArrayList;

public class PaymentController {

    private final List<Payment> payments = new ArrayList<>();

    // Método para obtener todos los pagos
    public List<Payment> getAllPayments() {
        return payments;
    }

    // Método para obtener un pago por ID
    public Payment getPaymentById(int id) {
        for (Payment payment : payments) {
            if (payment.getId() == id) {
                return payment;
            }
        }
        return null;
    }

    // Método para agregar un nuevo pago
    public boolean addPayment(Payment payment) {
        return payments.add(payment);
    }

    // Método para actualizar un pago
    public boolean updatePayment(int id, Payment updatedPayment) {
        for (int i = 0; i < payments.size(); i++) {
            if (payments.get(i).getId() == id) {
                payments.set(i, updatedPayment);
                return true;
            }
        }
        return false;
    }

    // Método para eliminar un pago
    public boolean deletePayment(int id) {
        return payments.removeIf(payment -> payment.getId() == id);
    }
}
