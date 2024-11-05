package com.nttdata.proyectos.pagos.repository;

import com.nttdata.proyectos.pagos.dto.CardRequestDTO;
import com.nttdata.proyectos.pagos.dto.CardResponseDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CardRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static final String FIND_ALL_CARDS = "SELECT card_number FROM cards";
    private static final String ADD_CARD = "INSERT INTO cards (card_number) VALUES (:cardNumber)";
    private static final String FIND_BY_CARD_NUMBER = "SELECT card_number FROM cards WHERE card_number = :cardNumber";

    public CardRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CardResponseDTO> findAll() {
        return jdbcTemplate.query(FIND_ALL_CARDS, new CardRowMapper());
    }

    public int save(CardRequestDTO cardRequestDTO) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("cardNumber", cardRequestDTO.getCardNumber());

        return jdbcTemplate.update(ADD_CARD, parameters);
    }

    public CardResponseDTO findByCardNumber(String cardNumber) {
        MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("cardNumber", cardNumber);

        return jdbcTemplate.query(FIND_BY_CARD_NUMBER, parameters, new CardRowMapper())
                .stream()
                .findFirst()
                .orElse(null);
    }

    public static class CardRowMapper implements RowMapper<CardResponseDTO> {
        @Override
        public CardResponseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new CardResponseDTO(rs.getString("card_number"));
        }
    }
}

