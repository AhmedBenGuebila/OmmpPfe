package org.ommp.ommpspring.controllers;

import org.ommp.ommpspring.IService.IUserSiegeService;
import org.ommp.ommpspring.entities.UserSiege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/api/user-sieges")
public class UserSiegeController {

    @Autowired
    private IUserSiegeService userSiegeService;

    @PostMapping
    public ResponseEntity<UserSiege> createUserSiege(@RequestBody UserSiege userSiege) {
        UserSiege newUserSiege = userSiegeService.saveUserSiege(userSiege);
        return new ResponseEntity<>(newUserSiege, HttpStatus.CREATED);
    }

    @PutMapping("/{userSiegeId}")
    public ResponseEntity<UserSiege> updateUserSiege(@PathVariable Long userSiegeId, @RequestBody UserSiege userSiege) {
        try {
            userSiege.setIdUser(userSiegeId);
            UserSiege updatedUserSiege = userSiegeService.updateUserSiege(userSiege);
            return new ResponseEntity<>(updatedUserSiege, HttpStatus.OK);
        } catch (ChangeSetPersister.NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userSiegeId}")
    public ResponseEntity<Void> deleteUserSiege(@PathVariable Long userSiegeId) {
        try {
            userSiegeService.deleteUserSiege(userSiegeId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ChangeSetPersister.NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userSiegeId}")
    public ResponseEntity<UserSiege> getUserSiegeById(@PathVariable Long userSiegeId) {
        try {
            Optional<UserSiege> userSiegeOptional = userSiegeService.getUserSiegeById(userSiegeId);
            UserSiege userSiege =userSiegeOptional.get();
            return new ResponseEntity<>(userSiege, HttpStatus.OK);
        } catch (ChangeSetPersister.NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserSiege>> getAllUserSieges() {
        List<UserSiege> userSieges = userSiegeService.getAllUserSieges();
        return new ResponseEntity<>(userSieges, HttpStatus.OK);
    }
}
