package org.ommp.ommpspring.controllers;

import org.ommp.ommpspring.IService.IUserAdminService;
import org.ommp.ommpspring.entities.UserAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-admins")
public class UserAdminController {

    @Autowired
    private IUserAdminService userAdminService;

    @PostMapping
    public ResponseEntity<UserAdmin> createUserAdmin(@RequestBody UserAdmin userAdmin) {
        UserAdmin newUserAdmin = userAdminService.saveUserAdmin(userAdmin);
        return new ResponseEntity<>(newUserAdmin, HttpStatus.CREATED);
    }

    @PutMapping("/{userAdminId}")
    public ResponseEntity<UserAdmin> updateUserAdmin(@PathVariable Long userAdminId, @RequestBody UserAdmin userAdmin) {
        try {
            userAdmin.setIdUser(userAdminId);
            UserAdmin updatedUserAdmin = userAdminService.updateUserAdmin(userAdmin);
            return new ResponseEntity<>(updatedUserAdmin, HttpStatus.OK);
        } catch (ChangeSetPersister.NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userAdminId}")
    public ResponseEntity<Void> deleteUserAdmin(@PathVariable Long userAdminId) {
        try {
            userAdminService.deleteUserAdmin(userAdminId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ChangeSetPersister.NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userAdminId}")
    public ResponseEntity<UserAdmin> getUserAdminById(@PathVariable Long userAdminId) {
        try {
            UserAdmin userAdmin = userAdminService.getUserAdminById(userAdminId);
            return new ResponseEntity<>(userAdmin, HttpStatus.OK);
        } catch (ChangeSetPersister.NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserAdmin>> getAllUserAdmins() {
        List<UserAdmin> userAdmins = userAdminService.getAllUserAdmins();
        return new ResponseEntity<>(userAdmins, HttpStatus.OK);
    }
}
