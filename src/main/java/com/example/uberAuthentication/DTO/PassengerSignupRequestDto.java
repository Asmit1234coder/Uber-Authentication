package com.example.uberAuthentication.DTO;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerSignupRequestDto {

    private String name;
    private String email;
    private String password;
    private String phoneNumber;


}
