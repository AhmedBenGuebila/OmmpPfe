package org.ommp.ommpspring.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table
@NoArgsConstructor

public class UserSiege extends User{



    public enum UserSiegeType {

        DIRECTION_QUALITE,
        DIRECTION_RESSOURCES_HUMAINES,
        DIRECTION_FORMATION,
        DIRECTION_ETUDES_DEVELOPPEMENT,
        DIRECTION_APPROVISIONNEMENT_MAINTENANCE,
        DIRECTION_SYSTEMES_INFORMATION,
        DIRECTION_SECURITE_INFORMATION_VEILLE_TECHNOLOGIQUE,
        DIRECTION_FINANCIERES,
        DIRECTION_JURIDIQUES,
        DIRECTION_VEILLE_REGLEMENTAIRE_MARITIME,
        DIRECTION_GENS_MER,
        DIRECTION_FLOTTE_SECURITE_MARITIME,
        DIRECTION_UNITES_FLOTTANTES,
        SPCM,
        DOM

    }
    @Enumerated(EnumType.STRING)
    private UserSiegeType userSiegeType;

    public UserSiege(Long idUser, String nom, String prenom, Long matricule, String email, Long numTel, String password, UserSiegeType userSiegeType ,UserType userType) {
        super(idUser, nom, prenom, matricule, email, numTel, password,userType);
        this.userSiegeType = userSiegeType;
    }



}
