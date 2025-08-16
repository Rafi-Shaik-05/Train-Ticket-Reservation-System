package org.example.service;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Injects the password encoder

    /**
     * Registers a new user, hashing their password before saving.
     */
    public User registerUser(String email, String password) throws Exception {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new Exception("User with email " + email + " already exists.");
        }

        User newUser = new User();
        newUser.setEmail(email);
        // Hash the password before saving it to the database
        newUser.setPassword(passwordEncoder.encode(password));

        return userRepository.save(newUser);
    }

    /**
     * Authenticates a user.
     * @param email The user's email.
     * @param password The user's plain text password.
     * @return The authenticated User object.
     * @throws Exception if the user is not found or the password does not match.
     */
    public User loginUser(String email, String password) throws Exception {
        // Find the user by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("User not found with email: " + email));

        // Check if the provided password matches the stored hashed password
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new Exception("Invalid password.");
        }

        return user;
    }
}