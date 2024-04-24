package com.starbook.starbook.service;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.starbook.starbook.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
public class BookServiceV2Test {
	
	@Mock
	private BookRepository bookRepository;
	
	@InjectMocks
	private BookServiceV2 bookService;
	
	@Test
	void deleteAllBooks_ShouldCallRepositoryDeleteAll() {
		
		bookService.deleteAllBooks();
		verify(bookRepository).deleteAll();
	}
}
