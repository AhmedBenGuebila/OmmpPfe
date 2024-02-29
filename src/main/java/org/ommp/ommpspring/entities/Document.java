package org.ommp.ommpspring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
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
    private Document.ConcerneType concerneType;



    public enum ConcerneType {
        port,regionMaritime
    }
    public enum DocumentType {
        procedure, precess ,instruction ,PQ , MQ , TB
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
