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

    @PostMapping
    public ResponseEntity<Card> addCardToDeck(@PathVariable Long deckId, @RequestBody Card card) {
        return deckService.findDeckById(deckId)
                .map(deck -> {
                    card.setDeck(deck);
                    Card newCard = cardService.saveCard(card);
                    return ResponseEntity.ok().body(newCard);
                }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Card>> getAllCardsByDeckId(@PathVariable Long deckId) {
        return deckService.findDeckById(deckId)
                .map(deck -> {
                    List<Card> cards = cardService.findAllCardsByDeckId(deckId);
                    return ResponseEntity.ok().body(cards);
                }).orElse(ResponseEntity.notFound().build());
    }


}
