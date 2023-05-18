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

@Service
public class ServiceUserDestination {

    private final IRepositoryUser user_repository;
    private final IRepositoryDestination destination_repository;

    private final IRepositoryUserDestination user_destination_repository;

    public ServiceUserDestination(IRepositoryUser user_repository, IRepositoryDestination destination_repository, IRepositoryUserDestination user_destination_repository) {
        this.user_repository = user_repository;
        this.destination_repository = destination_repository;
        this.user_destination_repository = user_destination_repository;
    }

    public UserDestination add(UserDestination newUserDestination) {
        newUserDestination = user_destination_repository.save(newUserDestination);
        return newUserDestination;
    }


    public List<Destination> getPrivateDestinations(String userName) {
        List<Destination> privateDestinations = new ArrayList<>();

        User user = user_repository.findByUsername(userName);
        List<UserDestination> userDestinations = user_destination_repository.findAll();
        
        for (UserDestination userDestination : userDestinations) {
            if (userDestination.getUserId() == user.getUserId()) { // User's all destinations
                // For current Destination of intermediate table, find this destination in Destination's table.. access to isPrivate
                privateDestinations.add(userDestination.getDestination());

            }
        }

        return privateDestinations;
    }

}
