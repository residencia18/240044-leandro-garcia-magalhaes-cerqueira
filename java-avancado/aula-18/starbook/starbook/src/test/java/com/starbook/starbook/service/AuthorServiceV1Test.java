package com.starbook.starbook.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.javafaker.Faker;
import com.starbook.starbook.model.Author;
import com.starbook.starbook.repository.AuthorRepository;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceV1Test {
	
	@Mock
	private AuthorRepository authorRepository;
	
	@InjectMocks
	private AuthorServiceV1 authorService;

	private Faker faker;
	
	@BeforeEach
	void setUp() {
		faker = new Faker();
	}
	
	private Author generateFakeAuthor() {
		Author author = new Author();
		author.setId(faker.number().randomNumber());
		author.setName(faker.name().fullName());
		return author;
	}
	
	@Test
	void createAuthor_WithValidData_ReturnAuthor() {
		Author fakeAuthor = generateFakeAuthor();
		given(authorRepository.save(any(Author.class))).willReturn(fakeAuthor);
		
		Author savedAuthor = authorService.create(fakeAuthor);
		
		assertNotNull(fakeAuthor);
		assertEquals(fakeAuthor.getId(), savedAuthor.getId());
		assertEquals(fakeAuthor.getName(), savedAuthor.getName());
	
		verify(authorRepository).save(any(Author.class));
		
	}
	
	@Test
	void deleteAuthor_WithValidId_DeletesAuthor() {
	    // Create a fake author
	    Author fakeAuthor = generateFakeAuthor();

	    // Call the delete method of authorService
	    authorService.delete(fakeAuthor.getId());

	    // Verify that the authorRepository.delete method was called with the fake author
	    verify(authorRepository).deleteById(fakeAuthor.getId());
	}

	
	  @Test
	    void findAuthorById_WithUnexistingId_ReturnsEmpty() {
	        Long fakeId = faker.number().randomNumber();
	        given(authorRepository.findById(fakeId)).willReturn(Optional.empty());

	        Optional<Author> result = authorService.findById(fakeId);

	        assertFalse(result.isPresent());
	        verify(authorRepository).findById(fakeId);
	   }
	      
	    @Test
	    void updateAuthor_WithValidData_ReturnsUpdatedAuthor() {
	        Author originalAuthor = generateFakeAuthor();
	        Author updatedAuthor = generateFakeAuthor();

	        given(authorRepository.findById(originalAuthor.getId())).willReturn(Optional.of(originalAuthor));
	        given(authorRepository.save(any(Author.class))).willReturn(updatedAuthor);

	        Optional<Author> result = authorService.update(originalAuthor.getId(), updatedAuthor);

	        assertTrue(result.isPresent());
	        assertEquals(updatedAuthor.getName(), result.get().getName());
	        assertEquals(updatedAuthor.getId(), result.get().getId());
	        verify(authorRepository).findById(originalAuthor.getId());
	        verify(authorRepository).save(any(Author.class));
	    }
	
	
}
