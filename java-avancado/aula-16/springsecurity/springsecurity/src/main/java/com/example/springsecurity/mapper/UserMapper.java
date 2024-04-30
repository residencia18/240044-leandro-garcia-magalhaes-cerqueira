package com.example.springsecurity.mapper;

import org.springframework.stereotype.Service;

import com.example.springsecurity.dto.RegisterRequest;
import com.example.springsecurity.entity.User;


@Service
public class UserMapper {

    public User fromRegisterRequest(RegisterRequest registerRequest) {
        return User.builder()
                .email(registerRequest.email())
                .username(registerRequest.username())
                .password(registerRequest.password())
                .role("ROLE_USER")
                .build();
    }
}