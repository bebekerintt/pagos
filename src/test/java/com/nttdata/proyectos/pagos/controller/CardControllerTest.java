package com.nttdata.proyectos.pagos.controller;

import com.nttdata.proyectos.pagos.dto.CardRequestDTO;
import com.nttdata.proyectos.pagos.dto.CardResponseDTO;
import com.nttdata.proyectos.pagos.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.CONFLICT;

public class CardControllerTest {

    @Mock
    private CardService cardService;

    @InjectMocks
    private CardController cardController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddCard_CardCreated() {
        CardRequestDTO cardRequest = new CardRequestDTO("1234567812345678");
        CardResponseDTO cardResponse = new CardResponseDTO("1234567812345678");

        when(cardService.addCard(cardRequest)).thenReturn(cardResponse);

        ResponseEntity<Object> response = cardController.addCard(cardRequest);

        assertEquals(CREATED, response.getStatusCode());
        assertEquals(cardResponse, response.getBody());
        verify(cardService, times(1)).addCard(cardRequest);
    }

    @Test
    public void testAddCard_CardAlreadyExists() {
        CardRequestDTO cardRequest = new CardRequestDTO("1234567812345678");

        when(cardService.addCard(cardRequest)).thenReturn(null);

        ResponseEntity<Object> response = cardController.addCard(cardRequest);

        assertEquals(CONFLICT, response.getStatusCode());
        assertEquals("Card already exists", response.getBody());
        verify(cardService, times(1)).addCard(cardRequest);
    }

    @Test
    public void testGetAllCards() {
        CardResponseDTO card1 = new CardResponseDTO("1234567812345678");
        CardResponseDTO card2 = new CardResponseDTO("8765432187654321");
        List<CardResponseDTO> cards = Arrays.asList(card1, card2);

        when(cardService.getAllCards()).thenReturn(cards);

        ResponseEntity<List<CardResponseDTO>> response = cardController.getAllCards();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cards, response.getBody());
        verify(cardService, times(1)).getAllCards();
    }
}
