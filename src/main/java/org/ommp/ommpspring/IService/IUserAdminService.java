package org.ommp.ommpspring.IService;

import org.ommp.ommpspring.entities.UserAdmin;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public interface IUserAdminService {
    UserAdmin saveUserAdmin(UserAdmin userAdmin);

    UserAdmin updateUserAdmin(UserAdmin userAdmin) ;

    void deleteUserAdmin(Long userAdminId) ;

    Optional<UserAdmin> getUserAdminById(Long userAdminId) ;

    List<UserAdmin> getAllUserAdmins();
}
