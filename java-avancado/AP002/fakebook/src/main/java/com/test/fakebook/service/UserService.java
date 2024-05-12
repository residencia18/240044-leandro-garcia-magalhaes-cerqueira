package com.test.fakebook.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.fakebook.entity.User;
import com.test.fakebook.repository.UserRepository;

// Service class for user-related operations
@Service
public class UserService {

    // Autowired repository for users
    @Autowired
    private UserRepository userRepository;

    // Method to find a user by ID
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    // Method to find a user by email
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Method to save a user
    public void saveUser(User user) {
        userRepository.save(user);
    }
}

