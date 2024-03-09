package app.jakub.flashcards.repo;

import app.jakub.flashcards.model.Deck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeckRepository extends JpaRepository<Deck, Long> {
}
