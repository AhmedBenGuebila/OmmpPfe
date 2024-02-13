package org.ommp.ommpspring.controllers;

import org.ommp.ommpspring.IService.IUserRegionMaritimeService;
import org.ommp.ommpspring.entities.UserRegionMaritime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-region-maritimes")
public class UserRegionMaritimeController {

    @Autowired
    private IUserRegionMaritimeService userRegionMaritimeService;

    @PostMapping
    public ResponseEntity<UserRegionMaritime> createUserRegionMaritime(@RequestBody UserRegionMaritime userRegionMaritime) {
        UserRegionMaritime newUserRegionMaritime = userRegionMaritimeService.saveUserRegionMaritime(userRegionMaritime);
        return new ResponseEntity<>(newUserRegionMaritime, HttpStatus.CREATED);
    }

    @PutMapping("/{userRegionMaritimeId}")
    public ResponseEntity<UserRegionMaritime> updateUserRegionMaritime(@PathVariable Long userRegionMaritimeId, @RequestBody UserRegionMaritime userRegionMaritime) {
        try {
            userRegionMaritime.setIdUser(userRegionMaritimeId);
            UserRegionMaritime updatedUserRegionMaritime = userRegionMaritimeService.updateUserRegionMaritime(userRegionMaritime);
            return new ResponseEntity<>(updatedUserRegionMaritime, HttpStatus.OK);
        } catch (ChangeSetPersister.NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userRegionMaritimeId}")
    public ResponseEntity<Void> deleteUserRegionMaritime(@PathVariable Long userRegionMaritimeId) {
        try {
            userRegionMaritimeService.deleteUserRegionMaritime(userRegionMaritimeId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ChangeSetPersister.NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userRegionMaritimeId}")
    public ResponseEntity<UserRegionMaritime> getUserRegionMaritimeById(@PathVariable Long userRegionMaritimeId) {
        try {
            UserRegionMaritime userRegionMaritime = userRegionMaritimeService.getUserRegionMaritimeById(userRegionMaritimeId);
            return new ResponseEntity<>(userRegionMaritime, HttpStatus.OK);
        } catch (ChangeSetPersister.NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserRegionMaritime>> getAllUserRegionMaritimes() {
        List<UserRegionMaritime> userRegionMaritimes = userRegionMaritimeService.getAllUserRegionMaritimes();
        return new ResponseEntity<>(userRegionMaritimes, HttpStatus.OK);
    }
}
