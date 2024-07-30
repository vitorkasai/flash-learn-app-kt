package dc.ufscar.flashlearn.builder;

import dc.ufscar.flashlearn.dto.CardDTO;
import dc.ufscar.flashlearn.model.Card;
import org.springframework.stereotype.Component;

@Component
public class CardBuilder {

    public Card build(CardDTO dto) {
        return Card.builder()
                .front(dto.getFront())
                .back(dto.getBack())
                .build();
    }

}
