package com.example.destinationManagement.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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

}
