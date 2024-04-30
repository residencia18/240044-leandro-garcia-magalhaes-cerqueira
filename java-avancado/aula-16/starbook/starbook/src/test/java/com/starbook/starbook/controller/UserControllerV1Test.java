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
import com.starbook.starbook.model.User;
import com.starbook.starbook.service.UserServiceV1;

import org.springframework.http.MediaType;

@WebMvcTest(UserControllerV1.class)
public class UserControllerV1Test {
	
//	@Autowired
//	private MockMvc mockMvc;
//	
//	@Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    @Qualifier("v1")
//    private UserServiceV1 userService;
//
//    @Autowired
//    private Faker faker;
//
//    @TestConfiguration
//    static class FakerTestConfig {
//
//        @Bean
//        public Faker faker() {
//            return new Faker();
//        }
//    }
//    
//    private User generateFakeUser() {
//		User user = new User();
//		user.setId(faker.number().randomNumber());
//		user.setUsername(faker.name().username());
//		return user;
//	}
//    
//    @Test
//    void createUser_WithValidData_ReturnsCreated() throws Exception { 
//        User newUser = generateFakeUser();
//   
//        User savedUser = generateFakeUser();
//        savedUser.setId(faker.number().randomNumber()); 
//        
//        when(userService.create(any(User.class))).thenReturn(savedUser);
//    
//        mockMvc.perform(post("/api/v1/users")
//                        .content(objectMapper.writeValueAsString(newUser))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(content().json(objectMapper.writeValueAsString(savedUser)));
//    }
//	
//    
//    @Test
//    void getAllUsers_ReturnsUserList() throws Exception {
//        User user = generateFakeUser();
//        when(userService.findAll()).thenReturn(Arrays.asList(user));
//
//        mockMvc.perform(get("/api/v1/users"))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(user))));
//    }
//
//    @Test
//    void getUserById_WhenUserExists_ReturnsUser() throws Exception {
//        User user = generateFakeUser();
//        when(userService.findById(1L)).thenReturn(Optional.of(user));
//
//        mockMvc.perform(get("/api/v1/users/{id}", 1))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(user)));
//    }
//
//    @Test
//    void getUserById_WhenUserDoesNotExist_ReturnsNotFound() throws Exception {
//        when(userService.findById(any(Long.class))).thenReturn(Optional.empty());
//
//        mockMvc.perform(get("/api/v1/users/{id}", 1))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void updateUser_WhenUserExists_ReturnsUpdatedUser() throws Exception {
//        User updateInfo = generateFakeUser(); 
//        User updatedUser = generateFakeUser(); 
//
//        updatedUser.setUsername("Updated " + updatedUser.getUsername());
//        when(userService.update(any(Long.class), any(User.class))).thenReturn(Optional.of(updatedUser));
//
//        mockMvc.perform(put("/api/v1/users/{id}", 1)
//                        .content(objectMapper.writeValueAsString(updateInfo))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(updatedUser)));
//    }
//    
//    @Test
//    void deleteUser_WhenUserExists_ReturnsNoContent() throws Exception {
//        mockMvc.perform(delete("/api/v1/users/{id}", 1))
//                .andExpect(status().isNoContent());
//    }
}
