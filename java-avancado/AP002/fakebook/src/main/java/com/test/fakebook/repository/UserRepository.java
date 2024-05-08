package com.test.fakebook.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.fakebook.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findById(Long id);

	Optional<User> findByUsername(String username);
    
	Optional<User> findByEmail(String email);
	

}
