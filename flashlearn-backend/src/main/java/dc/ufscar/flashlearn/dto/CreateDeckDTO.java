package dc.ufscar.flashlearn.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CreateDeckDTO {
    private String category;
}
