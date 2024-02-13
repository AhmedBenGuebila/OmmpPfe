package org.ommp.ommpspring.services;

import org.ommp.ommpspring.IService.IUserService;
import org.ommp.ommpspring.entities.User;
import org.ommp.ommpspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) throws ChangeSetPersister.NotFoundException {
        if (userRepository.existsById(user.getIdUser())) {
            return userRepository.save(user);
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public void deleteUser(Long userId) throws ChangeSetPersister.NotFoundException {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public User getUserById(Long userId) throws ChangeSetPersister.NotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}