package org.ommp.ommpspring.configuration;

import org.ommp.ommpspring.entities.UserType;

public interface AuthService {

    boolean createUser(SignupRequest signupRequest);
    boolean updateUser(SignupRequest signupRequest,long id);

    boolean testUser(SignupRequest signupRequest, long id);
}
