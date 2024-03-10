package app.jakub.flashcards.Controller;

import app.jakub.flashcards.Service.DeckService;
import app.jakub.flashcards.model.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/decks")
public class DeckController {

    DeckService deckService;

    @Autowired
    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    @GetMapping
    public List<Deck> getAllDecks(){
        return deckService.findAllDecks();
    }

    @GetMapping("/{deckId}")
    public ResponseEntity<Deck> getDeckById(@PathVariable Long deckId){
        return deckService.findDeckById(deckId)
                .map(deck -> ResponseEntity.ok().body(deck))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Deck createDeck(@RequestBody Deck deck){
        return deckService.saveNewDeck(deck);
    }

    @PutMapping("/{deckId}")
    public ResponseEntity<Deck> updateDeck(@PathVariable Long deckId,
                                           @RequestBody Deck deckDetails){
        return deckService.findDeckById(deckId)
                .map(deck ->
                        ResponseEntity.ok().body(deckService.updateDeckData(deck, deckDetails))
                ).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{deckId}")
    public ResponseEntity<?> deleteDeck(@PathVariable Long deckId) {
        return deckService.findDeckById(deckId)
                .map(deck -> {
                    deckService.deleteDeck(deck.getDeckId());
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
