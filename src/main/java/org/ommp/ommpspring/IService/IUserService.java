package org.ommp.ommpspring.IService;

import org.ommp.ommpspring.entities.User;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public interface IUserService {
    User saveUser(User user);

    User updateUser(User user) ;

    void deleteUser(Long userId);

    Optional<User> getUserById(Long userId) ;

    List<User> getAllUsers();


}
