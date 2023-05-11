package com.example.destinationManagement;

import com.example.destinationManagement.domain.User;
import com.example.destinationManagement.repository.IRepositoryDestination;
import com.example.destinationManagement.repository.IRepositoryUser;
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
    CommandLineRunner initDatabase(IRepositoryUser repository, IRepositoryDestination destinations,IRepositoryDestination userDestinations) {

        return args -> {
            log.info("Preloading " + repository.save(new User(1,true,"catalina","0000","arbacatalina@yahoo.com")));
            log.info("Preloading " + repository.save(new User(1,false,"lois","0000","lois@yahoo.com")));

            // fetch all customers
            log.info("People found with findAll():");
            log.info("-------------------------------");
            for (User person : repository.findAll()) {
                log.info(person.toString());
            }
            log.info("");



        };
    }
}
