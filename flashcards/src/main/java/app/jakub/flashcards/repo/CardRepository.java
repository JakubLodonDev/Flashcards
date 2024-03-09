package app.jakub.flashcards.repo;

import app.jakub.flashcards.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
