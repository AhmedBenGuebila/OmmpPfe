package org.ommp.ommpspring.configuration;
import jakarta.persistence.*;
import lombok.*;
import org.ommp.ommpspring.entities.UserAdmin;
import org.ommp.ommpspring.entities.UserPort;
import org.ommp.ommpspring.entities.UserRegionMaritime;
import org.ommp.ommpspring.entities.UserType;


@Getter
@Setter

public class SignupRequest {
    private String nom;
    private String prenom;
    private int cin;
    private String email;
    private Long numTel;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType userType; // Enum defining user types
    @Enumerated(EnumType.STRING)
    private UserAdmin.userAdminType userAdminType;
    @Enumerated(EnumType.STRING)
    private UserPort.Port port;
    @Enumerated(EnumType.STRING)
    private UserPort.UserPortType userPortType;
    @Enumerated(EnumType.STRING)
    private UserRegionMaritime.Region region;
    @Enumerated(EnumType.STRING)
    private UserRegionMaritime.UserRegionMaritimeType userRegionMaritimeType;


}
