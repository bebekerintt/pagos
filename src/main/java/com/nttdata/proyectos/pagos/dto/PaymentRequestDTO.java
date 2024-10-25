package com.nttdata.proyectos.pagos.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PaymentRequestDTO {
    private String cardNumber;
    private BigDecimal amount;
    private LocalDate paymentDate;
    private String description;

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
