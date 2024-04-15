package com.starbook.starbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.starbook.starbook.repository.UserRepository;

@Service
@Qualifier("v2")
public class UserServiceV2 extends UserServiceV1 {
	
	@Autowired
	UserRepository userRepository;
	
	public void deleteAllUsers() {
		userRepository.deleteAll();
	}
	
	

}
