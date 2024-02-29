package org.ommp.ommpspring.services;

import org.ommp.ommpspring.IService.IUserRegionMaritimeService;
import org.ommp.ommpspring.entities.Site;
import org.ommp.ommpspring.entities.UserRegionMaritime;
import org.ommp.ommpspring.repositories.SiteRepository;
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
    @Autowired
    private SiteRepository siteRepository;
    @Override
    public UserRegionMaritime saveUserRegionMaritime(UserRegionMaritime userRegionMaritime) {return userRegionMaritimeRepository.save(userRegionMaritime);}

    @Override
    public UserRegionMaritime updateUserRegionMaritime(UserRegionMaritime userRegionMaritime) {return  userRegionMaritimeRepository.save(userRegionMaritime);}

    @Override
    public void deleteUserRegionMaritime(Long userRegionMaritimeId){userRegionMaritimeRepository.deleteById(userRegionMaritimeId);}

    @Override
    public Optional<UserRegionMaritime> getUserRegionMaritimeById(Long userRegionMaritimeId) {return userRegionMaritimeRepository.findById(userRegionMaritimeId);}

    @Override
    public List<UserRegionMaritime> getAllUserRegionMaritimes() { return userRegionMaritimeRepository.findAll();}

    public void createSiteAndAssignToUserRegionMaritime(String nom, String libelleFR, Long userId) {
        Optional<UserRegionMaritime> userRegionMaritimeOptional = userRegionMaritimeRepository.findById(userId);

        if (userRegionMaritimeOptional.isPresent()) {
            UserRegionMaritime userRegionMaritime = userRegionMaritimeOptional.get();

            if (userRegionMaritime.getUserRegionMaritimeType() == UserRegionMaritime.UserRegionMaritimeType.CHEF_SOUS_QUARTIER_MARITIME ||
                    userRegionMaritime.getUserRegionMaritimeType() == UserRegionMaritime.UserRegionMaritimeType.CHEF_QUARTIER_MARITIME) {

                Site site = new Site();
                site.setNom(nom);
                site.setLibelleFR(libelleFR);
                site.setUserRegionMaritime(userRegionMaritime);

                siteRepository.save(site);
            } else {
                throw new RuntimeException("L'utilisateur n'est pas autorisé à affecter un site.");
            }
        } else {
            throw new RuntimeException("L'utilisateur de la région maritime n'existe pas avec l'ID spécifié.");
        }
    }
}