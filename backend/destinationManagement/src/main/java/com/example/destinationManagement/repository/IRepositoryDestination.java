package com.example.destinationManagement.repository;

import com.example.destinationManagement.domain.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface IRepositoryDestination extends JpaRepository<Destination, Integer> {
}
