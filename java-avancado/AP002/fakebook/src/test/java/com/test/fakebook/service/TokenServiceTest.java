package com.test.fakebook.service;

import com.test.fakebook.entity.PasswordResetToken;
import com.test.fakebook.entity.User;
import com.test.fakebook.repository.PasswordResetTokenRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TokenServiceTest {

    @Mock
    private PasswordResetTokenRepository tokenRepository;

    @InjectMocks
    private TokenService tokenService;

    @Test
    public void testGenerateToken() {
        // When
        String token = tokenService.generateToken();

        // Then
        assertNotNull(token);
        assertTrue(token.matches("^\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}$")); // Check if token is in UUID format
    }

    @Test
    public void testSaveToken() {
        // Given
        User user = new User();
        String token = UUID.randomUUID().toString();

        // When
        tokenService.saveToken(token, user);

        // Then
        verify(tokenRepository, times(1)).save(any(PasswordResetToken.class));
    }

    @Test
    public void testFindByToken_TokenExistsAndNotExpired() {
        // Given
        String tokenValue = UUID.randomUUID().toString();
        PasswordResetToken token = new PasswordResetToken();
        token.setToken(tokenValue);
        token.setExpiryDate(new Date(System.currentTimeMillis() + 600000)); // Set expiry date 10 minutes in the future
        when(tokenRepository.findByToken(tokenValue)).thenReturn(Optional.of(token));

        // When
        PasswordResetToken result = tokenService.findByToken(tokenValue);

        // Then
        assertNotNull(result);
        assertEquals(tokenValue, result.getToken());
        assertFalse(tokenService.isTokenExpired(result));
    }

    @Test
    public void testFindByToken_TokenExistsAndExpired() {
        // Given
        String tokenValue = UUID.randomUUID().toString();
        PasswordResetToken token = new PasswordResetToken();
        token.setToken(tokenValue);
        token.setExpiryDate(new Date(System.currentTimeMillis() - 600000)); // Set expiry date 10 minutes in the past
        when(tokenRepository.findByToken(tokenValue)).thenReturn(Optional.of(token));

        // When
        PasswordResetToken result = tokenService.findByToken(tokenValue);

        // Then
        assertNull(result);
    }

    @Test
    public void testFindByToken_TokenNotExists() {
        // Given
        String tokenValue = UUID.randomUUID().toString();
        when(tokenRepository.findByToken(tokenValue)).thenReturn(Optional.empty());

        // When
        PasswordResetToken result = tokenService.findByToken(tokenValue);

        // Then
        assertNull(result);
    }

    @Test
    public void testInvalidateToken_TokenExists() {
        // Given
        String tokenValue = UUID.randomUUID().toString();
        PasswordResetToken token = new PasswordResetToken();
        token.setToken(tokenValue);
        when(tokenRepository.findByToken(tokenValue)).thenReturn(Optional.of(token));

        // When
        tokenService.invalidateToken(tokenValue);

        // Then
        verify(tokenRepository, times(1)).deleteByToken(tokenValue);
    }

    @Test
    public void testInvalidateToken_TokenNotExists() {
        // Given
        String tokenValue = UUID.randomUUID().toString();
        when(tokenRepository.findByToken(tokenValue)).thenReturn(Optional.empty());

        // When
        tokenService.invalidateToken(tokenValue);

        // Then
        verify(tokenRepository, never()).deleteByToken(any());
    }
}

