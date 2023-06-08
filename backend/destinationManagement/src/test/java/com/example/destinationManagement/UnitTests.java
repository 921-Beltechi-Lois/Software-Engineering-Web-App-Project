package com.example.destinationManagement;

import com.example.destinationManagement.controller.Controller;
import com.example.destinationManagement.domain.Destination;
import com.example.destinationManagement.domain.User;
import com.example.destinationManagement.repository.IRepositoryDestination;
import com.example.destinationManagement.repository.IRepositoryUser;
import com.example.destinationManagement.repository.IRepositoryUserDestination;
import com.example.destinationManagement.service.ServiceDestination;
import com.example.destinationManagement.service.ServiceUser;
import com.example.destinationManagement.service.ServiceUserDestination;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UnitTests {

    @Mock
    private IRepositoryDestination destinationRepository;

    @Mock
    private IRepositoryUserDestination userDestinationRepository;

    @Mock
    private IRepositoryUser userRepository;

    //private ServiceUser user_service;
    //private ServiceDestination destination_service;
    //private ServiceUserDestination user_destination_service;

    @Test
    public void test_getPublicList(){

        // Create a list of destinations with some private destinations
        List<Destination> destinationList = new ArrayList<>();
        Destination destination1 = new Destination(false, 1, "Destination 1", "Location 1", "Description 1", "image1.jpg");
        Destination destination2 = new Destination(true, 2, "Destination 2", "Location 2", "Description 2", "image2.jpg");
        Destination destination3 = new Destination(false, 3, "Destination 3", "Location 3", "Description 3", "image3.jpg");
        Destination destination4 = new Destination(true, 4, "Destination 4", "Location 4", "Description 4", "image4.jpg");

        // Add destinations to the list
        destinationList.add(destination1);
        destinationList.add(destination2);
        destinationList.add(destination3);
        destinationList.add(destination4);

        // Mock the behavior of the destination repository to return the destination list
        Mockito.when(destinationRepository.findAll()).thenReturn(destinationList);

        // Create an instance of the service
        ServiceDestination serviceDestination = new ServiceDestination(destinationRepository,userRepository,userDestinationRepository);

        // Call the method to get the public list
        List<Destination> publicList = serviceDestination.getPublicList();

        // Assert that the public list only contains public destinations
        for (Destination destination : publicList) {
            Assert.assertFalse(destination.isPrivate());
        }

        // Assert that the public list size is correct
        Assert.assertEquals(2, publicList.size());

    }

    @Test
    public void test_logInUser_validCredentials() {
        // Create a list of users with some matching credentials
        List<User> userList = new ArrayList<>();
        User user1 = new User(false, "username1", "password1", "email1@example.com");
        User user2 = new User(true, "username2", "password2", "email2@example.com");
        User user3 = new User(false, "username3", "password3", "email3@example.com");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        // Set up the mock behavior of the user repository to return the user list
        Mockito.when(userRepository.findAll()).thenReturn(userList);

        ServiceUser serviceUser = new ServiceUser(userRepository,destinationRepository,userDestinationRepository);

        // Call the logInUser method with valid credentials
        boolean result = serviceUser.logInUser("username2", "password2");

        // Assert that the result is true, indicating successful login
        Assert.assertTrue(result);
    }

    @Test
    public void test_logInUser_invalidCredentials() {
        // Create a list of users without matching credentials
        List<User> userList = new ArrayList<>();
        User user1 = new User(false, "username1", "password1", "email1@example.com");
        User user2 = new User(false, "username2", "password2", "email2@example.com");
        User user3 = new User(false, "username3", "password3", "email3@example.com");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        // Set up the mock behavior of the user repository to return the user list
        Mockito.when(userRepository.findAll()).thenReturn(userList);

        ServiceUser serviceUser = new ServiceUser(userRepository,destinationRepository,userDestinationRepository);

        // Call the logInUser method with invalid credentials
        boolean result = serviceUser.logInUser("username4", "password4");

        // Assert that the result is false, indicating unsuccessful login
        Assert.assertFalse(result);
    }

    @Test
    public void test_add() {
        // Create a new destination and a logged-in user
        Destination newDestination = new Destination(true, 1, "New Destination", "Location", "Description", "image.jpg");
        User loggedInUser = new User(false, "username", "password", "email");

        // Set up the mock behavior of the destination repository to return the saved destination
        Mockito.when(destinationRepository.save(Mockito.any(Destination.class))).thenReturn(newDestination);

        ServiceDestination destinationService = new ServiceDestination(destinationRepository,userRepository, userDestinationRepository);

        // Call the add method
        Destination addedDestination = destinationService.add(newDestination, loggedInUser);

        // Verify that the user ID is set correctly
        Assert.assertEquals(loggedInUser.getUserId(), addedDestination.getUserId());

        // Verify that the destination is saved
        Mockito.verify(destinationRepository, Mockito.times(1)).save(Mockito.any(Destination.class));
    }


}
