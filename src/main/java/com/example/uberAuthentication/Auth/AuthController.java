package com.example.uberAuthentication.Auth;


import com.example.uberAuthentication.DTO.PassengerDto;
import com.example.uberAuthentication.DTO.PassengerSignupRequestDto;
import com.example.uberAuthentication.Service.AuthService;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup/passenger")
    public ResponseEntity<PassengerDto> signUp(@RequestBody PassengerSignupRequestDto passengerSignupRequestDto){
        PassengerDto response=authService.signupPassenger(passengerSignupRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/signin/passenger")
    public ResponseEntity<?> signIn(){
        return new ResponseEntity<>(10,HttpStatus.CREATED);
    }

}
