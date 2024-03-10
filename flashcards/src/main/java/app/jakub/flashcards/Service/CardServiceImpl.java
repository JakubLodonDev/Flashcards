package app.jakub.flashcards.Service;

import app.jakub.flashcards.model.Card;
import app.jakub.flashcards.model.Deck;
import app.jakub.flashcards.repo.CardRepository;
import app.jakub.flashcards.repo.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService{

    CardRepository cardRepository;
    DeckRepository deckRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository, DeckRepository deckRepository) {
        this.cardRepository = cardRepository;
        this.deckRepository = deckRepository;
    }
    public Optional<Card> findCardByIdAndDeckId(Long cardId, Long deckId){
        return Optional.ofNullable(cardRepository.findCardByCardIdAndDeck_DeckId(cardId, deckId));
    }

    public List<Card> findAllCardsByDeckId(Long deckId) {
        return cardRepository.findCardsByDeck_DeckId(deckId);
    }

    public Card saveCard(Card card) {
        return cardRepository.save(card);
    }

    public Card updateCardData(Card card, Card cardDetails){
        card.updateData(cardDetails.getFront(), cardDetails.getBack());
        return cardRepository.save(card);
    }

    public void deleteCard(Long cardId, Long deckId){
        cardRepository.delete(cardRepository.findCardByCardIdAndDeck_DeckId(cardId, deckId));
    }
}
