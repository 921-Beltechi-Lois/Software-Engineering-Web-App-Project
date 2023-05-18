package com.example.destinationManagement.controller;

import com.example.destinationManagement.domain.Destination;
import com.example.destinationManagement.domain.User;
import com.example.destinationManagement.domain.UserDestination;
import com.example.destinationManagement.service.ServiceDestination;
import com.example.destinationManagement.service.ServiceUser;
import com.example.destinationManagement.service.ServiceUserDestination;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    ServiceUser serviceUser;
    ServiceDestination serviceDestination;

    ServiceUserDestination serviceUserDestination;


    public Controller(ServiceUser serviceUser, ServiceDestination serviceDestination,ServiceUserDestination serviceUserDestination) {
        this.serviceUser = serviceUser;
        this.serviceDestination = serviceDestination;
        this.serviceUserDestination = serviceUserDestination;
    }

    @GetMapping("/api/login/{username}/{password}")
    boolean logIn(@PathVariable String username, @PathVariable String password){
        return serviceUser.logInUser(username,password);

    }



    // getAllUserDestinations from UserDestination
    @GetMapping("/api/userdestinations/{username}")
    List<Destination> getAllUserDestinations(@PathVariable String username){
        return serviceUserDestination.getAllUserDestinations(username);

    }

    @PostMapping("/api/user")
    User addUser(@RequestBody User newUser){
        return serviceUser.add(newUser);

    }

    @PostMapping("/api/destination/{staydates}")
    Destination addDestination(@RequestBody Destination newDestination,@PathVariable Integer staydates){
        User loggedInUser =serviceUser.loggedInUser;
        Destination addedDestination = serviceDestination.add(newDestination,loggedInUser);

        UserDestination userdestination = new UserDestination(loggedInUser.getUserId(),addedDestination.getDestinationId(),staydates);
        serviceUserDestination.add(userdestination);

        return addedDestination;
    }

    @GetMapping("/api/destinations")
    List<Destination> getAllDestinations(){
        return serviceDestination.getAllDestinations();
    }


//    @PostMapping("/api/userdestination")
//    UserDestination addUserDestination(@RequestBody UserDestination newUserDestination){
//        return serviceUserDestination.add(newUserDestination);
//
//    }


}
