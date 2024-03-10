package app.jakub.flashcards.Service;

import app.jakub.flashcards.model.Deck;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DeckService {

    Optional<Deck> findDeckById(Long id);

    List<Deck> findAllDecks();

    void deleteDeck(Long deckId);

    Deck saveNewDeck(Deck deck);
}
