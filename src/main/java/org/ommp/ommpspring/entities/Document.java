package org.ommp.ommpspring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@Table
@NoArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDocument;
    private String titre;
    @DateTimeFormat
    private LocalDate dateCreation;
    @DateTimeFormat
    private LocalDate dateMiseAJour;
    private  String url;


    @Enumerated(EnumType.STRING)
    private Document.DocumentType documentType;
    @Enumerated(EnumType.STRING)
    private Document.Type type;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<TypeSMQ> typeSMQ;



    public enum TypeSMQ {
        port,region_maritime,siege
    }
    public enum Type {
        procedure, precess ,instruction_de_travail ,politique_qualitee , manuel_qualite , tableau_de_bord , autre
    }
    public enum DocumentType {
        test1, test2 ,test3
    }

    public void addUser(User user) {
        this.users.add(user);
        user.getDocuments().add(this);
    }

    public void removeUser(User user) {
        this.users.remove(user);
        user.getDocuments().remove(this);
    }

    @ManyToMany(mappedBy = "documents")
    private Set<User> users;


}
