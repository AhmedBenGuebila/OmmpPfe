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
    public UserPort updateUserPort(UserPort userPort) throws ChangeSetPersister.NotFoundException {
        if (userPortRepository.existsById(userPort.getIdUser())) {
            return userPortRepository.save(userPort);
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public void deleteUserPort(Long userPortId) throws ChangeSetPersister.NotFoundException {
        if (userPortRepository.existsById(userPortId)) {
            userPortRepository.deleteById(userPortId);
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public UserPort getUserPortById(Long userPortId) throws ChangeSetPersister.NotFoundException {
        Optional<UserPort> userPortOptional = userPortRepository.findById(userPortId);
        if (userPortOptional.isPresent()) {
            return userPortOptional.get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public List<UserPort> getAllUserPorts() {
        return userPortRepository.findAll();
    }
}