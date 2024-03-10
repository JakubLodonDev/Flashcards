package app.jakub.flashcards.repo;

import app.jakub.flashcards.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    Card findCardByCardIdAndDeck_DeckId(Long cardId, Long deckId);
    List<Card> findCardsByDeck_DeckId(Long deckId);
}
