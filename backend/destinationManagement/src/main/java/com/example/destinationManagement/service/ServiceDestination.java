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
public class ServiceDestination {

    private final IRepositoryDestination destination_repository;
    private final IRepositoryUser user_repository;
    private final IRepositoryUserDestination user_destination_repository;



    public ServiceDestination(IRepositoryDestination destination_repository, IRepositoryUser user_repository, IRepositoryUserDestination user_destination_repository) {
        this.destination_repository = destination_repository;
        this.user_repository = user_repository;
        this.user_destination_repository = user_destination_repository;
    }


    public Destination add(Destination newDestination, User loggedInUser) {
        newDestination.setUserId(loggedInUser.getUserId());
        return destination_repository.save(newDestination);
    }



//
//    public void remove(int destination_id) {
//        destination_repository.deleteById(destination_id);
//    }
//
//
//    public Destination update(Destination newDestination, Integer id) {
//        return destination_repository.findById(id)
//                .map(destination -> {
//                    destination.setImage(newDestination.getImage());
//                    destination.setDescription(newDestination.getDescription());
//                    destination.setGeo_location(newDestination.getGeo_location());
//                    destination.setTitle(newDestination.getTitle());
//                    //destination.setPrivate(newDestination.get);
//                    destination.setAdded_by_user(newDestination.getAdded_by_user());
//                    return destination_repository.save(destination);
//                })
//                .orElseGet(() -> { // otherwise if not found, ADD IT
//                    newDestination.setDestination_id(id);
//                    return destination_repository.save(newDestination);
//                });
//    }
//
//
//    public List<Destination> getAllDestinations() {
//        return destination_repository.findAll();
//    }
//
//    public Destination getDestinationById(int destination_id) {
//        return destination_repository.findById(destination_id).orElse(null);
//    }

    // Doar pt user_id dat?
//    public List<Destination> getPrivateDestinations() {
//
//        return destination_repository.findAll().stream()
//                .filter(Destination::isPrivate)
//                .collect(Collectors.toList());
//
//    }

//    public List<Destination> getPrivateDestinationsForUser(Integer user_id) {
//        User user = user_repository.findById(user_id).orElse(null);
//        if (user == null) {
//            return Collections.emptyList(); // return an empty list if the user is not found
//        }
//        List<Destination> privateDestinations = new ArrayList<>();
//        for (Destination destination : user.getDestinations()) {
//            if (destination.isPrivate()) {
//                privateDestinations.add(destination);
//            }
//        }
//        return privateDestinations;
//    }


//    public List<Destination> getPrivateDestinationsByUserId(User user) {
//        List<Destination> privateDestinations = new ArrayList<>();
//
//        // Add private destinations from addedToPrivateList
//        for (Destination destination : user.getAddedToPrivateList()) {
//            if (!privateDestinations.contains(destination)) {
//                privateDestinations.add(destination);
//            }
//        }
//
//        // Add private destinations from addedFromPublicList
//        for (String destinationId : user.getAddedFromPublicList()) {
//            Optional<Destination> optionalDestination = destination_repository.findById(Integer.parseInt(destinationId));
//            if (optionalDestination.isPresent()) {
//                Destination destination = optionalDestination.get();
//                if (destination.isPrivate() && !privateDestinations.contains(destination)) { // Checks for duplicate
//                    privateDestinations.add(destination);
//                }
//            }
//        }
//
//        return privateDestinations;
//    }
//
//
//    public List<Destination> getPublicDestinations() {
//        return destination_repository.findAll()
//                .stream()
//                .filter(destination -> !destination.isPrivate())
//                .collect(Collectors.toList());
//
//    }
}
