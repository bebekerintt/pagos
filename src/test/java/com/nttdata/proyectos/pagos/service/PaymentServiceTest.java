package com.nttdata.proyectos.pagos.service;

import com.nttdata.proyectos.pagos.repository.PaymentRepository;
import com.nttdata.proyectos.pagos.dto.PaymentResponseDTO;
import com.nttdata.proyectos.pagos.dto.PaymentRequestDTO;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPayments() {

        PaymentResponseDTO payment1 = new PaymentResponseDTO(1L, "1111111111111111", new BigDecimal("100.00"), LocalDate.now(), "Pago 1");
        PaymentResponseDTO payment2 = new PaymentResponseDTO(2L, "2222222222222222", new BigDecimal("200.00"), LocalDate.now(), "Pago 2");

        when(paymentRepository.findAll()).thenReturn(Arrays.asList(payment1, payment2));


        List<PaymentResponseDTO> result = paymentService.getAllPayments();

        // Verificar resultados
        assertEquals(2, result.size());
        assertEquals("1111111111111111", result.get(0).getCardNumber());
        assertEquals("2222222222222222", result.get(1).getCardNumber());
    }

    @Test
    public void testRegisterPayment() {

        PaymentRequestDTO paymentRequest = new PaymentRequestDTO();
        paymentRequest.setCardNumber("1111111111111111");
        paymentRequest.setAmount(new BigDecimal("100.00"));
        paymentRequest.setPaymentDate(LocalDate.now());
        paymentRequest.setDescription("Pago de prueba");

        paymentService.registerPayment(paymentRequest);

        verify(paymentRepository, times(1)).save(paymentRequest);
    }
}

