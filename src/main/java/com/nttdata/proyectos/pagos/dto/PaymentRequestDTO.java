package com.nttdata.proyectos.pagos.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PaymentRequestDTO {
    private String cardNumber;  // VARCHAR(255)
    private BigDecimal amount;  // NUMERIC(38,2)
    private LocalDate paymentDate;  // DATE
    private String description;  // VARCHAR(255), opcional

    // Getters y Setters
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
