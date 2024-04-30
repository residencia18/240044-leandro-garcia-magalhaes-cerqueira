package com.starbook.starbook.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starbook.starbook.dto.LoginRequest;
import com.starbook.starbook.dto.LoginResponse;
import com.starbook.starbook.dto.RegisterRequest;
import com.starbook.starbook.dto.RegisterResponse;
import com.starbook.starbook.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest registerRequest) {
        authenticationService.register(registerRequest);
        return new RegisterResponse("User registered successfully");
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authenticationService.login(loginRequest);
    }
}