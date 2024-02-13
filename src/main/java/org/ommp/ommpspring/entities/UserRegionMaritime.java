package org.ommp.ommpspring.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.validation.constraints.Email;
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

    public enum UserTypeRegionMaritime {
        DIRECTEUR_DE_PORT,
        COORDINATEUR_QUALITE,
        CHEF_DIVISION_EXPLOITATEUR,
        CHEF_SERVICE_EXPLOITATEUR,
        CHEF_DIVISION_CAPITAINERIE,
        CHEF_SERVICE_ARMEMENT,
        CHEF_SERVICE_SURETE_SECURITE,
        CHEF_DIVISION_TECHNIQUE,
        CHEF_SERVICE_MAINTENANCE,
        CHEF_DIVISION_ADMINISTRATIF_FINANCIER,
        CHEF_SERVICE_ADMINISTRATIF,
        CHEF_SECTION_ACHAT,
        CHEF_SERVICE_FINANCIER,
        CHEF_SECTION_COMPTABILITE,
        CHEF_DIVISION_GARRE_MARITIME,
        CHEF_SERVICE_GARRE_MARITIME
    }

    @Enumerated(EnumType.STRING)
    private Region region;

    @Enumerated(EnumType.STRING)
    private UserTypeRegionMaritime userTypeRegionMaritime;

    public UserRegionMaritime(Long idUser, String nom, String prenom, int cin, String email, Long numTel, String password, Region region, UserTypeRegionMaritime userTypeRegionMaritime) {
        super(idUser, nom, prenom, cin, email, numTel, password);
        this.region = region;
        this.userTypeRegionMaritime = userTypeRegionMaritime;
    }

    @OneToMany(mappedBy = "userRegionMaritime", cascade = CascadeType.ALL)
    private Set<Site> sites;


}
