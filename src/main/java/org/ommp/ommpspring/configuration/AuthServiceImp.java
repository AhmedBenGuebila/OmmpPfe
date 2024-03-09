package org.ommp.ommpspring.configuration;

import org.ommp.ommpspring.entities.*;
import org.ommp.ommpspring.repositories.UserAdminRepository;
import org.ommp.ommpspring.repositories.UserPortRepository;
import org.ommp.ommpspring.repositories.UserRegionMaritimeRepository;
import org.ommp.ommpspring.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImp implements AuthService {

    private final UserRepository userRepository;
    private final UserRegionMaritimeRepository userRegionMaritimeRepository;
    private final UserPortRepository userPortRepository;
    private final UserAdminRepository userAdminRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImp(UserRepository userRepository,
                          UserRegionMaritimeRepository userRegionMaritimeRepository,
                          UserPortRepository userPortRepository,
                          UserAdminRepository userAdminRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRegionMaritimeRepository = userRegionMaritimeRepository;
        this.userPortRepository = userPortRepository;
        this.userAdminRepository = userAdminRepository;
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

        BeanUtils.copyProperties(signupRequest, user);
        String hashedPassword = passwordEncoder.encode(signupRequest.getPassword());
        user.setPassword(hashedPassword);

        if (user instanceof UserRegionMaritime) {
            userRegionMaritimeRepository.save((UserRegionMaritime) user);
        } else if (user instanceof UserPort) {
            userPortRepository.save((UserPort) user);
        } else {
            userAdminRepository.save((UserAdmin) user);
        }

        return true;
    }
}
