package com.test.fakebook.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.fakebook.entity.User;
import com.test.fakebook.repository.UserRepository;

@Service
public class UserService {

	@Autowired
    private UserRepository userRepository;

    public Optional<User> findById(Long id){
    	return userRepository.findById(id);
    }
    
    
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    

	public void saveUser(User user) {
		userRepository.save(user);
		
	}

  
}
