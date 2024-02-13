package org.ommp.ommpspring.entities;

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



    @ManyToMany(mappedBy = "documents")
    private Set<User> users;


}
