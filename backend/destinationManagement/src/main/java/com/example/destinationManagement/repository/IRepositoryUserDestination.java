package com.example.destinationManagement.repository;

import com.example.destinationManagement.domain.User;
import com.example.destinationManagement.domain.UserDestination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface IRepositoryUserDestination extends JpaRepository<UserDestination, Integer> {
}