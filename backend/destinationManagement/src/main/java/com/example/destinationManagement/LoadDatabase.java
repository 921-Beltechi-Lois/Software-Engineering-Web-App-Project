package com.example.destinationManagement;

import com.example.destinationManagement.domain.Destination;
import com.example.destinationManagement.domain.User;
import com.example.destinationManagement.domain.UserDestination;
import com.example.destinationManagement.repository.IRepositoryDestination;
import com.example.destinationManagement.repository.IRepositoryUser;
import com.example.destinationManagement.repository.IRepositoryUserDestination;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
class LoadDatabase {


    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);


    @Bean
    CommandLineRunner initDatabase(IRepositoryUser user_repository, IRepositoryDestination destination_repository, IRepositoryUserDestination user_destination_repository) {

        return args -> {
            log.info("Preloading " + user_repository.save(new User(true,"catalina","0000","arbacatalina@yahoo.com")));
            log.info("Preloading " + user_repository.save(new User(false,"lois","0000","lois@yahoo.com")));

            log.info("Preloading "+destination_repository.save(new Destination(true,1, "Viseul de Sus","10.22.78","Frumos","https://img.directbooking.ro/getimage.ashx?f=statiuni&file=Statiune_45a6e98f-02b4-4c3f-b55d-9317d2cea6ca.jpg")));
            log.info("Preloading "+destination_repository.save(new Destination(false,1, "Maldive","10.25.78","Scump","https://www.aerocenter.ro/LocationFileHandler/1200/1200/maldive-kuramathi-2134.jpg")));


            log.info("Preloading"+ user_destination_repository.save(new UserDestination(1,1,3)));
            log.info("Preloading"+ user_destination_repository.save(new UserDestination(1,2,4)));


            // fetch all users
            log.info("Users found with findAll():");
            log.info("-------------------------------");
            for (User user : user_repository.findAll()) {
                log.info(user.toString());
            }
            log.info("");

            // fetch all destinations
            log.info("Destinations found with findAll():");
            log.info("-------------------------------");
            for (Destination destination : destination_repository.findAll()) {
                log.info(destination.toString());
            }
            log.info("");

            // fetch all UserDestinations
            log.info("UserDestinations found with findAll():");
            log.info("-------------------------------");
            for (UserDestination user_destination : user_destination_repository.findAll()) {
                log.info(user_destination.toString());
            }
            log.info("");



        };
    }
}
