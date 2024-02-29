package org.ommp.ommpspring.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table
@NoArgsConstructor

public class UserAdmin extends User{



    public enum userAdminType {
        admin,superAdmin
    }
    @Enumerated(EnumType.STRING)
    private UserAdmin.userAdminType userAdminType;

    public UserAdmin(Long idUser, String nom, String prenom, int cin, String email, Long numTel, String password, UserPort.Port port, UserPort.UserPortType userPortType) {
        super(idUser, nom, prenom, cin, email, numTel, password);
        this.userAdminType = userAdminType;
    }



}
