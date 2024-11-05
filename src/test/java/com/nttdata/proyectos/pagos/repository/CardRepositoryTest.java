package com.nttdata.proyectos.pagos.repository;

import com.nttdata.proyectos.pagos.dto.CardRequestDTO;
import com.nttdata.proyectos.pagos.dto.CardResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class CardRepositoryTest {

    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;

    @InjectMocks
    private CardRepository cardRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        CardResponseDTO card1 = new CardResponseDTO("1234567812345678");
        CardResponseDTO card2 = new CardResponseDTO("8765432187654321");
        List<CardResponseDTO> expectedCards = Arrays.asList(card1, card2);

        when(jdbcTemplate.query(anyString(), any(CardRepository.CardRowMapper.class)))
                .thenReturn(expectedCards);

        List<CardResponseDTO> result = cardRepository.findAll();

        assertEquals(expectedCards, result);
        verify(jdbcTemplate, times(1)).query(eq("SELECT card_number FROM cards"), any(CardRepository.CardRowMapper.class));
    }

    @Test
    public void testSave() {
        CardRequestDTO cardRequestDTO = new CardRequestDTO("1234567812345678");
        when(jdbcTemplate.update(anyString(), any(MapSqlParameterSource.class))).thenReturn(1);

        int result = cardRepository.save(cardRequestDTO);

        assertEquals(1, result);
        verify(jdbcTemplate, times(1)).update(eq("INSERT INTO cards (card_number) VALUES (:cardNumber)"), any(MapSqlParameterSource.class));
    }

    @Test
    public void testFindByCardNumber_CardExists() {
        CardResponseDTO expectedCard = new CardResponseDTO("1234567812345678");
        when(jdbcTemplate.query(anyString(), any(MapSqlParameterSource.class), any(CardRepository.CardRowMapper.class)))
                .thenReturn(Arrays.asList(expectedCard));

        CardResponseDTO result = cardRepository.findByCardNumber("1234567812345678");

        assertEquals(expectedCard, result);
        verify(jdbcTemplate, times(1)).query(eq("SELECT card_number FROM cards WHERE card_number = :cardNumber"), any(MapSqlParameterSource.class), any(CardRepository.CardRowMapper.class));
    }

    @Test
    public void testFindByCardNumber_CardDoesNotExist() {
        when(jdbcTemplate.query(anyString(), any(MapSqlParameterSource.class), any(CardRepository.CardRowMapper.class)))
                .thenReturn(Arrays.asList());

        CardResponseDTO result = cardRepository.findByCardNumber("nonexistent");

        assertNull(result);
        verify(jdbcTemplate, times(1)).query(eq("SELECT card_number FROM cards WHERE card_number = :cardNumber"), any(MapSqlParameterSource.class), any(CardRepository.CardRowMapper.class));
    }
}
