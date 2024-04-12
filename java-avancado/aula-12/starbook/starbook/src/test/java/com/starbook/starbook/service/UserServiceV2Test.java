package com.starbook.starbook.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import com.github.javafaker.Faker;
import com.starbook.starbook.model.User;
import com.starbook.starbook.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceV2Test {
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserServiceV1 userService;
	
	private Faker faker;
	
	@BeforeEach
	void setUp() {
		faker = new Faker();
	}
		
	private User generateFakeUser() {
		User user = new User();
		user.setId(faker.number().randomNumber());
		user.setLogin(faker.name().username());
		user.setPassword(faker.number().digits(8));
		return user;
	}
	
	@Test
	void createUser_WithValidData_ReturnUser() {
		User fakeUser = generateFakeUser();
		given(userRepository.save(any(User.class))).willReturn(fakeUser);
		
		User savedUser = userService.create(fakeUser);
		
		assertNotNull(fakeUser);
		assertEquals(fakeUser.getLogin(), savedUser.getLogin());
		assertEquals(fakeUser.getPassword(), savedUser.getPassword());
		verify(userRepository).save(any(User.class));
		
	}
	
	@Test
	void createUser_WithLoginAlreadyExists_ThrowsDataIntegrityViolationException(){
		
		User fakeUser = generateFakeUser();
		
		// Simulating a uniqueness restrict violation of the "login" field.
		willThrow(DataIntegrityViolationException.class).given(userRepository).save(argThat(newUser -> newUser.getLogin().equals(fakeUser.getLogin())));
		
		//Attempts to create the Employee, expecting the exception to be thrown due to the name uniqueness violation.
		assertThrows(DataIntegrityViolationException.class, () -> userService.create(fakeUser));
		
		// Checks whether the save method was actually called, indicating that the attempt to save the User was made.
        then(userRepository).should().save(any(User.class));
		
	}
	

	@Test
	void deleteUser_WithValidId_DeletesUser() {
	    // Create a fake user
	    User fakeUser = generateFakeUser();

	    // Call the delete method of userService
	    userService.delete(fakeUser.getId());

	    // Verify that the userRepository.delete method was called with the fake user
	    verify(userRepository).deleteById(fakeUser.getId());
	}

	
	  @Test
	    void findUserById_WithUnexistingId_ReturnsEmpty() {
	        Long fakeId = faker.number().randomNumber();
	        given(userRepository.findById(fakeId)).willReturn(Optional.empty());

	        Optional<User> result = userService.findById(fakeId);

	        assertFalse(result.isPresent());
	        verify(userRepository).findById(fakeId);
	   }
	      
	    @Test
	    void updateUser_WithValidData_ReturnsUpdatedUser() {
	        User originalUser = generateFakeUser();
	        User updatedUser = generateFakeUser();

	        given(userRepository.findById(originalUser.getId())).willReturn(Optional.of(originalUser));
	        given(userRepository.save(any(User.class))).willReturn(updatedUser);

	        Optional<User> result = userService.update(originalUser.getId(), updatedUser);

	        assertTrue(result.isPresent());
	        assertEquals(updatedUser.getLogin(), result.get().getLogin());
	        assertEquals(updatedUser.getPassword(), result.get().getPassword());
	        verify(userRepository).findById(originalUser.getId());
	        verify(userRepository).save(any(User.class));
	    }

}
