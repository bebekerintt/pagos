package com.nttdata.proyectos.pagos.controller;

import com.nttdata.proyectos.pagos.service.PaymentService;
import com.nttdata.proyectos.pagos.dto.PaymentRequestDTO;
import com.nttdata.proyectos.pagos.dto.PaymentResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponseDTO>> getPayments() {
        List<PaymentResponseDTO> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    @PostMapping
    public ResponseEntity<String> addPayment(@RequestBody PaymentRequestDTO paymentRequest) {
        paymentService.registerPayment(paymentRequest);
        return ResponseEntity.ok("Pago registrado exitosamente");
    }
}

