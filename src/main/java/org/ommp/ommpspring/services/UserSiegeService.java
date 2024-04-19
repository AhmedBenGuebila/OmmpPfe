package org.ommp.ommpspring.services;

import org.ommp.ommpspring.IService.IUserSiegeService;
import org.ommp.ommpspring.entities.UserSiege;
import org.ommp.ommpspring.repositories.UserSiegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserSiegeService implements IUserSiegeService {

    @Autowired
    private UserSiegeRepository userSiegeRepository;

    @Override
    public UserSiege saveUserSiege(UserSiege userSiege) {
        return userSiegeRepository.save(userSiege);
    }

    @Override
    public UserSiege updateUserSiege(UserSiege userSiege) { return userSiegeRepository.save(userSiege);}

    @Override
    public void deleteUserSiege(Long userSiegeId) {userSiegeRepository.deleteById(userSiegeId);}

    @Override
    public Optional<UserSiege> getUserSiegeById(Long userSiegeId) {
        return userSiegeRepository.findById(userSiegeId);
    }

    @Override
    public List<UserSiege> getAllUserSieges() {
        return userSiegeRepository.findAll();
    }
}