package dc.ufscar.flashlearn.controller;

import dc.ufscar.flashlearn.dto.CardDTO;
import dc.ufscar.flashlearn.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping("/card")
    public ResponseEntity<?> createCard(@RequestBody CardDTO cardDTO) {
        cardService.createCard(cardDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
