package com.waste.wastTracker.controller;

import com.waste.wastTracker.dto.LoginRequest;
import com.waste.wastTracker.dto.LoginResponse;
import com.waste.wastTracker.dto.UserDTO;
import com.waste.wastTracker.security.JWTTokenProvider;
import com.waste.wastTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;
    private final JWTTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(UserService userService, JWTTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate( // create auth token with the provided username abd pass
                new UsernamePasswordAuthenticationToken(                   // if not valid throw exception
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication); // generate the token: contains user info and expiration time

        UserDTO userDetails = userService.getUserByUsername(loginRequest.getUsername()); // prepare the response

        return ResponseEntity.ok(new LoginResponse(jwt, userDetails));
    }

    @GetMapping("/generate-password")
    public String generatePassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode("admin123");
        return password;
    }



}
