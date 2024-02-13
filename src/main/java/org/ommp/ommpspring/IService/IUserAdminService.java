package org.ommp.ommpspring.IService;

import org.ommp.ommpspring.entities.UserAdmin;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface IUserAdminService {
    UserAdmin saveUserAdmin(UserAdmin userAdmin);

    UserAdmin updateUserAdmin(UserAdmin userAdmin) throws ChangeSetPersister.NotFoundException;

    void deleteUserAdmin(Long userAdminId) throws ChangeSetPersister.NotFoundException;

    UserAdmin getUserAdminById(Long userAdminId) throws ChangeSetPersister.NotFoundException;

    List<UserAdmin> getAllUserAdmins();
}
