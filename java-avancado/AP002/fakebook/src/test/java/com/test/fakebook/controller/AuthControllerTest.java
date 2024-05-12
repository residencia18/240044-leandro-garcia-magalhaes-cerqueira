package com.test.fakebook.controller;

import com.test.fakebook.dto.LoginRequest;
import com.test.fakebook.dto.LoginResponse;
import com.test.fakebook.dto.RegisterRequest;
import com.test.fakebook.dto.RegisterResponse;
import com.test.fakebook.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthController authController;

    @Test
    public void testRegister_ValidUser() {
        // Given
        RegisterRequest registerRequest = new RegisterRequest("username", "@StrongPwd123", "test@example.com");
        when(authenticationService.register(registerRequest)).thenReturn(true);

        // When
        ResponseEntity<RegisterResponse> responseEntity = authController.register(registerRequest);

        // Then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("User registered successfully!", responseEntity.getBody().message());
    }

    @Test
    public void testRegister_InvalidPassword() {
        // Given
        RegisterRequest registerRequest = new RegisterRequest("username", "weak", "test@example.com");
        when(authenticationService.register(registerRequest)).thenReturn(false);

        // When
        ResponseEntity<RegisterResponse> responseEntity = authController.register(registerRequest);

        // Then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Invalid password. You must create a stronger password: at least 8 characters (Use A-Z, a-z, and symbols).", responseEntity.getBody().message());
    }

    @Test
    public void testLogin_ValidCredentials() {
        // Given
        LoginRequest loginRequest = new LoginRequest("username", "@StrongPwd123");
        LoginResponse loginResponse = new LoginResponse("token");
        when(authenticationService.login(loginRequest)).thenReturn(loginResponse);

        // When
        ResponseEntity<LoginResponse> responseEntity = authController.login(loginRequest);

        // Then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("token", responseEntity.getBody().token());
    }

    @Test
    public void testLogin_InvalidCredentials() {
        // Given
        LoginRequest loginRequest = new LoginRequest("username", "weak");
        when(authenticationService.login(loginRequest)).thenThrow(BadCredentialsException.class);

        // When
        ResponseEntity<LoginResponse> responseEntity = authController.login(loginRequest);

        // Then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Invalid username or password.", responseEntity.getBody().token());
    }
}

