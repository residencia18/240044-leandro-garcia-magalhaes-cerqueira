package com.starbook.starbook.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.github.javafaker.Faker;
import com.starbook.starbook.model.Book;


@DataJpaTest
public class BookRepositoryTest {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private Faker faker;
	
	ArrayList<String> bookCovers;
	
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
//			book.setId(faker.number().randomNumber());
			book.setTitle(faker.book().title());
			book.setGenre(faker.book().genre());
			book.setSubgenre(faker.book().genre());
			book.setPublication_date(faker.date().birthday());
			book.setPage_count(faker.number().numberBetween(10, 1000));
			book.setStars(faker.number().numberBetween(1, 5));
			book.setReview(faker.lorem().characters(1, 1000));
			book.setCover(bookCovers.getFirst());
			book.setPhysical(faker.bool().bool());
			return book;
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
		assertThat(bookRepository).isNotNull();
	}
	
	@Test
	void createBook_WithValidData_ReturnBook() {
		
		Book book = generateFakeBook();
		
		Book savedBook = bookRepository.save(book);
		
		assertThat(savedBook).isNotNull();
		assertThat(savedBook.getId()).isEqualTo(book.getId());
		assertThat(savedBook.getTitle()).isEqualTo(savedBook.getTitle());
		
	}
	
	@Test
	void findBook_ById_ReturnBook() {
		Book book = generateFakeBook();
		
		Book persistedBook = testEntityManager.persistFlushFind(book);
		
		Optional<Book> foundBook = bookRepository.findById(persistedBook.getId());
		
		assertThat(foundBook).isNotEmpty();
		assertThat(foundBook.get().getId()).isEqualTo(persistedBook.getId());
		
	}
	
	@Test
	void findBook_ByTitle_ReturnsBook() {
		
		Book book = generateFakeBook();
		
		Book persistedBook = testEntityManager.persistFlushFind(book);
		
		Optional<Book> foundBook = bookRepository.findByTitle(persistedBook.getTitle());
		
		assertThat(foundBook).isNotEmpty();
		assertThat(foundBook.get().getId()).isEqualTo(persistedBook.getId());
		
	}
	
	
	
	

}
