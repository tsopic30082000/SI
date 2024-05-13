package hr.foi.si.projekt.controllers;

import hr.foi.si.projekt.dtos.AuthenticationRequest;
import hr.foi.si.projekt.dtos.AuthenticationResponse;

import hr.foi.si.projekt.models.User;
import hr.foi.si.projekt.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/rest/v1/auth")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:8081","http://localhost:8088"})
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        try {
            String token = authService.login(request.getEmail(), request.getPassword());
            return ResponseEntity.ok(new AuthenticationResponse(token));
        } catch (Exception e) {
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

}