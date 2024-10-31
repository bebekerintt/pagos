package com.nttdata.proyectos.pagos.repository;

import com.nttdata.proyectos.pagos.dto.PaymentResponseDTO;
import com.nttdata.proyectos.pagos.dto.PaymentRequestDTO;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class PaymentRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String GET_ALL_PAYMENTS_SQL = "SELECT id, card_number, amount, payment_date, description FROM payments";
    private static final String ADD_PAYMENT_SQL = "INSERT INTO payments (card_number, amount, payment_date, description) " +
                                                      "VALUES (:cardNumber, :amount, :paymentDate, :description)";

    public PaymentRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<PaymentResponseDTO> findAll() {
        return namedParameterJdbcTemplate.query(GET_ALL_PAYMENTS_SQL, new PaymentRowMapper());
    }

    public int save(PaymentRequestDTO paymentRequest) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("cardNumber", paymentRequest.getCardNumber());
        parameters.addValue("amount", paymentRequest.getAmount());
        parameters.addValue("paymentDate", paymentRequest.getPaymentDate());
        parameters.addValue("description", paymentRequest.getDescription());

        return namedParameterJdbcTemplate.update(ADD_PAYMENT_SQL, parameters);
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
