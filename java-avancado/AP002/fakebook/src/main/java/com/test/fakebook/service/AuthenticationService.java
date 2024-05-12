package com.test.fakebook.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.test.fakebook.dto.LoginRequest;
import com.test.fakebook.dto.LoginResponse;
import com.test.fakebook.dto.NewPasswordRequest;
import com.test.fakebook.dto.RegisterRequest;
import com.test.fakebook.entity.User;
import com.test.fakebook.mapper.UserMapper;
import com.test.fakebook.repository.UserRepository;
import com.test.fakebook.security.JwtProvider;
import jakarta.validation.Valid;

import com.test.fakebook.validation.PasswordConstraintValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    // Autowired dependencies
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final UserMapper userMapper;

    // Method to register a new user
    public Boolean register(@Valid RegisterRequest registerRequest) {
        // Validate the password
        if (!isPasswordValid(registerRequest.password())) {
            return false;
        } else {
            // Map RegisterRequest to User entity
            User user = userMapper.fromRegisterRequest(registerRequest);
            // Encode the password before saving
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            // Save the user
            userRepository.save(user);
            return true;
        }
    }

    // Method to authenticate a user and generate JWT token
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            // Authenticate user
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
            // Generate JWT token
            String token = jwtProvider.generateToken(authenticate);
            return new LoginResponse(token);
        } catch (BadCredentialsException e) {
            // Handle authentication failure
            throw new BadCredentialsException("Invalid username or password.");
        }
    }

    // Method to validate password against password constraints
    boolean isPasswordValid(String password) {
        PasswordConstraintValidator validator = new PasswordConstraintValidator();
        return validator.isValid(password, null);
    }

    // Method to change user password
    public void changePassword(@Valid NewPasswordRequest newPasswordRequest){
        if(!isPasswordValid(newPasswordRequest.password())){
            // Handle invalid password
            ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Invalid password. You must create a stronger password: at least 8 characters (Use A-Z, a-z, and symbols) ");
            throw new IllegalArgumentException("Invalid password.");
        }
    }
}

