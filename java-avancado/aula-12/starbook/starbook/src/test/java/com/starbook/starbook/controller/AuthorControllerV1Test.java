package com.starbook.starbook.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.starbook.starbook.model.Author;
import com.starbook.starbook.service.AuthorServiceV1;

import org.springframework.http.MediaType;

@WebMvcTest(AuthorControllerV1.class)
public class AuthorControllerV1Test {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthorServiceV1 authorService;

    @Autowired
    private Faker faker;

    @TestConfiguration
    static class FakerTestConfig {

        @Bean
        public Faker faker() {
            return new Faker();
        }
    }
    
    private Author generateFakeAuthor() {
		Author author = new Author();
		author.setId(faker.number().randomNumber());
		author.setName(faker.name().fullName());
		return author;
	}
    
    @Test
    void createAuthor_WithValidData_ReturnsCreated() throws Exception { 
        Author newAuthor = generateFakeAuthor();
   
        Author savedAuthor = generateFakeAuthor();
        savedAuthor.setId(faker.number().randomNumber()); 
        
        when(authorService.create(any(Author.class))).thenReturn(savedAuthor);
    
        mockMvc.perform(post("/api/v1/authors")
                        .content(objectMapper.writeValueAsString(newAuthor))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(savedAuthor)));
    }
	
    
    @Test
    void getAllAuthors_ReturnsAuthorList() throws Exception {
        Author author = generateFakeAuthor();
        when(authorService.findAll()).thenReturn(Arrays.asList(author));

        mockMvc.perform(get("/api/v1/authors"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(author))));
    }

    @Test
    void getAuthorById_WhenAuthorExists_ReturnsAuthor() throws Exception {
        Author author = generateFakeAuthor();
        when(authorService.findById(1L)).thenReturn(Optional.of(author));

        mockMvc.perform(get("/api/v1/authors/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(author)));
    }

    @Test
    void getAuthorById_WhenAuthorDoesNotExist_ReturnsNotFound() throws Exception {
        when(authorService.findById(any(Long.class))).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/authors/{id}", 1))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateAuthor_WhenAuthorExists_ReturnsUpdatedAuthor() throws Exception {
        Author updateInfo = generateFakeAuthor(); 
        Author updatedAuthor = generateFakeAuthor(); 

        updatedAuthor.setName("Updated " + updatedAuthor.getName());
        when(authorService.update(any(Long.class), any(Author.class))).thenReturn(Optional.of(updatedAuthor));

        mockMvc.perform(put("/api/v1/authors/{id}", 1)
                        .content(objectMapper.writeValueAsString(updateInfo))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(updatedAuthor)));
    }
    
    
    
    
    
    

}
