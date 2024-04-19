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

    public UserAdmin(Long idUser, String nom, String prenom, Long matricule, String email, Long numTel, String password, UserAdmin.userAdminType userAdminType,UserType userType) {
        super(idUser, nom, prenom, matricule, email, numTel, password,userType);
        this.userAdminType = userAdminType;
    }



}
