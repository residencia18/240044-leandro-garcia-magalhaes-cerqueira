package com.starbook.starbook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

import com.starbook.starbook.model.User;

import com.starbook.starbook.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Primary
@Qualifier("v1")
public class UserServiceV1 {
	
	JdbcClient jdbcClient;
	
	@Autowired
	private UserRepository userRepository;
	
	@Cacheable("users")
	public List<User> findAll(){
    	log.info("Find all users method started...");
		return userRepository.findAll();
	}

	
	public User create(User user) {
		log.info("Create User method started...");
		return userRepository.save(user);
	}
	
	
	@Cacheable("userById")
	public Optional<User> findById(Long id){
		log.info("Find User by Id method started...");
		return userRepository.findById(id);
	}
	
	@Cacheable("userByUsername")
	public Optional<User> findByUsername(String username) {
    	log.info("Find User by Username method started...");
        return userRepository.findByUsername(username, jdbcClient);
    }

	
	public Optional<User> update(Long id, User updatedUser){
		log.info("update User method started...");
	    return userRepository.findById(id)
	           .map(user -> {
	                user.setUsername(updatedUser.getUsername());
	                user.setPassword(updatedUser.getPassword());
	                return userRepository.save(user);
	            });
	}


	public void delete(Long id) {
		log.info("Delete User method started...");
		userRepository.deleteById(id);
	}
	
}
