package org.ommp.ommpspring.IService;

import org.ommp.ommpspring.entities.UserPort;
import org.ommp.ommpspring.entities.UserRegionMaritime;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserRegionMaritimeService {
    UserRegionMaritime saveUserRegionMaritime(UserRegionMaritime userRegionMaritime);

    UserRegionMaritime updateUserRegionMaritime(UserRegionMaritime userRegionMaritime) throws ChangeSetPersister.NotFoundException;

    void deleteUserRegionMaritime(Long userRegionMaritimeId) throws ChangeSetPersister.NotFoundException;

    UserRegionMaritime getUserRegionMaritimeById(Long userRegionMaritimeId) throws ChangeSetPersister.NotFoundException;

    List<UserRegionMaritime> getAllUserRegionMaritimes();
}
