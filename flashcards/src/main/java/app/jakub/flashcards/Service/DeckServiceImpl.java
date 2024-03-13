package app.jakub.flashcards.Service;

import app.jakub.flashcards.exception.ResourceNotFoundException;
import app.jakub.flashcards.model.Deck;
import app.jakub.flashcards.repo.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeckServiceImpl implements DeckService{

    @Autowired
    DeckRepository deckRepository;

    public Deck findDeckById(Long deckId) {
        return deckRepository.findById(deckId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Not found Deck with id = " + deckId));
    }

    public List<Deck> findAllDecks() {
        return deckRepository.findAll();
    }

    public Deck saveNewDeck(Deck deck) throws Exception {
        boolean exists = deckRepository.existsByName(deck.getName());
        if (exists) {
            throw new Exception("Deck with name " + deck.getName() + " already exists.");
        }
        return deckRepository.save(deck);
    }

    public Deck updateDeckData(Long deckId, Deck deckDetails) {
        Deck _deck = findDeckById(deckId);
        _deck.updateData(deckDetails.getName());
        return deckRepository.save(_deck);
    }

    public void deleteDeck(Long deckId) {
        findDeckById(deckId);
        deckRepository.deleteById(deckId);
    }

    public boolean existsDeckById(Long deckId){
        return deckRepository.existsById(deckId);
    }
}
