package dc.ufscar.flashlearn.service;

import dc.ufscar.flashlearn.builder.DeckBuilder;
import dc.ufscar.flashlearn.dto.CreateDeckDTO;
import dc.ufscar.flashlearn.dto.DeckDTO;
import dc.ufscar.flashlearn.model.Deck;
import dc.ufscar.flashlearn.repository.DeckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeckService {
    private final DeckRepository deckRepository;
    private final DeckBuilder deckBuilder;

    public List<DeckDTO> findAll() {
        List<Deck> decks = deckRepository.findAll();
        return decks.stream().map(deckBuilder::build).toList();
    }

    public void createDeck(CreateDeckDTO createDeckDTO) {
        Deck deckDomain = deckBuilder.build(createDeckDTO);
        deckRepository.save(deckDomain);
    }

    public Deck findDeckByCategory(String category) {
        return deckRepository.findByCategoryIgnoreCase(category).orElseThrow(() -> new RuntimeException("Deck não foi encontrado"));
    }

    public void deleteDeckByCategory(String category) {
        Deck deck = findDeckByCategory(category);
        deckRepository.delete(deck);
    }

}
