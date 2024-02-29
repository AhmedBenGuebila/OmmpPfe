package org.ommp.ommpspring.services;

import org.ommp.ommpspring.IService.IUserAdminService;
import org.ommp.ommpspring.entities.UserAdmin;
import org.ommp.ommpspring.repositories.UserAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAdminService implements IUserAdminService {

    @Autowired
    private UserAdminRepository userAdminRepository;

    @Override
    public UserAdmin saveUserAdmin(UserAdmin userAdmin) {return userAdminRepository.save(userAdmin);}

    @Override
    public UserAdmin updateUserAdmin(UserAdmin userAdmin) {return userAdminRepository.save(userAdmin);}

    @Override
    public void deleteUserAdmin(Long userAdminId){userAdminRepository.deleteById(userAdminId);}

    @Override
    public Optional<UserAdmin> getUserAdminById(Long userAdminId){ return userAdminRepository.findById(userAdminId);}

    @Override
    public List<UserAdmin> getAllUserAdmins() {return userAdminRepository.findAll();}
}
