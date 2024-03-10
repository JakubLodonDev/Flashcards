package app.jakub.flashcards.Controller;

import app.jakub.flashcards.Service.CardService;
import app.jakub.flashcards.Service.DeckService;
import app.jakub.flashcards.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/decks/{deckId}/cards")
public class CardController {

    CardService cardService;
    DeckService deckService;

    @Autowired
    public CardController(CardService cardService, DeckService deckService) {
        this.cardService = cardService;
        this.deckService = deckService;
    }

    @GetMapping
    public ResponseEntity<List<Card>> getAllCardsByDeckId(@PathVariable Long deckId) {
        return deckService.findDeckById(deckId)
                .map(deck -> {
                    List<Card> cards = cardService.findAllCardsByDeckId(deckId);
                    return ResponseEntity.ok().body(cards);
                }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{cardId}")
    public ResponseEntity<Card> updateCard(@PathVariable Long deckId,
                                           @PathVariable Long cardId,
                                           @RequestBody Card cardDetails){
        return deckService.findDeckById(deckId)
                .map(deck ->
                    cardService.findCardByIdAndDeckId(cardId, deckId)
                            .map(card ->
                                    ResponseEntity.ok().body(cardService.updateCardData(card, cardDetails)))
                            .orElse(ResponseEntity.notFound().build())
                ).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Card> addCardToDeck(@PathVariable Long deckId, @RequestBody Card card) {
        return deckService.findDeckById(deckId)
                .map(deck -> {
                    card.setDeck(deck);
                    return ResponseEntity.ok().body(cardService.saveCard(card));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<?> deleteCard(@PathVariable Long deckId,
                                           @PathVariable Long cardId){
        return deckService.findDeckById(deckId)
                .map(deck ->
                        cardService.findCardByIdAndDeckId(cardId, deckId)
                                .map(card ->{
                                    cardService.deleteCard(cardId, deckId);
                                    return ResponseEntity.ok().build();
                                })
                                .orElse(ResponseEntity.notFound().build())
                ).orElse(ResponseEntity.notFound().build());
    }
}
