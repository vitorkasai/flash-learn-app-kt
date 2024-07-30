package dc.ufscar.flashlearn.builder;

import dc.ufscar.flashlearn.dto.CreateDeckDTO;
import dc.ufscar.flashlearn.dto.DeckDTO;
import dc.ufscar.flashlearn.model.Deck;
import org.springframework.stereotype.Component;

@Component
public class DeckBuilder {

    public Deck build(CreateDeckDTO dto) {
        return Deck.builder()
                .category(dto.getCategory())
                .build();
    }

    public DeckDTO build(Deck deckDomain) {
        return DeckDTO.builder()
                .id(deckDomain.getId())
                .category(deckDomain.getCategory())
                .cards(deckDomain.getCards())
                .build();
    }
}
