package com.nttdata.proyectos.pagos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO {

    @Schema(description = "Número de la tarjeta", example = "1234567812345678", required = true)
    private String cardNumber;

    @Schema(description = "Cantidad del pago", example = "150.50", required = true)
    private BigDecimal amount;

    @Schema(description = "Fecha de pago", example = "2023-10-20", required = true)
    private LocalDate paymentDate;

    @Schema(description = "Descripción del pago", example = "Pago mensual tarjeta de crédito")
    private String description;

    // Getters y Setters

}
