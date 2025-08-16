package org.example.controller;

import org.example.model.User;
import org.example.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * Endpoint to handle user registration.
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User newUser = authService.registerUser(user.getEmail(), user.getPassword());
            newUser.setPassword(null); // Never return the password
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Endpoint to handle user login.
     * Listens for POST requests to /api/auth/login.
     * @param user A map containing the user's email and password.
     * @return A response entity with the logged-in user's data or an error.
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            User loggedInUser = authService.loginUser(user.getEmail(), user.getPassword());
            loggedInUser.setPassword(null); // Never return the password
            return ResponseEntity.ok(loggedInUser);
        } catch (Exception e) {
            // For security, return a generic "invalid credentials" message
            return ResponseEntity.status(401).body("Invalid email or password.");
        }
    }
}