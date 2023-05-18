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

    //Log in function
    @GetMapping("/api/login/{username}/{password}")
    boolean logIn(@PathVariable String username, @PathVariable String password){
        return serviceUser.logInUser(username,password);

    }



    // Show private list
    @GetMapping("/api/userdestinations/{username}")
    List<Destination> getPrivateList(@PathVariable String username){
        System.out.println(username);
        /// !!!!!!!!!!
        return serviceUserDestination.getPrivateDestinations(username);

    }

    //Add destination: private or public destination
    @PostMapping("/api/destination/{staydates}")
    Destination addDestination(@RequestBody Destination newDestination,@PathVariable Integer staydates){
        User loggedInUser =serviceUser.loggedInUser;
        Destination addedDestination = serviceDestination.add(newDestination,loggedInUser);

        if(staydates!=-1) { // User wants to add it to its private list by giving > 0 StayDates
            UserDestination userdestination = new UserDestination(staydates, loggedInUser, addedDestination);
            serviceUserDestination.add(userdestination);
        }

        return addedDestination;
    }

    //Show public list
    @GetMapping("/api/destinations")
    List<Destination> getPublicList(){

        return serviceDestination.getPublicList();
    }

    @GetMapping("/api/isAdmin")
    boolean isAdmin(){
        return serviceUser.isAdmin();
    }


//    @PostMapping("/api/userdestination")
//    UserDestination addUserDestination(@RequestBody UserDestination newUserDestination){
//        return serviceUserDestination.add(newUserDestination);
//
//    }


}
