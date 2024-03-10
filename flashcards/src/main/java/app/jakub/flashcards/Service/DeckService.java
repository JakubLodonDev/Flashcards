package app.jakub.flashcards.Service;

import app.jakub.flashcards.model.Deck;

import java.util.List;
import java.util.Optional;

public interface DeckService {

    Optional<Deck> findDeckById(Long id);

    List<Deck> findAllDecks();

    Deck saveNewDeck(Deck deck);

    Deck updateDeckData(Deck deck, Deck deckDetails);

    void deleteDeck(Long deckId);

    boolean existsDeckById(Long deckId);
}
