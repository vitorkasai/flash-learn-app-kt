package dc.ufscar.flashlearn.repository;

import dc.ufscar.flashlearn.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

}
