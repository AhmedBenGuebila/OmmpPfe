package org.ommp.ommpspring.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.ommp.ommpspring.IService.IUserRegionMaritimeService;
import org.ommp.ommpspring.entities.Site;
import org.ommp.ommpspring.entities.UserRegionMaritime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
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
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userRegionMaritimeId}")
    public ResponseEntity<Void> deleteUserRegionMaritime(@PathVariable Long userRegionMaritimeId) {
        try {
            userRegionMaritimeService.deleteUserRegionMaritime(userRegionMaritimeId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userRegionMaritimeId}")
    public ResponseEntity<UserRegionMaritime> getUserRegionMaritimeById(@PathVariable Long userRegionMaritimeId) {
        Optional<UserRegionMaritime> userRegionMaritimeOptional = userRegionMaritimeService.getUserRegionMaritimeById(userRegionMaritimeId);
        return userRegionMaritimeOptional.map(userRegionMaritime -> new ResponseEntity<>(userRegionMaritime, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<UserRegionMaritime>> getAllUserRegionMaritimes() {
        List<UserRegionMaritime> userRegionMaritimes = userRegionMaritimeService.getAllUserRegionMaritimes();
        return new ResponseEntity<>(userRegionMaritimes, HttpStatus.OK);
    }

    @PostMapping("/cree-site-affecter")
    public ResponseEntity<String> createSiteAndAssignToUserRegionMaritime(@RequestBody Map<String, String> requestBody) {
        String nom = requestBody.get("nom");
        String libelleFR = requestBody.get("libelleFR");
        Long userId = Long.parseLong(requestBody.get("id"));

        try {
            userRegionMaritimeService.createSiteAndAssignToUserRegionMaritime(nom, libelleFR, userId);
            return new ResponseEntity<>("Site créé et affecté à l'utilisateur de la région maritime avec succès.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
