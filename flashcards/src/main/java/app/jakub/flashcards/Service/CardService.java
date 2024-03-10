package app.jakub.flashcards.Service;

import app.jakub.flashcards.model.Card;
import app.jakub.flashcards.repo.CardRepository;
import app.jakub.flashcards.repo.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {
    CardRepository cardRepository;
    DeckRepository deckRepository;

    @Autowired
    public CardService(CardRepository cardRepository, DeckRepository deckRepository) {
        this.cardRepository = cardRepository;
        this.deckRepository = deckRepository;
    }
    public Card saveCard(Card card) {
        return cardRepository.save(card);
    }

    public List<Card> findAllCardsByDeckId(Long deckId) {
        return cardRepository.findCardsByDeck_DeckId(deckId);
    }
}
