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
    public User updateUser(User user)  { return userRepository.save(user);}

    @Override
    public void deleteUser(Long userId){userRepository.deleteById(userId);}
    @Override
    public Optional<User> getUserById(Long userId) {return userRepository.findById(userId);}

    @Override
    public List<User> getAllUsers() {return userRepository.findAll();}


}