package com.example.destinationManagement.service;

import com.example.destinationManagement.domain.Destination;
import com.example.destinationManagement.domain.User;
import com.example.destinationManagement.domain.UserDestination;
import com.example.destinationManagement.repository.IRepositoryDestination;
import com.example.destinationManagement.repository.IRepositoryUser;
import com.example.destinationManagement.repository.IRepositoryUserDestination;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceUser {
    private final IRepositoryUser user_repository;
    private final IRepositoryDestination destination_repository;

    private final IRepositoryUserDestination user_destination_repository;

    public User loggedInUser;

    public ServiceUser(IRepositoryUser user_repository, IRepositoryDestination destination_repository, IRepositoryUserDestination user_destination_repository) {
        this.user_repository = user_repository;
        this.destination_repository = destination_repository;
        this.user_destination_repository = user_destination_repository;
    }
    public boolean logInUser(String userName, String password){
        List<User> usersList = user_repository.findAll();
        Optional<User> userFound = usersList.stream()
                .filter(user -> user.getUsername().equals(userName) && user.getPassword().equals(password))
                .findFirst();
        if(userFound.isPresent()){
            loggedInUser = userFound.get();
            System.out.println("LOGGED IN");
            return true;
        }
        System.out.println("Unknown account");

        return false;

    }



    public User add(User newUser) {
        newUser = user_repository.save(newUser);
        return newUser;
    }





    public Destination addDestination(Destination destination, Integer stayDates){
        destination.setUserId(loggedInUser.getUserId());
        if(!loggedInUser.isAdmin() && !destination.isPrivate()){ // user, public list
            throw new RuntimeException("User cannot add in public list!");
        }

        Destination newDestination = destination_repository.save(destination);

        UserDestination newUserDestination = new UserDestination(stayDates,loggedInUser,newDestination);

        user_destination_repository.save(newUserDestination);


        return newDestination;

    }





//    public void remove(Integer user_id) {
//        user_repository.deleteById(user_id);
//    }


//    public User update(User newUser, Integer id) {
//        return user_repository.findById(id)
//                .map(user -> {
//                    user.setUserName(newUser.getUser_name());
//                    user.setPassword(newUser.getPassword());
//                    user.setEmail(newUser.getEmail());
//                    user.set_admin(newUser.is_admin());  // Daca e admin doar
//                    return user_repository.save(user);
//                })
//                .orElseGet(() -> { // otherwise if not found, ADD IT
//                    newUser.setUser_id(id);
//                    return user_repository.save(newUser);
//                });
//    }


//
//
//    public List<User> getAllUsers() {
//        return user_repository.findAll();
//    }
//
//    public List<User> getAllAdmins() {
//        List<User> admins = new ArrayList<>();
//        for (User user : user_repository.findAll()) {
//            if (user.is_admin()) {
//                admins.add(user);
//            }
//        }
//        return admins;
//    }
//
//    public User getUserById(Integer user_id) {
//        Optional<User> user = user_repository.findById(user_id);
//        return user.orElse(null);
//    }
//
//    public User getUser(String userName, String password) {    /// Needed?
//        for (User user : user_repository.findAll()) {
//            if (user.getUsername().equals(userName) && user.getPassword().equals(password)) {
//                return user;
//            }
//        }
//        return null;
//    }
}
