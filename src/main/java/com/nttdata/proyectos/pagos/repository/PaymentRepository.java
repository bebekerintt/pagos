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

    public PaymentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<PaymentResponseDTO> findAll() {
        String sql = "SELECT id, card_number, amount, payment_date, description FROM payments";

        return jdbcTemplate.query(sql, new PaymentRowMapper());
    }

    public int save(PaymentRequestDTO paymentRequest) {
        String sql = "INSERT INTO payments (card_number, amount, payment_date, description) VALUES (?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
                paymentRequest.getCardNumber(),
                paymentRequest.getAmount(),
                paymentRequest.getPaymentDate(),
                paymentRequest.getDescription()
        );
    }

    public static class PaymentRowMapper implements RowMapper<PaymentResponseDTO> {

        @Override
        public PaymentResponseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            PaymentResponseDTO payment = new PaymentResponseDTO();
            payment.setId(rs.getLong("id"));
            payment.setCardNumber(rs.getString("card_number"));
            payment.setAmount(rs.getBigDecimal("amount"));
            payment.setPaymentDate(rs.getDate("payment_date").toLocalDate());
            payment.setDescription(rs.getString("description"));

            return payment;
        }
    }
}
