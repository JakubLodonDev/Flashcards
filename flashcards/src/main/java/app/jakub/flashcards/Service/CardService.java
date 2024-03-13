package app.jakub.flashcards.Service;

import app.jakub.flashcards.model.Card;
import app.jakub.flashcards.model.Deck;

import java.util.List;

public interface CardService {

    Deck findDeckById(Long id);
    Card findCardByIdAndDeckId(Long cardId, Long deckId) throws Exception;
    List<Card> findAllCardsByDeckId(Long deckId);
    Card saveCard(Long deckId, Card card);
    Card updateCardData(Long deckId, Long cardId, Card cardDetails) throws Exception;
    void deleteCard(Long cardId, Long deckId) throws Exception;
}
