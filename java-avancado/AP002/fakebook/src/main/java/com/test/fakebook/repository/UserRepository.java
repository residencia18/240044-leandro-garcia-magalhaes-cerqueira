package com.test.fakebook.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.fakebook.entity.User;

// Repository interface for User entity, extending JpaRepository
public interface UserRepository extends JpaRepository<User, Long> {
	
	// Method to find a user by ID
	Optional<User> findById(Long id);

	// Method to find a user by username
	Optional<User> findByUsername(String username);
    
	// Method to find a user by email
	Optional<User> findByEmail(String email);
}
