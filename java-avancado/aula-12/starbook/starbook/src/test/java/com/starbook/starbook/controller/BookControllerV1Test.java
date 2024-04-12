package com.starbook.starbook.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.starbook.starbook.model.Book;
import com.starbook.starbook.service.BookServiceV1;

@WebMvcTest(BookControllerV1.class)
public class BookControllerV1Test {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookServiceV1 bookService;

    @Autowired
    private Faker faker;

    @TestConfiguration
    static class FakerTestConfig {

        @Bean
        public Faker faker() {
            return new Faker();
        }
    }
    
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
        void createBook_WithValidData_ReturnsCreated() throws Exception { 
            Book newBook = generateFakeBook();
       
            Book savedBook = generateFakeBook();
            savedBook.setId(faker.number().randomNumber()); 
            
            when(bookService.create(any(Book.class))).thenReturn(savedBook);
        
            mockMvc.perform(post("/api/v1/books")
                            .content(objectMapper.writeValueAsString(newBook))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(content().json(objectMapper.writeValueAsString(savedBook)));
        }
    	
        
        @Test
        void getAllBooks_ReturnsBookList() throws Exception {
            Book book = generateFakeBook();
            when(bookService.findAll()).thenReturn(Arrays.asList(book));

            mockMvc.perform(get("/api/v1/books"))
                    .andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(book))));
        }

        @Test
        void getBookById_WhenBookExists_ReturnsBook() throws Exception {
            Book book = generateFakeBook();
            when(bookService.findById(1L)).thenReturn(Optional.of(book));

            mockMvc.perform(get("/api/v1/books/{id}", 1))
                    .andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(book)));
        }

        @Test
        void getBookById_WhenBookDoesNotExist_ReturnsNotFound() throws Exception {
            when(bookService.findById(any(Long.class))).thenReturn(Optional.empty());

            mockMvc.perform(get("/api/v1/books/{id}", 1))
                    .andExpect(status().isNotFound());
        }

        @Test
        void updateBook_WhenBookExists_ReturnsUpdatedBook() throws Exception {
            Book updateInfo = generateFakeBook(); 
            Book updatedBook = generateFakeBook(); 

            updatedBook.setTitle("Updated " + updatedBook.getTitle());
            when(bookService.update(any(Long.class), any(Book.class))).thenReturn(Optional.of(updatedBook));

            mockMvc.perform(put("/api/v1/books/{id}", 1)
                            .content(objectMapper.writeValueAsString(updateInfo))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(updatedBook)));
        }
        
        @Test
        void deleteBook_WhenBookExists_ReturnsNoContent() throws Exception {
            mockMvc.perform(delete("/api/v1/books/{id}", 1))
                    .andExpect(status().isNoContent());
        }

}
