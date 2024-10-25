package com.nttdata.proyectos.pagos.model;

import io.swagger.annotations.ApiParam;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiParam(value = "ID del pago", example = "1")
    private Long id;

    @Column(name = "card_number", nullable = false)
    @ApiParam(value = "Número de la tarjeta", example = "1234567812345678")
    private String cardNumber;

    @Column(name = "amount", nullable = false)
    @ApiParam(value = "Cantidad del pago", example = "150.50")
    private BigDecimal amount;

    @Column(name = "payment_date", nullable = false)
    @ApiParam(value = "Fecha de pago", example = "2023-10-20")
    private LocalDate paymentDate;

    @Column(name = "description")
    @ApiParam(value = "Descripción del pago", example = "Pago mensual tarjeta de crédito")
    private String description;

    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt = LocalDate.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

}

