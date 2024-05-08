package com.test.fakebook.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.test.fakebook.dto.LoginRequest;
import com.test.fakebook.dto.LoginResponse;
import com.test.fakebook.dto.RegisterRequest;
import com.test.fakebook.entity.User;
import com.test.fakebook.mapper.UserMapper;
import com.test.fakebook.repository.UserRepository;
import com.test.fakebook.security.JwtProvider;

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
        userRepository.save(user);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(),
                loginRequest.password()));
        String token = jwtProvider.generateToken(authenticate);
        return new LoginResponse(token);
    }
}