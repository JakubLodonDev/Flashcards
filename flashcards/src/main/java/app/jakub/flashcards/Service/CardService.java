package app.jakub.flashcards.Service;

import app.jakub.flashcards.model.Card;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CardService {
    Card saveCard(Card card);
    List<Card> findAllCardsByDeckId(Long deckId);
}
