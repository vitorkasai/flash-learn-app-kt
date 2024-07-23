package dc.ufscar.flashlearn.dto;

import dc.ufscar.flashlearn.model.Card;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
public class DeckDTO {
    private Long id;
    private String category;
    private List<Card> cards;
}
