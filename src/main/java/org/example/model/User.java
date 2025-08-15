package org.example.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users") // Specifies the table name in the database
@Data // Lombok annotation to generate getters, setters, toString, etc.
@NoArgsConstructor // Lombok annotation for a no-argument constructor
public class User {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increments the ID
    private Long id;

    @Column(nullable = false, unique = true) // Ensures email is not null and is unique
    private String email;

    @Column(nullable = false)
    private String password;

    // We won't store bookings directly in the User entity to keep it simple.
    // We can query bookings by user ID when needed.
}