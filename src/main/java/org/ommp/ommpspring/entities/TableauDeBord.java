package org.ommp.ommpspring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@Table
@NoArgsConstructor
public class TableauDeBord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTB;

    private UserPort.Port port;
    private UserRegionMaritime.Region region;

    @OneToMany(mappedBy = "tableauDeBord", cascade = CascadeType.ALL)
    private Set<DonneesMensuelles> donneesMensuellesSet;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "idTBF")
    private TableauDeBordFinal tableauDeBordFinal;


}