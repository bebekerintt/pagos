package com.nttdata.proyectos.pagos.repository;

import com.nttdata.proyectos.pagos.dto.PaymentRequestDTO;
import com.nttdata.proyectos.pagos.dto.PaymentResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PaymentRepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        PaymentResponseDTO payment = new PaymentResponseDTO(
                1L,
                "1234567812345678",
                new BigDecimal("100.00"),
                LocalDate.now(),
                "Pago 1");
        when(jdbcTemplate.query(any(String.class), any(PaymentRepository.PaymentRowMapper.class)))
                .thenReturn(Collections.singletonList(payment));

        List<PaymentResponseDTO> result = paymentRepository.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("1234567812345678", result.get(0).getCardNumber());
        assertEquals(new BigDecimal("100.00"), result.get(0).getAmount());
    }

    @Test
    public void testSave() {
        PaymentRequestDTO paymentRequest = new PaymentRequestDTO();
        paymentRequest.setCardNumber("1234567812345678");
        paymentRequest.setAmount(new BigDecimal("150.50"));
        paymentRequest.setPaymentDate(LocalDate.now());
        paymentRequest.setDescription("Pago de prueba");

        when(jdbcTemplate.update(any(String.class), any(), any(), any(), any())).thenReturn(1);

        int rowsAffected = paymentRepository.save(paymentRequest);
        assertEquals(1, rowsAffected);
        verify(jdbcTemplate, times(1)).update(any(String.class), any(), any(), any(), any());
    }

}

