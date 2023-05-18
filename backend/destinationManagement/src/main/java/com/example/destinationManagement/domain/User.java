package com.example.destinationManagement.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer userId;

    boolean isAdmin;
    String username;
    String  password;
    String email;

    public User(boolean isAdmin, String username, String password, String email) {
        this.isAdmin = isAdmin;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}

