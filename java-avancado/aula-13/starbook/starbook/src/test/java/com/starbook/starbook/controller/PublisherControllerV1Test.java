package com.starbook.starbook.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.starbook.starbook.model.Publisher;
import com.starbook.starbook.service.PublisherServiceV1;

import org.springframework.http.MediaType;

@WebMvcTest(PublisherControllerV1.class)
public class PublisherControllerV1Test {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    private ObjectMapper objectMapper;

    @MockBean
    @Qualifier("v1")
    private PublisherServiceV1 publisherService;

    @Autowired
    private Faker faker;

    @TestConfiguration
    static class FakerTestConfig {

        @Bean
        public Faker faker() {
            return new Faker();
        }
    }
    
    private Publisher generateFakePublisher() {
		Publisher publisher = new Publisher();
		publisher.setId(faker.number().randomNumber());
		publisher.setName(faker.name().fullName());
		return publisher;
	}
    
    @Test
    void createPublisher_WithValidData_ReturnsCreated() throws Exception { 
        Publisher newPublisher = generateFakePublisher();
   
        Publisher savedPublisher = generateFakePublisher();
        savedPublisher.setId(faker.number().randomNumber()); 
        
        when(publisherService.create(any(Publisher.class))).thenReturn(savedPublisher);
    
        mockMvc.perform(post("/api/v1/publishers")
                        .content(objectMapper.writeValueAsString(newPublisher))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(savedPublisher)));
    }
	
    
    @Test
    void getAllPublishers_ReturnsPublisherList() throws Exception {
        Publisher publisher = generateFakePublisher();
        when(publisherService.findAll()).thenReturn(Arrays.asList(publisher));

        mockMvc.perform(get("/api/v1/publishers"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(publisher))));
    }

    @Test
    void getPublisherById_WhenPublisherExists_ReturnsPublisher() throws Exception {
        Publisher publisher = generateFakePublisher();
        when(publisherService.findById(1L)).thenReturn(Optional.of(publisher));

        mockMvc.perform(get("/api/v1/publishers/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(publisher)));
    }

    @Test
    void getPublisherById_WhenPublisherDoesNotExist_ReturnsNotFound() throws Exception {
        when(publisherService.findById(any(Long.class))).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/publishers/{id}", 1))
                .andExpect(status().isNotFound());
    }

    @Test
    void updatePublisher_WhenPublisherExists_ReturnsUpdatedPublisher() throws Exception {
        Publisher updateInfo = generateFakePublisher(); 
        Publisher updatedPublisher = generateFakePublisher(); 

        updatedPublisher.setName("Updated " + updatedPublisher.getName());
        when(publisherService.update(any(Long.class), any(Publisher.class))).thenReturn(Optional.of(updatedPublisher));

        mockMvc.perform(put("/api/v1/publishers/{id}", 1)
                        .content(objectMapper.writeValueAsString(updateInfo))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(updatedPublisher)));
    }
    
    
    
    
    
    

}
