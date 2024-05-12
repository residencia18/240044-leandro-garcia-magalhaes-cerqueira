package com.test.fakebook.controller;

import com.test.fakebook.dto.NewPasswordRequest;
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
public class PasswordResetControllerTest {

    @Mock
    private ChangePasswordService changePasswordService;

    @InjectMocks
    private PasswordResetController passwordResetController;

    @Test
    public void testResetPassword_Successful() {
        // Given
        String token = "valid_token";
        NewPasswordRequest newPasswordRequest = new NewPasswordRequest("newPassword123");
        when(changePasswordService.changePassword(token, newPasswordRequest)).thenReturn(true);

        // When
        ResponseEntity<String> response = passwordResetController.resetPassword(token, newPasswordRequest);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Password updated with success!", response.getBody());
        verify(changePasswordService, times(1)).changePassword(token, newPasswordRequest);
    }

    @Test
    public void testResetPassword_InvalidToken() {
        // Given
        String token = "invalid_token";
        NewPasswordRequest newPasswordRequest = new NewPasswordRequest("newPassword123");
        when(changePasswordService.changePassword(token, newPasswordRequest)).thenReturn(false);

        // When
        ResponseEntity<String> response = passwordResetController.resetPassword(token, newPasswordRequest);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid token.", response.getBody());
        verify(changePasswordService, times(1)).changePassword(token, newPasswordRequest);
    }
}
