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

public class UserAdmin extends User{



    public enum userTypeAdmin {
        admin,superAdmin
    }
    @Enumerated(EnumType.STRING)
    private UserAdmin.userTypeAdmin userTypeAdmin;

    public UserAdmin(Long idUser, String nom, String prenom, int cin, String email, Long numTel, String password, UserPort.Port port, UserPort.UserPortType userPortType) {
        super(idUser, nom, prenom, cin, email, numTel, password);
        this.userTypeAdmin = userTypeAdmin;
    }



}
