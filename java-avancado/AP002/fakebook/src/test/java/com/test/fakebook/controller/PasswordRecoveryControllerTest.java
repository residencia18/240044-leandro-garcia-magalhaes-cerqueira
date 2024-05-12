package com.test.fakebook.controller;

import com.test.fakebook.dto.EmailRequest;
import com.test.fakebook.service.ChangePasswordService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PasswordRecoveryControllerTest {

    @Mock
    private ChangePasswordService changePasswordService;

    @InjectMocks
    private PasswordRecoveryController passwordRecoveryController;

    @Test
    public void testRequestPasswordRecovery_Successful() {
        // Given
        EmailRequest emailRequest = new EmailRequest("user@example.com");
        when(changePasswordService.findUserAndSendEmail(emailRequest)).thenReturn(true);

        // When
        ResponseEntity<String> response = passwordRecoveryController.requestPasswordRecovery(emailRequest);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("A recovery email has been sent to user@example.com", response.getBody());
        verify(changePasswordService, times(1)).findUserAndSendEmail(emailRequest);
    }

    @Test
    public void testRequestPasswordRecovery_UserNotFound() {
        // Given
        EmailRequest emailRequest = new EmailRequest("nonexistent@example.com");
        when(changePasswordService.findUserAndSendEmail(emailRequest)).thenReturn(false);

        // When
        ResponseEntity<String> response = passwordRecoveryController.requestPasswordRecovery(emailRequest);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found for the email provided.", response.getBody());
        verify(changePasswordService, times(1)).findUserAndSendEmail(emailRequest);
    }
}

