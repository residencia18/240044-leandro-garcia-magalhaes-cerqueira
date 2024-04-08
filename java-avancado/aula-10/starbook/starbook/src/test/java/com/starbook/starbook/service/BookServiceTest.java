package com.starbook.starbook.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.javafaker.Faker;
import com.starbook.starbook.model.Book;
import com.starbook.starbook.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
	
	@Mock
	private BookRepository bookRepository;
	
	@InjectMocks
	private BookService bookService;
	
	private Faker faker;
	
	ArrayList<String> bookCovers;
	
	@BeforeEach
	void setUp() {
		faker = new Faker();
	}
	
	@BeforeAll
	private void createCovers() {
	bookCovers = new ArrayList<String>();
	bookCovers.add("https://th.bing.com/th/id/OIP.fjy0J4oxduE_KEQYBQI6jgHaLY?rs=1&pid=ImgDetMain");
	bookCovers.add("https://designbump.com/wp-content/uploads/2014/12/2014-book-cover-001.jpg");
	bookCovers.add("https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/56d96263885635.5acd0047cf3e6.jpg");
	bookCovers.add("https://i.guim.co.uk/img/static/sys-images/Guardian/Pix/pictures/2014/7/30/1406718532907/396ff814-8791-451b-9214-6a9b86d54142-1360x2040.jpeg?w=1200&q=55&auto=format&usm=12&fit=max&s=6c95eabb2ad2e46429e3e297fca94c58");
	bookCovers.add("https://images.ctfassets.net/usf1vwtuqyxm/35KbpLHvQvQtBBKs0vKErL/43985bc9e5bea863ccf9cc9561b62827/English_Harry_Potter_6_Epub_9781781100257.jpg?w=914&q=70&fm=jpg");
	}
	
	private Book generateFakeBook() {
		
		//Create and add book covers
		createCovers();
		
		//sort covers from array
		Collections.sort(bookCovers);
		
		Book book = new Book();
		book.setId(faker.number().randomNumber());
		book.setTitle(faker.book().title());
		book.setGenre(faker.book().genre());
		book.setSubgenre(faker.book().genre());
		book.setPublication_date(faker.date().birthday());
		book.setPage_count(faker.number().numberBetween(10, 1000));
		book.setStars(faker.number().numberBetween(1, 5));
		book.setReview(faker.lorem().characters(30, 500));
		book.setCover(bookCovers.getFirst());
		return book;
	}
	
	@Test
	void createBook_WithValidData_ReturnBook() {
		Book fakeBook = generateFakeBook();
		given(bookRepository.save(any(Book.class))).willReturn(fakeBook);
		
		Book savedBook = bookService.create(fakeBook);
		
		assertNotNull(fakeBook);
		assertEquals(fakeBook.getTitle(), savedBook.getTitle());
		assertEquals(fakeBook.getGenre(), savedBook.getGenre());
		verify(bookRepository).save(any(Book.class));
	}
	
	@Test
	void deleteBook_WithValidId_DeletesBook() {
	    // Create a fake book
	    Book fakeBook = generateFakeBook();

	    // Call the delete method of bookService
	    bookService.delete(fakeBook.getId());

	    // Verify that the bookRepository.delete method was called with the fake book
	    verify(bookRepository).deleteById(fakeBook.getId());
	}

	
	  @Test
	    void findBookById_WithUnexistingId_ReturnsEmpty() {
	        Long fakeId = faker.number().randomNumber();
	        given(bookRepository.findById(fakeId)).willReturn(Optional.empty());

	        Optional<Book> result = bookService.findById(fakeId);

	        assertFalse(result.isPresent());
	        verify(bookRepository).findById(fakeId);
	  }
	      
	    @Test
	    void updateBook_WithValidData_ReturnsUpdatedBook() {
	        Book originalBook = generateFakeBook();
	        Book updatedBook = generateFakeBook();

	        given(bookRepository.findById(originalBook.getId())).willReturn(Optional.of(originalBook));
	        given(bookRepository.save(any(Book.class))).willReturn(updatedBook);

	        Optional<Book> result = bookService.update(originalBook.getId(), updatedBook);

	        assertTrue(result.isPresent());
	        assertEquals(updatedBook.getTitle(), result.get().getTitle());
	        assertEquals(updatedBook.getGenre(), result.get().getGenre());
	        verify(bookRepository).findById(originalBook.getId());
	        verify(bookRepository).save(any(Book.class));
	    }
	
}
