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
    private int annee;
    public String axesPolitiqueQualite;
    public String objectifsDuProcessus;
    public String indicateurDeMesure;
    public String methodeDeCalcul;
    public String frequenceDeMesure;
    public float valeurCible;

    @OneToMany(mappedBy = "tableauDeBordFinal", cascade = CascadeType.ALL)
    private Set<TableauDeBord> tableauDeBordSet;
}
