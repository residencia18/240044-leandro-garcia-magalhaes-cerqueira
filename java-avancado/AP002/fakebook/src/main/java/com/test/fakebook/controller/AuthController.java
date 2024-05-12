package com.test.fakebook.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.fakebook.dto.LoginRequest;
import com.test.fakebook.dto.LoginResponse;
import com.test.fakebook.dto.RegisterRequest;
import com.test.fakebook.dto.RegisterResponse;
import com.test.fakebook.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    // Injecting AuthenticationService bean
    private final AuthenticationService authenticationService;

    // Endpoint for user registration
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest) {
        // Registering the user and returning appropriate response
        if (authenticationService.register(registerRequest)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterResponse("User registered successfully!"));
        } else {
            // Returning error response for invalid password
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RegisterResponse("Invalid password. You must create a stronger password: at least 8 characters (Use A-Z, a-z, and symbols)."));
        }
    }

    // Endpoint for user login
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Attempting login and returning successful response
            LoginResponse response = authenticationService.login(loginRequest);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            // Returning error response for invalid credentials
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LoginResponse("Invalid username or password."));
        }
    }
}
