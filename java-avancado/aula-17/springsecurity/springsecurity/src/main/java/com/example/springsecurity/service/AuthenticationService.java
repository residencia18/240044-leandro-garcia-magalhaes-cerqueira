package com.example.springsecurity.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springsecurity.dto.LoginRequest;
import com.example.springsecurity.dto.LoginResponse;
import com.example.springsecurity.dto.RegisterRequest;
import com.example.springsecurity.entity.User;
import com.example.springsecurity.mapper.UserMapper;
import com.example.springsecurity.repository.UserRepository;
import com.example.springsecurity.security.JwtProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final UserMapper userMapper;

    public void register(RegisterRequest registerRequest) {
        User user = userMapper.fromRegisterRequest(registerRequest);// Usando o UserMapper para criar o objeto User
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.saveUser(user);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(),
                loginRequest.password()));
        String token = jwtProvider.generateToken(authenticate);
        return new LoginResponse(token);
    }
}