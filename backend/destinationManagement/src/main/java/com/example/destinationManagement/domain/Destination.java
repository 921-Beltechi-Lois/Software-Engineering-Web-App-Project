package com.example.destinationManagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Destination {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="destination_id")
    private Integer destinationId;

    boolean isPrivate;

    Integer userId;

    String title;
    String geo_location;
    String description;
    String image;

    @OneToMany(mappedBy = "destination", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
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
