package app.jakub.flashcards.Controller;

import app.jakub.flashcards.Service.DeckService;
import app.jakub.flashcards.model.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")
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
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(deckService.findDeckById(deckId));
    }

    @PostMapping
    public ResponseEntity<Deck> createDeck(@RequestBody Deck deck) throws Exception {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(deckService.saveNewDeck(deck));
    }

    @PutMapping("/{deckId}")
    public ResponseEntity<Deck> updateDeck(@PathVariable Long deckId,
                                           @RequestBody Deck deckDetails){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(deckService.updateDeckData(deckId, deckDetails));
    }

    @DeleteMapping("/{deckId}")
    public ResponseEntity<HttpStatus> deleteDeck(@PathVariable Long deckId) {
        deckService.deleteDeck(deckId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
