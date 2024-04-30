package org.ommp.ommpspring.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@Table
@NoArgsConstructor
public class TableauDeBordFinal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTBF;
    private String nom;
    private int annee;
    public String axesPolitiqueQualite;
    public String objectifsDuProcessus;
    public String indicateurDeMesure;

    @Enumerated(EnumType.STRING)
    public MethodeDeCalcul methodeDeCalcul;

    @Enumerated(EnumType.STRING)
    public FrequenceDeMesure frequenceDeMesure;
    public float valeurCible;

    @Enumerated(EnumType.STRING)
    public EtatTBF etatTBF;
    @Enumerated(EnumType.STRING)
    public Secteur secteur;

    public enum FrequenceDeMesure {
        MOIS,
        ANNEE,
        SEMESTRE,
        TRIMESTRE
    }

    public enum MethodeDeCalcul {
        M1,
        M2,
        M3,
        M4,
        M5
    }
    public enum EtatTBF {
        approuve , enAttente
    }
    public enum Secteur {
        PORT , REGION, SIEGE
    }

    @OneToMany(mappedBy = "tableauDeBordFinal", cascade = CascadeType.ALL)
    private Set<TableauDeBord> tableauDeBordSet;
}
