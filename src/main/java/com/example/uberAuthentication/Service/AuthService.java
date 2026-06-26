package com.example.uberAuthentication.Service;


import com.example.uberAuthentication.DTO.PassengerDto;
import com.example.uberAuthentication.DTO.PassengerSignupRequestDto;
import com.example.uberAuthentication.Repository.PassengerRepository;
import com.example.uberAuthentication.models.Passenger;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PassengerRepository passengerRepository;

    public AuthService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public PassengerDto signupPassenger(PassengerSignupRequestDto passengerSignupRequestDto){
        Passenger p=Passenger.builder()
                .email(passengerSignupRequestDto.getEmail())
                .name(passengerSignupRequestDto.getName())
                .password(passengerSignupRequestDto.getPassword())
                .phoneNumber(passengerSignupRequestDto.getPhoneNumber())
                .build();
        Passenger newPassenger=passengerRepository.save(p);
        return PassengerDto.from((newPassenger));

    }
}
