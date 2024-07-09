package org.ommp.ommpspring.configuration;

import org.ommp.ommpspring.EmailService;
import org.ommp.ommpspring.entities.*;
import org.ommp.ommpspring.repositories.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImp implements AuthService {

    private final UserRepository userRepository;
    private final UserRegionMaritimeRepository userRegionMaritimeRepository;
    private final UserPortRepository userPortRepository;
    private final UserAdminRepository userAdminRepository;
    private final UserSiegeRepository userSiegeRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    public AuthServiceImp(UserRepository userRepository,
                          UserRegionMaritimeRepository userRegionMaritimeRepository,
                          UserPortRepository userPortRepository,
                          UserAdminRepository userAdminRepository,
                          UserSiegeRepository userSiegeRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRegionMaritimeRepository = userRegionMaritimeRepository;
        this.userPortRepository = userPortRepository;
        this.userAdminRepository = userAdminRepository;
        this.userSiegeRepository = userSiegeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean createUser(SignupRequest signupRequest) {
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return false;
        }

        User user = null;
        if (signupRequest.getUserType() == UserType.REGION_MARITIME) {
            user = new UserRegionMaritime();
        } else if (signupRequest.getUserType() == UserType.PORT) {
            user = new UserPort();
        } else if (signupRequest.getUserType() == UserType.ADMIN) {
            user = new UserAdmin();
        } else if (signupRequest.getUserType() == UserType.SIEGE) {
            user = new UserSiege();
        }

        BeanUtils.copyProperties(signupRequest, user);
        String hashedPassword = passwordEncoder.encode(signupRequest.getPassword());
        user.setPassword(hashedPassword);

        if (user instanceof UserRegionMaritime) {
            userRegionMaritimeRepository.save((UserRegionMaritime) user);
        } else if (user instanceof UserPort) {
            userPortRepository.save((UserPort) user);
        } else if (user instanceof UserAdmin) {
            userAdminRepository.save((UserAdmin) user);
        } else if (user instanceof UserSiege) {
            userSiegeRepository.save((UserSiege) user);
        }

        emailService.sendSimpleMessage(user.getEmail(), "Compte pour la Platforme de l'Office Des ports et des Regions maritime", "Bonjour " + user.getNom() + ",\n\n" +
                "Nous sommes ravis de vous informer que votre compte sur notre plateforme a été créé avec succès.\n\n" +
                "Voici les détails de Connection :\n" +
                "Nom d'utilisateur : " + user.getPrenom() + "\n" +
                "Email : " + user.getEmail() + "\n" +
                "Mot de passe :" + signupRequest.getPassword() + "\n\n" +
                "Vous pouvez maintenant vous connecter et commencer à utiliser nos services.\n\n" +
                "http://localhost:4200/#/authentifications/login\n" +
                "Cordialement,\n" +
                "L'équipe OMMP");

        return true;
    }

    @Override
    public boolean updateUser(SignupRequest signupRequest, long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            User updatedUser = null;
            if (signupRequest.getUserType() == UserType.REGION_MARITIME) {
                updatedUser = new UserRegionMaritime();
            } else if (signupRequest.getUserType() == UserType.PORT) {
                updatedUser = new UserPort();
            } else if (signupRequest.getUserType() == UserType.ADMIN) {
                updatedUser = new UserAdmin();
            } else if (signupRequest.getUserType() == UserType.SIEGE) {
                updatedUser = new UserSiege();
            }

            BeanUtils.copyProperties(user, updatedUser, "id", "password");
            BeanUtils.copyProperties(signupRequest, updatedUser, "password");

            if (!signupRequest.getPassword().equals(user.getPassword())) {
                updatedUser.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
            }

            userRepository.delete(user);
            if (updatedUser instanceof UserAdmin) {
                userAdminRepository.save((UserAdmin) updatedUser);
            } else if (updatedUser instanceof UserPort) {
                userPortRepository.save((UserPort) updatedUser);
            } else if (updatedUser instanceof UserSiege) {
                userSiegeRepository.save((UserSiege) updatedUser);
            } else if (updatedUser instanceof UserRegionMaritime) {
                userRegionMaritimeRepository.save((UserRegionMaritime) updatedUser);
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean testUser(SignupRequest signupRequest, long id) {
        return false;
    }
}
