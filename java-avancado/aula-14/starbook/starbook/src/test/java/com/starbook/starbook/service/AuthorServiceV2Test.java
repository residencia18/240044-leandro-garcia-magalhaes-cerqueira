package com.starbook.starbook.service;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.starbook.starbook.repository.AuthorRepository;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceV2Test {
	
	@Mock
	private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceV2 authorService;

    @Test
    void deleteAllAuthors_ShouldCallRepositoryDeleteAll() {
        // Arrange

        // Act
        authorService.deleteAllAuthors();

        // Assert
        verify(authorRepository).deleteAll();
    }
	
}
