package app.jakub.flashcards.Service;

import app.jakub.flashcards.model.Card;

import java.util.List;
import java.util.Optional;

public interface CardService {

    Optional<Card> findCardByIdAndDeckId(Long cardId, Long deckId);
    List<Card> findAllCardsByDeckId(Long deckId);
    Card saveCard(Card card);
    Card updateCardData(Card card, Card cardDetails);
    void deleteCard(Long cardId, Long deckId);
}
