package dc.ufscar.flashlearn.controller;

import dc.ufscar.flashlearn.dto.CreateDeckDTO;
import dc.ufscar.flashlearn.dto.DeckDTO;
import dc.ufscar.flashlearn.service.DeckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DeckController {
    private final DeckService deckService;

    @GetMapping("/deck")
    public ResponseEntity<?> findAllDecks() {
        List<DeckDTO> decks = deckService.findAll();
        return new ResponseEntity<>(decks, HttpStatus.OK);
    }

    @PostMapping("/deck")
    public ResponseEntity<?> createDeck(@RequestBody CreateDeckDTO createDeckDTO) {
        deckService.createDeck(createDeckDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/deck/{category}")
    public ResponseEntity<?> deleteDeck(@PathVariable String category) {
        deckService.deleteDeckByCategory(category);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
