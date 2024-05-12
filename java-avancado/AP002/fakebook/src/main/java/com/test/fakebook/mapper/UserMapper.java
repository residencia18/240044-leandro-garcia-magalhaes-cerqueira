package com.test.fakebook.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.fakebook.dto.RegisterRequest;
import com.test.fakebook.entity.User;
import com.test.fakebook.repository.RoleRepository;
import com.test.fakebook.entity.Role;

@Service
public class UserMapper {
	
	@Autowired
	private RoleRepository roleRepository;

    // Map RegisterRequest to User entity
    public User fromRegisterRequest(RegisterRequest registerRequest) {
        // Create a new User object
        User user = User.builder()
                .email(registerRequest.email())
                .username(registerRequest.username())
                .password(registerRequest.password())
                .build();
        
        // Find the USER role from the RoleRepository
        Role userRole = roleRepository.findByName("USER");
        
        // Add the USER role to the user's roles
        user.getRoles().add(userRole);
        
        return user;
    }
}

