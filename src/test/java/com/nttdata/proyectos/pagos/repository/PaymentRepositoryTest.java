package com.nttdata.proyectos.pagos.repository;

import com.nttdata.proyectos.pagos.dto.PaymentRequestDTO;
import com.nttdata.proyectos.pagos.dto.PaymentResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PaymentRepositoryTest {

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @InjectMocks
    private PaymentRepository paymentRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        paymentRepository = spy(new PaymentRepository(namedParameterJdbcTemplate));
    }

    @Test
    public void testFindAll() {
        PaymentResponseDTO payment1 = new PaymentResponseDTO(1L, "1234567812345678", BigDecimal.valueOf(150.50), LocalDate.now(), "Pago mensual");
        PaymentResponseDTO payment2 = new PaymentResponseDTO(2L, "8765432187654321", BigDecimal.valueOf(250.00), LocalDate.now(), "Pago anual");

        when(namedParameterJdbcTemplate.query(anyString(), any(PaymentRepository.PaymentRowMapper.class)))
                .thenReturn(Arrays.asList(payment1, payment2));

        List<PaymentResponseDTO> result = paymentRepository.findAll();

        assertEquals(2, result.size());
        assertEquals(payment1, result.get(0));
        assertEquals(payment2, result.get(1));
    }

    @Test
    public void testSave() {
        PaymentRequestDTO paymentRequest = new PaymentRequestDTO("1234567812345678", BigDecimal.valueOf(150.50), LocalDate.now(), "Pago mensual");

        doReturn(true).when(paymentRepository).isCardNumberPresent(paymentRequest.getCardNumber());

        paymentRepository.save(paymentRequest);

        String expectedSql = "INSERT INTO payments (card_number, amount, payment_date, description) VALUES (:cardNumber, :amount, :paymentDate, :description)";
        verify(namedParameterJdbcTemplate).update(eq(expectedSql), any(MapSqlParameterSource.class));
        verify(paymentRepository).isCardNumberPresent(paymentRequest.getCardNumber());
    }
}
