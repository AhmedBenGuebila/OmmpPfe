package org.ommp.ommpspring.controllers;

import org.ommp.ommpspring.IService.IUserPortService;
import org.ommp.ommpspring.entities.UserPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-ports")
public class UserPortController {

    @Autowired
    private IUserPortService userPortService;

    @PostMapping
    public ResponseEntity<UserPort> createUserPort(@RequestBody UserPort userPort) {
        UserPort newUserPort = userPortService.saveUserPort(userPort);
        return new ResponseEntity<>(newUserPort, HttpStatus.CREATED);
    }

    @PutMapping("/{userPortId}")
    public ResponseEntity<UserPort> updateUserPort(@PathVariable Long userPortId, @RequestBody UserPort userPort) {
        try {
            userPort.setIdUser(userPortId);
            UserPort updatedUserPort = userPortService.updateUserPort(userPort);
            return new ResponseEntity<>(updatedUserPort, HttpStatus.OK);
        } catch (ChangeSetPersister.NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userPortId}")
    public ResponseEntity<Void> deleteUserPort(@PathVariable Long userPortId) {
        try {
            userPortService.deleteUserPort(userPortId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ChangeSetPersister.NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userPortId}")
    public ResponseEntity<UserPort> getUserPortById(@PathVariable Long userPortId) {
        try {
            UserPort userPort = userPortService.getUserPortById(userPortId);
            return new ResponseEntity<>(userPort, HttpStatus.OK);
        } catch (ChangeSetPersister.NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserPort>> getAllUserPorts() {
        List<UserPort> userPorts = userPortService.getAllUserPorts();
        return new ResponseEntity<>(userPorts, HttpStatus.OK);
    }
}
