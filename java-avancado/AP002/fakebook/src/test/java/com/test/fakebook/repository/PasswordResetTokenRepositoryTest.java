package com.test.fakebook.repository;

import com.test.fakebook.entity.PasswordResetToken;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class PasswordResetTokenRepositoryTest {

    @Mock
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Test
    public void testFindByToken() {
        // Given
        String token = "sample_token";
        PasswordResetToken expectedToken = new PasswordResetToken(1L, token, null, new Date());

        // Stubbing the repository method
        when(passwordResetTokenRepository.findByToken(token)).thenReturn(Optional.of(expectedToken));

        // When
        Optional<PasswordResetToken> result = passwordResetTokenRepository.findByToken(token);

        // Then
        assertTrue(result.isPresent());
        assertEquals(expectedToken, result.get());
    }

    @Test
    public void testDeleteByToken() {
        // Given
        String token = "sample_token";

        // When
        passwordResetTokenRepository.deleteByToken(token);

        // Then
        verify(passwordResetTokenRepository, times(1)).deleteByToken(token);
    }

    @Test
    public void testInvalidateToken() {
        // Given
        String token = "sample_token";

        // When
        passwordResetTokenRepository.invalidateToken(token);

        // Then
        verify(passwordResetTokenRepository, times(1)).invalidateToken(token);
    }
}


