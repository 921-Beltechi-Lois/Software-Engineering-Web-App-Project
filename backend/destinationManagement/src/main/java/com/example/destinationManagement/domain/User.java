package com.example.destinationManagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer user_id;

    boolean is_admin;
    String user_name;
    String  password;
    String email;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Destination> addedToPrivateList;  //private

    //List<Destination> addedFromPublicList;
    //@Transient
    @ElementCollection
    List<String> addedFromPublicList = new ArrayList<>(); // Or you can mark it as @Transient if it doesn't exist on DB table.


    int stay_dates;

}
