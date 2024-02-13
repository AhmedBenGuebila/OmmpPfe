package org.ommp.ommpspring.IService;

import org.ommp.ommpspring.entities.User;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface IUserService {
    User saveUser(User user);

    User updateUser(User user) throws ChangeSetPersister.NotFoundException;

    void deleteUser(Long userId) throws ChangeSetPersister.NotFoundException;

    User getUserById(Long userId) throws ChangeSetPersister.NotFoundException;

    List<User> getAllUsers();
}
