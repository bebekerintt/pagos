package com.nttdata.proyectos.pagos.controller;

import com.nttdata.proyectos.pagos.dto.CardRequestDTO;
import com.nttdata.proyectos.pagos.dto.CardResponseDTO;
import com.nttdata.proyectos.pagos.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<Object> addCard(@RequestBody CardRequestDTO cardRequestDTO) {
        CardResponseDTO cardResponseDTO = cardService.addCard(cardRequestDTO);
        if (cardResponseDTO != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(cardResponseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Card already exists");
        }
    }

    @GetMapping
    public ResponseEntity<List<CardResponseDTO>> getAllCards() {
        List<CardResponseDTO> cards = cardService.getAllCards();
        return ResponseEntity.ok(cards);
    }
}
