package com.test.fakebook.mapper;

import org.springframework.stereotype.Service;

import com.test.fakebook.dto.RegisterRequest;
import com.test.fakebook.entity.User;


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