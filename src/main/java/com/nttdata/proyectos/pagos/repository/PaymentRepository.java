package com.nttdata.proyectos.pagos.repository;

import com.nttdata.proyectos.pagos.dto.PaymentResponseDTO;
import com.nttdata.proyectos.pagos.dto.PaymentRequestDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class PaymentRepository {

    private final JdbcTemplate jdbcTemplate;

    // Inyectamos el JdbcTemplate en el constructor
    public PaymentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Método para obtener todos los pagos
    public List<PaymentResponseDTO> findAll() {
        String sql = "SELECT id, card_number, amount, payment_date, description FROM payments";

        return jdbcTemplate.query(sql, new PaymentRowMapper());
    }

    // Método para registrar un nuevo pago
    public int save(PaymentRequestDTO paymentRequest) {
        String sql = "INSERT INTO payments (card_number, amount, payment_date, description) VALUES (?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
                paymentRequest.getCardNumber(),
                paymentRequest.getAmount(),
                paymentRequest.getPaymentDate(),
                paymentRequest.getDescription()
        );
    }

    // Implementamos el RowMapper para mapear los resultados a un PaymentResponseDTO
    private static class PaymentRowMapper implements RowMapper<PaymentResponseDTO> {

        @Override
        public PaymentResponseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            PaymentResponseDTO payment = new PaymentResponseDTO();
            payment.setId(rs.getLong("id"));
            payment.setCardNumber(rs.getString("card_number"));  // Sin enmascarar
            payment.setAmount(rs.getBigDecimal("amount"));
            payment.setPaymentDate(rs.getDate("payment_date").toLocalDate());
            payment.setDescription(rs.getString("description"));

            return payment;
        }
    }
}
