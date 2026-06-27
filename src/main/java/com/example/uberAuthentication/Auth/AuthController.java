package com.example.uberAuthentication.Auth;


import com.example.uberAuthentication.DTO.AuthRequestDto;
import com.example.uberAuthentication.DTO.PassengerDto;
import com.example.uberAuthentication.DTO.PassengerSignupRequestDto;
import com.example.uberAuthentication.Service.AuthService;
import com.example.uberAuthentication.Service.JWTService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.apache.catalina.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final JWTService jwtService;
    private AuthService authService;
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthService authService, JWTService jwtService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.jwtService=jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signup/passenger")
    public ResponseEntity<PassengerDto> signUp(@RequestBody PassengerSignupRequestDto passengerSignupRequestDto){
        PassengerDto response=authService.signupPassenger(passengerSignupRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/signin/passenger")
    public ResponseEntity<?> signIn(@RequestBody AuthRequestDto authRequestDto, HttpServletResponse response){
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(),authRequestDto.getPassword()))
        if(authentication.isAuthenticated()){
            String jwtToken=jwtService.createToken(authRequestDto.getEmail());
            ResponseCookie cookie = ResponseCookie.from("JwtToken", jwtToken)
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(7*24*3600)
                    .build();

            response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());

            return new ResponseEntity<>("Success",HttpStatus.OK);
        }
        else{
            throw new UsernameNotFoundException("user Not Found");
        }
        return new ResponseEntity<>(10,HttpStatus.CREATED);
    }

}
