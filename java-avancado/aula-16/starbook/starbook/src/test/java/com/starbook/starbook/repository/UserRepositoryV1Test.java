package com.starbook.starbook.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.simple.JdbcClient;

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
	
	JdbcClient jdbcClient;
	
	private User generateFakeUser() {
		User user = new User();
		user.setUsername(faker.name().username());
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
		assertThat(savedUser.getUsername()).isEqualTo(user.getUsername());
		
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
	void findUser_ByUsername_ReturnsUser() {
		User user = generateFakeUser();
		
		User persistedUser = testEntityManager.persistFlushFind(user);
		
		Optional<User> foundUser = userRepository.findByUsername(persistedUser.getUsername(), jdbcClient);
		
		assertThat(foundUser).isNotEmpty();
		assertThat(foundUser.get().getId()).isEqualTo(persistedUser.getId());
		
	}

}
