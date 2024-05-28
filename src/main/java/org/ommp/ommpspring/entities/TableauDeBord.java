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
public class TableauDeBord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTB;

    private UserPort.Port port;
    private UserRegionMaritime.Region region;



    public void addUser(User user) {
        this.users.add(user);
        user.getTableauDeBords().add(this);
    }

    public void removeUser(User user) {
        this.users.remove(user);
        user.getTableauDeBords().remove(this);
    }

    @OneToMany(mappedBy = "tableauDeBord", cascade = CascadeType.ALL)
    private Set<DonneesMensuelles> donneesMensuellesSet;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "idTBF")
    private TableauDeBordFinal tableauDeBordFinal;



    @ManyToMany(mappedBy = "tableauDeBords", cascade = CascadeType.ALL)
    private Set<User> users;

}