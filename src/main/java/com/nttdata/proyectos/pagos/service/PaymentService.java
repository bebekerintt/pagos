package com.nttdata.proyectos.pagos.service;

import com.nttdata.proyectos.pagos.dto.PaymentRequestDTO;
import com.nttdata.proyectos.pagos.dto.PaymentsRequestDTO;
import com.nttdata.proyectos.pagos.repository.PaymentRepository;
import com.nttdata.proyectos.pagos.model.Payment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    // Inyección de dependencia a través del constructor
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    // Listar todos los pagos
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // Registrar un nuevo pago
    public Payment registerPayment(PaymentRequestDTO paymentRequest) {
        Payment payment = new Payment();
        payment.setCardNumber(paymentRequest.getCardNumber());
        payment.setAmount(paymentRequest.getAmount());
        LocalDate date = LocalDate.parse(paymentRequest.getPaymentDate());
        payment.setPaymentDate(date);
        payment.setDescription(paymentRequest.getDescription());

        return paymentRepository.save(payment);
    }

    public List<Payment> registerMultiplePayments(PaymentsRequestDTO paymentsRequest) {
        List<Payment> savedPayments = new ArrayList<>();
        for (PaymentRequestDTO paymentRequest : paymentsRequest.getPayments()) {
            savedPayments.add(registerPayment(paymentRequest));
        }
        return savedPayments;
    }
}

