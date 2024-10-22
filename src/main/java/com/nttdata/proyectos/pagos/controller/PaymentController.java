package com.nttdata.proyectos.pagos.controller;

import com.nttdata.proyectos.pagos.service.PaymentService;
import com.nttdata.proyectos.pagos.model.Payment;
import com.nttdata.proyectos.pagos.dto.PaymentRequestDTO;
import com.nttdata.proyectos.pagos.dto.PaymentsRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    // Endpoint para listar todos los pagos
    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    // Endpoint para registrar un nuevo pago
    @PostMapping("/register")
    public ResponseEntity<Payment> registerPayment(@RequestBody PaymentRequestDTO paymentRequest) {
        Payment savedPayment = paymentService.registerPayment(paymentRequest);
        return ResponseEntity.status(201).body(savedPayment);
    }

    @PostMapping("/registerMultiple")
    public ResponseEntity<List<Payment>> registerMultiplePayments(@RequestBody PaymentsRequestDTO paymentsRequest) {
        List<Payment> savedPayments = paymentService.registerMultiplePayments(paymentsRequest);
        return ResponseEntity.status(201).body(savedPayments);
    }

}
