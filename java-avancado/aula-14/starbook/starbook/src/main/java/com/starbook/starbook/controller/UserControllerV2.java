package com.starbook.starbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starbook.starbook.service.UserServiceV2;

@RestController
@RequestMapping("/api/v2/users")
public class UserControllerV2 {
	
	@Autowired
	@Qualifier("v2")
    private UserServiceV2 userService;
	
	@DeleteMapping
	public ResponseEntity<Void> deleteAllUsers(){
		userService.deleteAllUsers();
		return ResponseEntity.noContent().build();
	}
	
}