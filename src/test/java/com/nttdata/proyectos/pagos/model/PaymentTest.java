package com.nttdata.proyectos.pagos.model;

import com.nttdata.proyectos.pagos.model.Payment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class PaymentTest {

    @Test
    public void testPaymentGettersAndSetters() {
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setCardNumber("1234567812345678");
        payment.setAmount(new BigDecimal("100.00"));
        payment.setPaymentDate(LocalDate.now());
        payment.setDescription("Pago de prueba");
        payment.setCreatedAt(LocalDate.now());

        assertEquals(1L, payment.getId());
        assertEquals("1234567812345678", payment.getCardNumber());
        assertEquals(new BigDecimal("100.00"), payment.getAmount());
        assertNotNull(payment.getPaymentDate());
        assertEquals("Pago de prueba", payment.getDescription());
        assertNotNull(payment.getCreatedAt());
    }
}
