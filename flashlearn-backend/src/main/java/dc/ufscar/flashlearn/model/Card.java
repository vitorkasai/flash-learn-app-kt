package dc.ufscar.flashlearn.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
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
    private Deck deck;
}
