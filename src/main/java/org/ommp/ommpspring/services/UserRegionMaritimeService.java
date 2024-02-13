package org.ommp.ommpspring.services;

import org.ommp.ommpspring.IService.IUserRegionMaritimeService;
import org.ommp.ommpspring.entities.UserRegionMaritime;
import org.ommp.ommpspring.repositories.UserRegionMaritimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRegionMaritimeService implements IUserRegionMaritimeService {

    @Autowired
    private UserRegionMaritimeRepository userRegionMaritimeRepository;

    @Override
    public UserRegionMaritime saveUserRegionMaritime(UserRegionMaritime userRegionMaritime) {
        return userRegionMaritimeRepository.save(userRegionMaritime);
    }

    @Override
    public UserRegionMaritime updateUserRegionMaritime(UserRegionMaritime userRegionMaritime) throws ChangeSetPersister.NotFoundException {
        if (userRegionMaritimeRepository.existsById(userRegionMaritime.getIdUser())) {
            return userRegionMaritimeRepository.save(userRegionMaritime);
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public void deleteUserRegionMaritime(Long userRegionMaritimeId) throws ChangeSetPersister.NotFoundException {
        if (userRegionMaritimeRepository.existsById(userRegionMaritimeId)) {
            userRegionMaritimeRepository.deleteById(userRegionMaritimeId);
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public UserRegionMaritime getUserRegionMaritimeById(Long userRegionMaritimeId) throws ChangeSetPersister.NotFoundException {
        Optional<UserRegionMaritime> userRegionMaritimeOptional = userRegionMaritimeRepository.findById(userRegionMaritimeId);
        if (userRegionMaritimeOptional.isPresent()) {
            return userRegionMaritimeOptional.get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public List<UserRegionMaritime> getAllUserRegionMaritimes() {
        return userRegionMaritimeRepository.findAll();
    }
}