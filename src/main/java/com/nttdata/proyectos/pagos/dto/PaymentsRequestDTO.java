package com.nttdata.proyectos.pagos.dto;

import java.util.List;

public class PaymentsRequestDTO {
    private List<PaymentRequestDTO> payments;

    // Getters y Setters
    public List<PaymentRequestDTO> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentRequestDTO> payments) {
        this.payments = payments;
    }

}
