package org.ommp.ommpspring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.validation.constraints.Email;
@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@Table
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String nom;
    private String prenom;
    @Column(unique = true)
    private Long matricule;
    @Email
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private Long numTel;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User(Long idUser, String nom, String prenom, Long matricule, String email, Long numTel, String password,UserType userType) {
    }




    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_document",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "document_id")
    )

    private Set<Document> documents;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_tableau_de_bord",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tableau_de_bord_id")
    )

    private Set<TableauDeBord> tableauDeBords;
}
