package org.ommp.ommpspring.services;

import org.ommp.ommpspring.IService.IUserPortService;
import org.ommp.ommpspring.entities.UserPort;
import org.ommp.ommpspring.repositories.UserPortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPortService implements IUserPortService {

    @Autowired
    private UserPortRepository userPortRepository;

    @Override
    public UserPort saveUserPort(UserPort userPort) {
        return userPortRepository.save(userPort);
    }

    @Override
    public UserPort updateUserPort(UserPort userPort) { return userPortRepository.save(userPort);}

    @Override
    public void deleteUserPort(Long userPortId) {userPortRepository.deleteById(userPortId);}

    @Override
    public Optional<UserPort> getUserPortById(Long userPortId) {
        return userPortRepository.findById(userPortId);
    }

    @Override
    public List<UserPort> getAllUserPorts() {
        return userPortRepository.findAll();
    }
}