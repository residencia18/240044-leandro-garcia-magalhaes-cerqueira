package com.test.fakebook.service;
import com.test.fakebook.dto.EmailRequest;
import com.test.fakebook.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ChangePasswordServiceTest {

    @Mock
    private TokenService tokenService;

    @Mock
    private EmailService emailService;

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private ChangePasswordService changePasswordService;

    @Test
    public void testFindUserAndSendEmail_UserExists() {
        // Given
        String email = "test@example.com";
        EmailRequest emailRequest = new EmailRequest(email);
        User user = new User();
        when(userService.findByEmail(email)).thenReturn(Optional.of(user));
        when(tokenService.generateToken()).thenReturn("test_token");

        // When
        boolean result = changePasswordService.findUserAndSendEmail(emailRequest);

        // Then
        assertTrue(result);
        verify(tokenService, times(1)).generateToken();
        verify(tokenService, times(1)).saveToken(eq("test_token"), eq(user));
        verify(emailService, times(1)).sendEmail(eq(email), any(), any());
    }

    @Test
    public void testFindUserAndSendEmail_UserDoesNotExist() {
        // Given
        String email = "nonexistent@example.com";
        EmailRequest emailRequest = new EmailRequest(email);
        when(userService.findByEmail(email)).thenReturn(Optional.empty());

        // When
        boolean result = changePasswordService.findUserAndSendEmail(emailRequest);

        // Then
        assertFalse(result);
        verify(tokenService, never()).generateToken();
        verify(tokenService, never()).saveToken(any(), any());
        verify(emailService, never()).sendEmail(any(), any(), any());
    }

}
