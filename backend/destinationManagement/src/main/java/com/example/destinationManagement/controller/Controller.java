package com.example.destinationManagement.controller;

import com.example.destinationManagement.service.ServiceDestination;
import com.example.destinationManagement.service.ServiceUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    ServiceUser serviceUser;
    ServiceDestination serviceDestination;

    @GetMapping("/api/login/{username}/{password}")
    boolean logIn(@PathVariable String username, @PathVariable String password){
        return serviceUser.logInUser(username,password);

    }

}
