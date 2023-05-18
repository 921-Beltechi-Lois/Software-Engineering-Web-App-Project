package com.example.destinationManagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_id")
    private Integer userId;

    boolean isAdmin;
    String username;
    String  password;
    String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    List<UserDestination> userDestinations;

    public User(boolean isAdmin, String username, String password, String email) {
        this.isAdmin = isAdmin;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}

