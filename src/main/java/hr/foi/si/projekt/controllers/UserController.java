package hr.foi.si.projekt.controllers;

import hr.foi.si.projekt.models.User;
import hr.foi.si.projekt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:8081","http://localhost:8088"})
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("api/rest/v1/users")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }
    @PostMapping("api/rest/v1/users/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User newUser = userService.addUser(user);
        return ResponseEntity.ok(newUser);
    }
}
