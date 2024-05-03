package com.test.fakebook.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.fakebook.dto.RegisterRequest;
import com.test.fakebook.dto.RegisterResponse;
import com.test.fakebook.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class PasswordRecoveryController {
	
	private final AuthenticationService authenticationService;
	
	 @PostMapping("/recovery")
	    public RegisterResponse register(@RequestBody RegisterRequest registerRequest) {
	        authenticationService.register(registerRequest);
	        return new RegisterResponse("User registered successfully");
	    }

}
