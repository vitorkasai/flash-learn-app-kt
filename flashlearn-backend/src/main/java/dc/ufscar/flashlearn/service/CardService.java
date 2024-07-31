package dc.ufscar.flashlearn.service;

import dc.ufscar.flashlearn.builder.CardBuilder;
import dc.ufscar.flashlearn.dto.CardDTO;
import dc.ufscar.flashlearn.model.Card;
import dc.ufscar.flashlearn.model.Deck;
import dc.ufscar.flashlearn.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final CardBuilder cardBuilder;
    private final DeckService deckService;

    public List<Card> findCardsByCategory(String category) {
        return cardRepository.findCardByDeck_CategoryIgnoreCase(category);
    }

    public void createCard(CardDTO cardDTO) {
        Card cardDomain = cardBuilder.build(cardDTO);
        Deck deckFound = deckService.findDeckByCategory(cardDTO.getDeckCategory());
        cardDomain.setDeck(deckFound);
        cardRepository.save(cardDomain);
    }

    public void deleteCardById(Long id) {
        cardRepository.deleteById(id);
    }


}
