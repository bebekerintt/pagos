package com.nttdata.proyectos.pagos.controller;

import com.nttdata.proyectos.pagos.service.PaymentService;
import com.nttdata.proyectos.pagos.dto.PaymentRequestDTO;
import com.nttdata.proyectos.pagos.dto.PaymentResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPayments_ShouldReturnListOfPayments() {
        PaymentResponseDTO payment1 = new PaymentResponseDTO(1L, "1234567812345678", BigDecimal.valueOf(150.50), LocalDate.now(), "Pago mensual");
        PaymentResponseDTO payment2 = new PaymentResponseDTO(2L, "8765432187654321", BigDecimal.valueOf(200.00), LocalDate.now(), "Pago extra");

        when(paymentService.getAllPayments()).thenReturn(Arrays.asList(payment1, payment2));

        ResponseEntity<List<PaymentResponseDTO>> response = paymentController.getPayments();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(paymentService, times(1)).getAllPayments();
    }

    @Test
    void testAddPayment_Success() {
        PaymentRequestDTO paymentRequest = new PaymentRequestDTO();
        paymentRequest.setCardNumber("1234567812345678");
        paymentRequest.setAmount(new BigDecimal("150.50"));
        paymentRequest.setPaymentDate(LocalDate.now());
        paymentRequest.setDescription("Pago mensual tarjeta de crédito");

        ResponseEntity<String> response = paymentController.addPayment(paymentRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Payment successfully saved in the database", response.getBody());

        verify(paymentService, times(1)).registerPayment(paymentRequest);
    }

    @Test
    void testAddPayment_InvalidRequest() {
        PaymentRequestDTO paymentRequest = new PaymentRequestDTO();
        paymentRequest.setCardNumber(null);

        doThrow(new IllegalArgumentException("Invalid card number")).when(paymentService).registerPayment(paymentRequest);

        ResponseEntity<String> response = paymentController.addPayment(paymentRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid request data: Invalid card number", response.getBody());
    }

    @Test
    void testAddPayment_InternalServerError() {
        PaymentRequestDTO paymentRequest = new PaymentRequestDTO();
        paymentRequest.setCardNumber("1234567812345678");
        paymentRequest.setAmount(new BigDecimal("150.50"));
        paymentRequest.setPaymentDate(LocalDate.now());
        paymentRequest.setDescription("Pago mensual tarjeta de crédito");

        doThrow(new RuntimeException("Unexpected error")).when(paymentService).registerPayment(paymentRequest);

        ResponseEntity<String> response = paymentController.addPayment(paymentRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An error occurred: Unexpected error", response.getBody());
    }
}
