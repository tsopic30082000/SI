package hr.foi.si.projekt.controllers;

import hr.foi.si.projekt.dtos.AuthenticationRequest;
import hr.foi.si.projekt.dtos.AuthenticationResponse;

import hr.foi.si.projekt.models.User;
import hr.foi.si.projekt.repositories.UserRepository;
import hr.foi.si.projekt.services.AuthService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/rest/v1/auth")
@CrossOrigin(origins = {"http://localhost:5173", "https://localhost:8081","http://localhost:8088"})
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    @Transactional  // Da bi se osiguralo da se promjene pohranjuju
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        if (user.getBlocked()) {
            return ResponseEntity.badRequest().body("Account is blocked. Contact support.");
        }

        try {
            String token = authService.login(request.getEmail(), request.getPassword());
            return ResponseEntity.ok(new AuthenticationResponse(token));
        } catch (Exception e) {
            int attempts = user.getNumberOfAttempts() + 1;
            user.setNumberOfAttempts(attempts);
            if (attempts >= 3) {
                user.setBlocked(true);
            }
            userRepository.save(user);
            return ResponseEntity.badRequest().body("Login failed: " + e.getMessage());
        }
    }
    @PostMapping("/login2")
    public ResponseEntity<?> login2(@RequestBody AuthenticationRequest request) {
        try {

            List<User> users = authService.login2(request.getEmail(), request.getPassword());
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Login failed: " + e.getMessage());
        }
    }
    @PostMapping("/login3")
    public ResponseEntity<?> login3(@RequestBody AuthenticationRequest request) {
        try {

            List<User> users = authService.login3(request.getEmail(), request.getPassword());
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Login failed: " + e.getMessage());
        }
    }

}