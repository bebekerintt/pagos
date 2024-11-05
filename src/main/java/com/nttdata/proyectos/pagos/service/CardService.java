package com.nttdata.proyectos.pagos.service;

import com.nttdata.proyectos.pagos.dto.CardRequestDTO;
import com.nttdata.proyectos.pagos.dto.CardResponseDTO;
import com.nttdata.proyectos.pagos.repository.CardRepository;
import com.nttdata.proyectos.pagos.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public CardResponseDTO addCard(CardRequestDTO cardRequestDTO) {
        CardResponseDTO existingCard = cardRepository.findByCardNumber(cardRequestDTO.getCardNumber());
        if (existingCard != null) {
            return null;
        }

        cardRepository.save(cardRequestDTO);
        return cardRepository.findByCardNumber(cardRequestDTO.getCardNumber());
    }

    public List<CardResponseDTO> getAllCards() {
        return cardRepository.findAll();
    }
}
