package com.starbook.starbook.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.github.javafaker.Faker;
import com.starbook.starbook.model.User;

@DataJpaTest
public class UserRepositoryV1Test {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private Faker faker;
	
	private User generateFakeUser() {
		User user = new User();
		user.setLogin(faker.name().username());
		user.setPassword(faker.rickAndMorty().character());
		return user;
	}

	@TestConfiguration
	static class FakerTestConfig {
		
		@Bean
		public Faker faker() {
			return new Faker();
		}
	}
	
	@Test
	void injectComponentsAreNotNull() {
		assertThat(testEntityManager).isNotNull();
		assertThat(userRepository).isNotNull();
	}
	
	
	@Test
	void createUser_WithValidData_ReturnUser() {
		
		User user = generateFakeUser();
		
		User savedUser = userRepository.save(user);
		
		assertThat(savedUser).isNotNull();
		assertThat(savedUser.getId()).isEqualTo(user.getId());
		assertThat(savedUser.getLogin()).isEqualTo(user.getLogin());
		
	}
	
	@Test
	void findUser_ById_ReturnsUser() {
		User user = generateFakeUser();
		
		User persistedUser = testEntityManager.persistFlushFind(user);
		
		Optional<User> foundUser = userRepository.findById(persistedUser.getId());
		
		assertThat(foundUser).isNotEmpty();
		assertThat(foundUser.get().getId()).isEqualTo(persistedUser.getId());
		
	}
	
	@Test
	void findUser_ByLogin_ReturnsUser() {
		User user = generateFakeUser();
		
		User persistedUser = testEntityManager.persistFlushFind(user);
		
		Optional<User> foundUser = userRepository.findByLogin(persistedUser.getLogin());
		
		assertThat(foundUser).isNotEmpty();
		assertThat(foundUser.get().getId()).isEqualTo(persistedUser.getId());
		
	}

}