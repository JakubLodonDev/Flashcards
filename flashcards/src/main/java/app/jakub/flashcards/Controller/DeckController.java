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

    @GetMapping("/{id}")
    private ResponseEntity<Deck> getDeckById(@PathVariable Long id){
        return deckService.findDeckById(id)
                .map(deck -> ResponseEntity.ok().body(deck))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Deck createDeck(@RequestBody Deck deck){
        return deckService.saveNewDeck(deck);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDeck(@PathVariable Long id) {
        return deckService.findDeckById(id)
                .map(deck -> {
                    deckService.deleteDeck(deck.getDeckId());
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
