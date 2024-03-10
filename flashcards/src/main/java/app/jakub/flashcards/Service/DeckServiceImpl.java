package app.jakub.flashcards.Service;

import app.jakub.flashcards.model.Deck;
import app.jakub.flashcards.repo.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeckServiceImpl implements DeckService{

    @Autowired
    DeckRepository deckRepository;

    public Optional<Deck> findDeckById(Long id) {
        return deckRepository.findById(id);
    }

    public List<Deck> findAllDecks() {
        return deckRepository.findAll();
    }

    public void deleteDeck(Long deckId) {
        deckRepository.deleteById(deckId);
    }

    public Deck saveNewDeck(Deck deck) {
        return deckRepository.save(deck);
    }
}
