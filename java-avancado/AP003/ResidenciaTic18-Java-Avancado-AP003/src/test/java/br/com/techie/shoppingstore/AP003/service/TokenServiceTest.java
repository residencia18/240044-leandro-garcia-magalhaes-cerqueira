package br.com.techie.shoppingstore.AP003.service;


import br.com.techie.shoppingstore.AP003.infra.jwt.JwtUserDetailsService;
import br.com.techie.shoppingstore.AP003.model.Token;
import br.com.techie.shoppingstore.AP003.model.UserSystem;
import br.com.techie.shoppingstore.AP003.repository.TokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TokenServiceTest {

    @InjectMocks
    private TokenService tokenService;

    @Mock
    private TokenRepository tokenRepository;

    @Mock
    private EmailService emailService;

    private UserSystem user;

    private Token token;

    @Mock
    private JwtUserDetailsService jwtUserDetailsService; 

    @BeforeEach
    void setUp() {
        user = new UserSystem(1L, "test@example.com", "testuser", "password", "password", UserSystem.Role.ROLE_CLIENT,
                LocalDateTime.now(), LocalDateTime.now(), "creator", "modifier", true, "code_verifier");

        token = new Token(1L, "token", LocalDateTime.now(), user);
    }


    @Test
    void testFindByToken() {
        when(tokenRepository.findByToken("token")).thenReturn(Optional.of(token));

        Optional<Token> result = tokenService.findByToken("token");

        assertTrue(result.isPresent());
        assertEquals(token, result.get());
    }

    @Test
    void testDeleteToken() {
        doNothing().when(tokenRepository).delete(token);

        tokenService.deleteToken(token);

        verify(tokenRepository, times(1)).delete(token);
    }
}
