package com.starbook.starbook.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.verify;
import com.starbook.starbook.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceV2Test {
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserServiceV2 userService;
	
	@Test
	void deleteAllUsers_ShouldCallRepositoryDeleteAll() {
		
		userService.deleteAllUsers();
		verify(userRepository).deleteAll();
	}	

}
