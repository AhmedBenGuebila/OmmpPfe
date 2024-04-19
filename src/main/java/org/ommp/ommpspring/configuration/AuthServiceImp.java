package org.ommp.ommpspring.configuration;

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
        }  else if (signupRequest.getUserType() == UserType.ADMIN) {
         user = new UserAdmin();
        }
        else if (signupRequest.getUserType() == UserType.SIEGE) {
            user = new UserSiege();
        }

        BeanUtils.copyProperties(signupRequest, user);
        String hashedPassword = passwordEncoder.encode(signupRequest.getPassword());
        user.setPassword(hashedPassword);

        if (user instanceof UserRegionMaritime) {
            userRegionMaritimeRepository.save((UserRegionMaritime) user);
        } else if (user instanceof UserPort) {
            userPortRepository.save((UserPort) user);
        } else if (user instanceof UserAdmin)  {
            userAdminRepository.save((UserAdmin) user);
        }
        else if (user instanceof UserSiege)  {
            userSiegeRepository.save((UserSiege) user);
        }

            return true;
        }

        @Override
        public boolean testUser(SignupRequest signupRequest, long id) {
            Optional<User> optionalUser = userRepository.findById(id);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();


            user.setNom(signupRequest.getNom());
            user.setPrenom(signupRequest.getPrenom());
            user.setMatricule(signupRequest.getMatricule());
            user.setEmail(signupRequest.getEmail());
            user.setNumTel(signupRequest.getNumTel());
            user.setUserType(signupRequest.getUserType());


            if (signupRequest.getUserType() == UserType.ADMIN) {
                ((UserAdmin) user).setUserAdminType(signupRequest.getUserAdminType());
                userAdminRepository.save((UserAdmin) user);
            } else if (signupRequest.getUserType() == UserType.PORT) {
                ((UserPort) user).setPort(signupRequest.getPort());
                ((UserPort) user).setUserPortType(signupRequest.getUserPortType());
                userPortRepository.save((UserPort) user);
            } else if (signupRequest.getUserType() == UserType.SIEGE) {
                ((UserSiege) user).setUserSiegeType(signupRequest.getUserSiegeType());
                userSiegeRepository.save((UserSiege) user);

            } else if (signupRequest.getUserType() == UserType.REGION_MARITIME) {
                ((UserRegionMaritime) user).setRegion(signupRequest.getRegion());
                ((UserRegionMaritime) user).setUserRegionMaritimeType(signupRequest.getUserRegionMaritimeType());
                userRegionMaritimeRepository.save((UserRegionMaritime) user);
            }



            return true;
        } else {
            return false;
        }
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

            // Copie les propriétés de l'utilisateur existant vers le nouvel objet
            updatedUser.setNom(signupRequest.getNom());
            updatedUser.setPrenom(signupRequest.getPrenom());
            updatedUser.setMatricule(signupRequest.getMatricule());
            updatedUser.setEmail(signupRequest.getEmail());
            updatedUser.setNumTel(signupRequest.getNumTel());
            updatedUser.setUserType(signupRequest.getUserType());
            if (signupRequest.getPassword().equals(user.getPassword())){
                updatedUser.setPassword(user.getPassword());
            } else {
                updatedUser.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
            }



            if (updatedUser instanceof UserAdmin) {
                ((UserAdmin) updatedUser).setUserAdminType(signupRequest.getUserAdminType());
                userRepository.delete(user); // Supprime l'ancien utilisateur
                userAdminRepository.save((UserAdmin)updatedUser); // Enregistre le nouvel utilisateur
            } else if (updatedUser instanceof UserPort) {
                ((UserPort) updatedUser).setPort(signupRequest.getPort());
                ((UserPort) updatedUser).setUserPortType(signupRequest.getUserPortType());
                userRepository.delete(user); // Supprime l'ancien utilisateur
                userPortRepository.save((UserPort)updatedUser); // Enregistre le nouvel utilisateur
            } else if (updatedUser instanceof UserSiege) {
                ((UserSiege) updatedUser).setUserSiegeType(signupRequest.getUserSiegeType());
                userRepository.delete(user); // Supprime l'ancien utilisateur
                userSiegeRepository.save((UserSiege)updatedUser); // Enregistre le nouvel utilisateur
            } else if (updatedUser instanceof UserRegionMaritime) {
                ((UserRegionMaritime) updatedUser).setRegion(signupRequest.getRegion());
                ((UserRegionMaritime) updatedUser).setUserRegionMaritimeType(signupRequest.getUserRegionMaritimeType());
                userRepository.delete(user); // Supprime l'ancien utilisateur
                userRegionMaritimeRepository.save((UserRegionMaritime)updatedUser); // Enregistre le nouvel utilisateur
            }


            return true;
        } else {
            return false;
        }
    }

}
