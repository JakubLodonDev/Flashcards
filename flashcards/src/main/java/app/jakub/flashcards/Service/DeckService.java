package app.jakub.flashcards.Service;

import app.jakub.flashcards.model.Deck;

import java.util.List;

public interface DeckService {

    Deck findDeckById(Long id);

    List<Deck> findAllDecks();

    Deck saveNewDeck(Deck deck) throws Exception;

    Deck updateDeckData(Long deckId, Deck deckDetails);

    void deleteDeck(Long deckId);
}
