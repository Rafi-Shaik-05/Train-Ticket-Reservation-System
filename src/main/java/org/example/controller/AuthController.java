package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.model.User;
import org.example.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User newUser = authService.registerUser(user.getEmail(), user.getPassword());
            newUser.setPassword(null);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user, HttpServletRequest request) {
        try {
            User loggedInUser = authService.loginUser(user.getEmail(), user.getPassword());

            // --- Manually create a security session ---
            // Create an authentication token for the user
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    loggedInUser.getEmail(),
                    null,
                    Collections.emptyList() // No specific roles/authorities for now
            );

            // Set the authentication in the security context
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);

            // Create a new session and add the security context to it
            HttpSession session = request.getSession(true);
            session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, securityContext);
            // --- Session creation complete ---

            loggedInUser.setPassword(null);
            return ResponseEntity.ok(loggedInUser);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid email or password.");
        }
    }
}
