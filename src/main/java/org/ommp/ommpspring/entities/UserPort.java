package org.ommp.ommpspring.entities;

import jakarta.persistence.*;
import lombok.*;

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
        private Port port;

        @Enumerated(EnumType.STRING)
        private UserPortType userPortType;

        public UserPort(Long idUser, String nom, String prenom, int cin, String email, Long numTel, String password, Port port, UserPortType userPortType) {
            super(idUser, nom, prenom, cin, email, numTel, password);
            this.port = port;
            this.userPortType = userPortType;
        }




}
