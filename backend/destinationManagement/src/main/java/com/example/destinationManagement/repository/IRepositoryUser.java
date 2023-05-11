package com.example.destinationManagement.repository;


import com.example.destinationManagement.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface IRepositoryUser extends JpaRepository<User, Integer> {
}
