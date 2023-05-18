package com.example.destinationManagement.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Destination {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer destinationId;

    boolean isPrivate;

    Integer userId;

    String title;
    String geo_location;
    String description;
    String image;

    @OneToMany(mappedBy = "destination", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<UserDestination> userDestinations;

    public Destination(boolean isPrivate, Integer userId, String title, String geo_location, String description, String image) {
        this.isPrivate = isPrivate;
        this.userId = userId;
        this.title = title;
        this.geo_location = geo_location;
        this.description = description;
        this.image = image;
    }
}
