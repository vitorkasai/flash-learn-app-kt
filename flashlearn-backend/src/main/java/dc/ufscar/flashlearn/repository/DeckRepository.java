package dc.ufscar.flashlearn.repository;

import dc.ufscar.flashlearn.model.Deck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeckRepository extends JpaRepository<Deck, Long> {

    Optional<Deck> findByCategoryIgnoreCase(String category);
}
