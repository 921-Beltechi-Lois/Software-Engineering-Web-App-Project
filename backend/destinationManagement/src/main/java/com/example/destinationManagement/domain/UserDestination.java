package com.example.destinationManagement.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDestination {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer userDestinationId;

    Integer userId;
    Integer destinationId;
    Integer stayDates;

    public UserDestination(Integer userId, Integer destinationId, Integer stayDates) {
        this.userId = userId;
        this.destinationId = destinationId;
        this.stayDates = stayDates;
    }
}
