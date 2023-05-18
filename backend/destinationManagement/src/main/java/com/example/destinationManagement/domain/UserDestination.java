package com.example.destinationManagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

    Integer stayDates;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id")
    @JsonIgnore
    Destination destination;

    public UserDestination(Integer stayDates, User user, Destination destination) {
        this.stayDates = stayDates;
        this.user = user;
        this.destination = destination;
    }
}
