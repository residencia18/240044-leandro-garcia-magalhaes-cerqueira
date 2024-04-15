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
import com.starbook.starbook.model.Publisher;

@DataJpaTest
public class PublisherRepositoryV1Test {
	
	@Autowired
	private PublisherRepository publisherRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private Faker faker;
	
	private Publisher generateFakePublisher() {
		Publisher publisher = new Publisher();
		publisher.setName(faker.name().fullName());
		return publisher;
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
		assertThat(publisherRepository).isNotNull();
	}
	
	
	@Test
	void createPublisher_WithValidData_ReturnPublisher() {
		
		Publisher publisher = generateFakePublisher();
		
		Publisher savedPublisher = publisherRepository.save(publisher);
		
		assertThat(savedPublisher).isNotNull();
		assertThat(savedPublisher.getId()).isEqualTo(publisher.getId());
		assertThat(savedPublisher.getName()).isEqualTo(publisher.getName());
		
	}
	
	
	@Test
	void findPublisher_ById_ReturnsPublisher() {
		Publisher publisher = generateFakePublisher();
		
		Publisher persistedPublisher = testEntityManager.persistFlushFind(publisher);
		
		Optional<Publisher> foundPublisher = publisherRepository.findById(persistedPublisher.getId());
		
		assertThat(foundPublisher).isNotEmpty();
		assertThat(foundPublisher.get().getId()).isEqualTo(persistedPublisher.getId());
		
	}
	
	@Test
	void findPublisher_ByName_ReturnsPublisher() {
		Publisher publisher = generateFakePublisher();
		
		Publisher persistedPublisher = testEntityManager.persistFlushFind(publisher);
		
		Optional<Publisher> foundPublisher = publisherRepository.findByName(persistedPublisher.getName());
		
		assertThat(foundPublisher).isNotEmpty();
		assertThat(foundPublisher.get().getId()).isEqualTo(persistedPublisher.getId());
		
	}
	
	
	
	
	
}
