package com.nttdata.proyectos.pagos.service;

import com.nttdata.proyectos.pagos.dto.PaymentRequestDTO;
import com.nttdata.proyectos.pagos.dto.PaymentResponseDTO;
import com.nttdata.proyectos.pagos.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<PaymentResponseDTO> getAllPayments() {
        return paymentRepository.findAll();
    }

    public void registerPayment(PaymentRequestDTO paymentRequest) {
        paymentRepository.save(paymentRequest);
    }
}

