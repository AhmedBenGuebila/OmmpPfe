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

public class UserPort extends User{


        public enum Port {
            RADES, BIZERTE, GOULETTE, SOUSSE, SFAX, GABES, ZARZIS
        }

        public enum UserPortType {
            CHEF_REGION_MARITIME,
            COORDINATEUR_QUALITE,
            CHEF_DIVISION_SECURITE_MARITIME,
            CHEF_SERVICE_SECURITE_MARITIME,
            CHEF_QUARTIER,
            CHARGE_BUREAU_FLOTTE,
            CHARGE_BUREAU_GENS_DE_MER,
            CHEF_QUARTIER_MARITIME
        }

        @Enumerated(EnumType.STRING)
        private Port port;

        @Enumerated(EnumType.STRING)
        private UserPortType userPortType;

        public UserPort(Long idUser, String nom, String prenom, int cin, String email, Long numTel, String password, Port port, UserPortType userPortType) {
            super(idUser, nom, prenom, cin, email, numTel, password);
            this.port = port;
            this.userPortType = userPortType;
        }




}
