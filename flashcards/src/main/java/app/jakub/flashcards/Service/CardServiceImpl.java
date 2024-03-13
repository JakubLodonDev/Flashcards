package app.jakub.flashcards.Service;

import app.jakub.flashcards.exception.ResourceNotFoundException;
import app.jakub.flashcards.model.Card;
import app.jakub.flashcards.model.Deck;
import app.jakub.flashcards.repo.CardRepository;
import app.jakub.flashcards.repo.DeckRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService{

    CardRepository cardRepository;
    DeckRepository deckRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository, DeckRepository deckRepository) {
        this.cardRepository = cardRepository;
        this.deckRepository = deckRepository;
    }
    public Card findCardByIdAndDeckId(Long cardId, Long deckId) throws Exception {
        Card _card = cardRepository.findCardByCardIdAndDeck_DeckId(cardId, deckId);
        if(_card == null){
            throw new Exception("Card with this id " + cardId + " not exists.");
        }
        return _card;
    }

    public List<Card> findAllCardsByDeckId(Long deckId) {
        findDeckById(deckId);
        return cardRepository.findCardsByDeck_DeckId(deckId);
    }

    @Transactional
    public Card saveCard(Long deckId, Card card) {
        Deck _deck = findDeckById(deckId);
        card.setDeck(_deck);
        return cardRepository.save(card);
    }

    @Transactional
    public Card updateCardData(Long deckId, Long cardId, Card cardDetails) throws Exception {
        findDeckById(deckId);
        Card _card = findCardByIdAndDeckId(cardId, deckId);
        _card.updateData(cardDetails.getFront(), cardDetails.getBack());
        return cardRepository.save(_card);
    }

    @Transactional
    public void deleteCard(Long cardId, Long deckId) throws Exception {
        findDeckById(deckId);
        findCardByIdAndDeckId(cardId, deckId);
        cardRepository.delete(cardRepository.findCardByCardIdAndDeck_DeckId(cardId, deckId));
    }

    public Deck findDeckById(Long deckId) {
        return deckRepository.findById(deckId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Not found Deck with id = " + deckId));
    }
}
