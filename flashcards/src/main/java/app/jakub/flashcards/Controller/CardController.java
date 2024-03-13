package app.jakub.flashcards.Controller;

import app.jakub.flashcards.Service.CardService;
import app.jakub.flashcards.Service.DeckService;
import app.jakub.flashcards.model.Card;
import app.jakub.flashcards.model.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardService.findAllCardsByDeckId(deckId));
    }

    @PostMapping
    public ResponseEntity<Card> addCardToDeck(@PathVariable Long deckId,
                                              @RequestBody Card card) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardService.saveCard(deckId, card));
    }

    @PutMapping("/{cardId}")
    public ResponseEntity<Card> updateCard(@PathVariable Long deckId,
                                           @PathVariable Long cardId,
                                           @RequestBody Card cardDetails) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardService.updateCardData(cardId, deckId, cardDetails));
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<HttpStatus> deleteCard(@PathVariable Long deckId,
                                           @PathVariable Long cardId) throws Exception {
        cardService.deleteCard(cardId, deckId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
