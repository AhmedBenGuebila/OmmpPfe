package org.ommp.ommpspring.IService;

import org.ommp.ommpspring.entities.UserPort;
import org.ommp.ommpspring.entities.UserSiege;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IUserSiegeService {
    UserSiege saveUserSiege(UserSiege userSiege);

    UserSiege updateUserSiege(UserSiege userSiege) throws ChangeSetPersister.NotFoundException;

    void deleteUserSiege(Long userSiegeId) throws ChangeSetPersister.NotFoundException;

    Optional<UserSiege> getUserSiegeById(Long userPortId) throws ChangeSetPersister.NotFoundException;

    List<UserSiege> getAllUserSieges();
}
