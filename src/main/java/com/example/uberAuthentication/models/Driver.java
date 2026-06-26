package com.example.uberAuthentication.models;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Driver extends BaseModel{
    private String name;

    @OneToMany(mappedBy = "driver")
    private List<Booking> bookings=new ArrayList<>();



}

