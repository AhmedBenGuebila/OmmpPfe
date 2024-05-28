package org.ommp.ommpspring.configuration;

import org.ommp.ommpspring.entities.User;
import org.ommp.ommpspring.entities.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/signup")
public class SignupController {

    private final AuthService authService;

    @Autowired
    public SignupController(AuthService authService) {
        this.authService = authService;
    }




    @PostMapping("/user")
    public ResponseEntity<String> signUpUser(@RequestBody SignupRequest signupRequest) {
        boolean isUserCreated = authService.createUser(signupRequest);
        return generateResponse(isUserCreated, "User");
    }

    @PostMapping("/ADMIN")
    public ResponseEntity<String> signUpAdmin(@RequestBody SignupRequest signupRequest) {
        boolean isAdminCreated = authService.createUser(signupRequest);
        return generateResponse(isAdminCreated, "Admin");
    }

    @PostMapping("/PORT")
    public ResponseEntity<String> signUpPortUser(@RequestBody SignupRequest signupRequest) {
        boolean isPortUserCreated = authService.createUser(signupRequest);
        return generateResponse(isPortUserCreated, "Port User");
    }

    @PostMapping("/SIEGE")
    public ResponseEntity<String> signUpSiegeUser(@RequestBody SignupRequest signupRequest) {
        boolean isSiegeUserCreated = authService.createUser(signupRequest);
        return generateResponse(isSiegeUserCreated, "Siege User");
    }

    @PostMapping("/REGION_MARITIME")
    public ResponseEntity<String> signUpRegionMaritimeUser(@RequestBody SignupRequest signupRequest) {
        boolean isRegionMaritimeUserCreated = authService.createUser(signupRequest);
        return generateResponse(isRegionMaritimeUserCreated, "Region Maritime User");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable long id, @RequestBody SignupRequest signupRequest) {
        boolean isUserUpdated = authService.updateUser(signupRequest, id);
        if (isUserUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body("{}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID " + id + " not found");
        }
    }

    private ResponseEntity<String> generateResponse(boolean isUserCreated, String userType) {
        if (isUserCreated) {

            return ResponseEntity.status(HttpStatus.CREATED).body(userType + " created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create " + userType);
        }
    }
}
