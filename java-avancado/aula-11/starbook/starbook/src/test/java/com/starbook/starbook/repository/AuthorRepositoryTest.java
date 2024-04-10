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
import com.starbook.starbook.model.Author;

@DataJpaTest
public class AuthorRepositoryTest {
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private Faker faker;
	
	private Author generateFakeAuthor() {
		Author author = new Author();
		author.setId(faker.number().randomNumber());
		author.setName(faker.name().fullName());
		return author;
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
		assertThat(authorRepository).isNotNull();
	}
	
	
	@Test
	void createAuthor_WithValidData_ReturnAuthor() {
		
		Author author = generateFakeAuthor();
		
		Author savedAuthor = authorRepository.save(author);
		
		assertThat(savedAuthor).isNotNull();
		assertThat(savedAuthor.getId()).isEqualTo(author.getId());
		assertThat(savedAuthor.getName()).isEqualTo(author.getName());
		
	}
	
	
	@Test
	void findAuthor_ById_ReturnsAuthor() {
		Author author = generateFakeAuthor();
		
		Author persistedAuthor = testEntityManager.persistFlushFind(author);
		
		Optional<Author> foundAuthor = authorRepository.findById(persistedAuthor.getId());
		
		assertThat(foundAuthor).isNotEmpty();
		assertThat(foundAuthor.get().getId()).isEqualTo(persistedAuthor.getId());
		
	}
	
	@Test
	void findAuthor_ByName_ReturnsAuthor() {
		Author author = generateFakeAuthor();
		
		Author persistedAuthor = testEntityManager.persistFlushFind(author);
		
		Optional<Author> foundAuthor = authorRepository.findByName(persistedAuthor.getName());
		
		assertThat(foundAuthor).isNotEmpty();
		assertThat(foundAuthor.get().getId()).isEqualTo(persistedAuthor.getId());
		
	}
	
	
	
	
	
}
