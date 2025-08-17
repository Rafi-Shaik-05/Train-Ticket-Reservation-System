package org.example.dto;

import lombok.Data;
import org.example.model.User;

@Data
public class UserDTO {
    private Long id;
    private String email;

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
    }
}