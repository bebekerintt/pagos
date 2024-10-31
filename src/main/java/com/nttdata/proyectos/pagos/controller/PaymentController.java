package com.nttdata.proyectos.pagos.controller;

import com.nttdata.proyectos.pagos.service.PaymentService;
import com.nttdata.proyectos.pagos.dto.PaymentRequestDTO;
import com.nttdata.proyectos.pagos.dto.PaymentResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Operation(summary = "Retrieve all payments", description = "Fetches a list of all payments from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payments retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<PaymentResponseDTO>> getPayments() {
        List<PaymentResponseDTO> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    @Operation(summary = "Add a new payment", description = "Registers a new payment in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Payment created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<String> addPayment(@RequestBody PaymentRequestDTO paymentRequest) {
        try {
            paymentService.registerPayment(paymentRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Payment successfully saved in the database");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid request data: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}
