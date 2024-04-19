package org.ommp.ommpspring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@Table
@NoArgsConstructor
public class DonneesMensuelles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Mois mois;
    private int valeur1;
    private int valeur2;
    private double taux;

    public enum Mois {
        JANVIER, FERVRIER, MARS, AVRIL,MAI ,JUIN,JUILLET,AOUT,SEPTEMBRE,OCTOBRE,NOVEMBRE ,DECEMBRE
    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "idTB")
    private TableauDeBord tableauDeBord;
}
