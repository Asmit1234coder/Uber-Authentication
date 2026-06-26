package com.example.uberAuthentication.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Booking extends BaseModel{

    @Enumerated(value = EnumType.STRING)
    private BookingStatus status;

    private Date startTime;

    private Date endTime;

    private Long distance;

    @ManyToOne
    private Driver driver;

    @ManyToOne
    private Passenger passenger;


}
