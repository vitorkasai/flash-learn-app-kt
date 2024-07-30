package dc.ufscar.flashlearn.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "flashlearn", name = "tb_card")
public class Card {
    @Id
    @Column(name = "id_card", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tx_front", nullable = false)
    private String front;

    @Column(name = "tx_back", nullable = false)
    private String back;

    @ManyToOne
    @JoinColumn(name = "id_deck", nullable = false)
    @JsonBackReference
    private Deck deck;

}
