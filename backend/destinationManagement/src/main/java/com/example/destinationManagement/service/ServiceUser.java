package com.example.destinationManagement.service;

import com.example.destinationManagement.domain.Destination;
import com.example.destinationManagement.domain.User;
import com.example.destinationManagement.repository.IRepositoryUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceUser {
    private final IRepositoryUser user_repository;

    public ServiceUser(IRepositoryUser user_repository) {
        this.user_repository = user_repository;
    }

    public User add(User newUser) {
        newUser = user_repository.save(newUser);
        return newUser;
    }

    public void remove(Integer user_id) {
        user_repository.deleteById(user_id);
    }

    public User update(User newUser, Integer id) {
        return user_repository.findById(id)
                .map(user -> {
                    user.setUser_name(newUser.getUser_name());
                    user.setPassword(newUser.getPassword());
                    user.setEmail(newUser.getEmail());
                    user.set_admin(newUser.is_admin());  // Daca e admin doar
                    return user_repository.save(user);
                })
                .orElseGet(() -> { // otherwise if not found, ADD IT
                    newUser.setUser_id(id);
                    return user_repository.save(newUser);
                });
    }




    public List<User> getAllUsers() {
        return user_repository.findAll();
    }

    public List<User> getAllAdmins() {
        List<User> admins = new ArrayList<>();
        for (User user : user_repository.findAll()) {
            if (user.is_admin()) {
                admins.add(user);
            }
        }
        return admins;
    }

    public User getUserById(Integer user_id) {
        Optional<User> user = user_repository.findById(user_id);
        return user.orElse(null);
    }

    public User getUser(String userName, String password) {    /// Needed?
        for (User user : user_repository.findAll()) {
            if (user.getUser_name().equals(userName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
