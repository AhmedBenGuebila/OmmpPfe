package org.ommp.ommpspring.configuration;

import org.ommp.ommpspring.entities.UserType;

public interface AuthService {

    boolean createUser(SignupRequest signupRequest);
}
