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
    public UserAdmin saveUserAdmin(UserAdmin userAdmin) {
        return userAdminRepository.save(userAdmin);
    }

    @Override
    public UserAdmin updateUserAdmin(UserAdmin userAdmin) throws ChangeSetPersister.NotFoundException {
        if (userAdminRepository.existsById(userAdmin.getIdUser())) {
            return userAdminRepository.save(userAdmin);
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public void deleteUserAdmin(Long userAdminId) throws ChangeSetPersister.NotFoundException {
        if (userAdminRepository.existsById(userAdminId)) {
            userAdminRepository.deleteById(userAdminId);
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public UserAdmin getUserAdminById(Long userAdminId) throws ChangeSetPersister.NotFoundException {
        Optional<UserAdmin> userAdminOptional = userAdminRepository.findById(userAdminId);
        if (userAdminOptional.isPresent()) {
            return userAdminOptional.get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public List<UserAdmin> getAllUserAdmins() {
        return userAdminRepository.findAll();
    }
}
