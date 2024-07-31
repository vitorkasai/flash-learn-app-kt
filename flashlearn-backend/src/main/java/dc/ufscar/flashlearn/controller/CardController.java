package dc.ufscar.flashlearn.controller;

import dc.ufscar.flashlearn.dto.CardDTO;
import dc.ufscar.flashlearn.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CardController {

    private final CardService cardService;

    @GetMapping("/card/{category}")
    public ResponseEntity<?> findCardsByCategory(@PathVariable("category") String category) {
        log.info("Listando cards por categoria de Deck");
        return new ResponseEntity<>(cardService.findCardsByCategory(category), HttpStatus.OK);
    }

    @PostMapping("/card")
    public ResponseEntity<?> createCard(@RequestBody CardDTO cardDTO) {
        cardService.createCard(cardDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/card/{id}")
    public ResponseEntity<?> deleteCard(@PathVariable("id") Long id) {
        cardService.deleteCardById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
