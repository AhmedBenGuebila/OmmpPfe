package org.ommp.ommpspring.IService;

import org.ommp.ommpspring.entities.Site;
import org.ommp.ommpspring.entities.UserPort;
import org.ommp.ommpspring.entities.UserRegionMaritime;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IUserRegionMaritimeService {
    UserRegionMaritime saveUserRegionMaritime(UserRegionMaritime userRegionMaritime);

    UserRegionMaritime updateUserRegionMaritime(UserRegionMaritime userRegionMaritime);

    void deleteUserRegionMaritime(Long userRegionMaritimeId);

    Optional<UserRegionMaritime> getUserRegionMaritimeById(Long userRegionMaritimeId);

    List<UserRegionMaritime> getAllUserRegionMaritimes();

    void createSiteAndAssignToUserRegionMaritime(String nom, String libelleFR, Long userId);
}
