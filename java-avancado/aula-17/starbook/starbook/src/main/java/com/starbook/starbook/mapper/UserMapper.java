package com.starbook.starbook.mapper;

import org.springframework.stereotype.Service;

import com.starbook.starbook.dto.RegisterRequest;
import com.starbook.starbook.model.User;




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