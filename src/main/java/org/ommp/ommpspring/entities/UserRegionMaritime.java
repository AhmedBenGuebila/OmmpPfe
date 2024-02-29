package org.ommp.ommpspring.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table
@NoArgsConstructor

public class UserRegionMaritime extends User{


    public enum Region {
        BIZERTE, TUNIS, SOUSSE, MONASTIR, SFAX, GABES, DJERBA
    }

    public enum UserRegionMaritimeType {
        CHEF_REGION_MARITIME,
        COORDINATEUR_QUALITE,
        CHEF_DIVISION_SECURITE_MARITIME,
        CHEF_SERVICE_SECURITE_MARITIME,
        CHEF_QUARTIER,
        CHARGE_BUREAU_FLOTTE,
        CHARGE_BUREAU_GENS_DE_MER,
        CHEF_SOUS_QUARTIER_MARITIME,
        CHEF_QUARTIER_MARITIME
    }

    @Enumerated(EnumType.STRING)
    private Region region;

    @Enumerated(EnumType.STRING)
    private UserRegionMaritimeType userRegionMaritimeType;

    public UserRegionMaritime(Long idUser, String nom, String prenom, int cin, String email, Long numTel, String password, Region region, UserRegionMaritimeType userRegionMaritimeType) {
        super(idUser, nom, prenom, cin, email, numTel, password);
        this.region = region;
        this.userRegionMaritimeType = userRegionMaritimeType;
    }

    @OneToMany(mappedBy = "userRegionMaritime", cascade = CascadeType.ALL)
    private Set<Site> sites;


}
