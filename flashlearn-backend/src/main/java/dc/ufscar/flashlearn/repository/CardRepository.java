package dc.ufscar.flashlearn.repository;

import dc.ufscar.flashlearn.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findCardByDeck_CategoryIgnoreCase(String category);
}
