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
    @Enumerated(EnumType.STRING)
    private Mois mois;
    @Enumerated(EnumType.STRING)
    private Semestre semestre;
    @Enumerated(EnumType.STRING)
    private Trimestre trimestre;
    @Enumerated(EnumType.STRING)
    private Annee annee;

    private int valeur1;
    private int valeur2;
    private double taux;

    private String analyse;
    private String FNC;




    public enum Mois {
        JANVIER, FEVRIER, MARS, AVRIL,MAI ,JUIN,JUILLET,AOUT,SEPTEMBRE,OCTOBRE,NOVEMBRE ,DECEMBRE
    }
    public enum Semestre {
        SEMESTRE1,SEMESTRE2
    }
    public enum Trimestre {
        TRIMESTRE1,TRIMESTRE2,TRIMESTRE3
    }
    public enum Annee {
        ANNEE
    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "idTB")
    private TableauDeBord tableauDeBord;
}
