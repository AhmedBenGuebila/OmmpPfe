package org.ommp.ommpspring.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@Table
@NoArgsConstructor
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSite;
    private String nom;
    private String libelleFR;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private UserRegionMaritime userRegionMaritime;
}
