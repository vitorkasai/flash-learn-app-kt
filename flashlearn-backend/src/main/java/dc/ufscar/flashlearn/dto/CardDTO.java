package dc.ufscar.flashlearn.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class CardDTO {
    private Long id;
    private String front;
    private String back;
    private String deckCategory;
}
