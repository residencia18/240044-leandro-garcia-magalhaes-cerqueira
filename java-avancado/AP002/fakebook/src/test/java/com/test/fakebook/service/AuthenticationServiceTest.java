package com.test.fakebook.service;


import com.github.javafaker.Faker;
import com.test.fakebook.dto.LoginRequest;
import com.test.fakebook.dto.LoginResponse;
import com.test.fakebook.dto.NewPasswordRequest;
import com.test.fakebook.dto.RegisterRequest;
import com.test.fakebook.entity.User;
import com.test.fakebook.mapper.UserMapper;
import com.test.fakebook.repository.UserRepository;
import com.test.fakebook.security.JwtProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtProvider jwtProvider;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private AuthenticationService authenticationService;

    private final Faker faker = new Faker();

   @Test
    public void testRegister_ValidUser() {
        // Given
        String email = faker.internet().emailAddress();
        String username = faker.name().username();
        String password = "@Tiscuvhe5434";
        RegisterRequest registerRequest = new RegisterRequest(username, password, email);
        User user = new User();
        when(userMapper.fromRegisterRequest(registerRequest)).thenReturn(user);

        // When
        boolean result = authenticationService.register(registerRequest);


        // Then
        assertTrue(result);
        verify(userRepository, times(1)).save(user);
    }



    @Test
    public void testRegister_InvalidPassword() {
        // Given
        RegisterRequest registerRequest = new RegisterRequest(faker.name().username(), faker.internet().password(0, 7, false), faker.internet().emailAddress());

        // When
        boolean result = authenticationService.register(registerRequest);

        // Then
        assertFalse(result);
        verify(userRepository, never()).save(any());
    }

    @Test
    public void testLogin_ValidCredentials() {
        // Given
        String username = faker.name().username();
        String password = faker.internet().password();
        LoginRequest loginRequest = new LoginRequest(username, password);
        when(authenticationManager.authenticate(any())).thenReturn(null);
        when(jwtProvider.generateToken(any())).thenReturn("jwt_token");

        // When
        LoginResponse response = authenticationService.login(loginRequest);

        // Then
        assertNotNull(response);
        assertEquals("jwt_token", response.token());
    }

    @Test
    public void testChangePassword_ValidPassword() {
        // Given
        String newPassword = "@LEsfj3454b";
        NewPasswordRequest newPasswordRequest = new NewPasswordRequest(newPassword);

        // When
        authenticationService.changePassword(newPasswordRequest);

        // Then: No exception thrown
    }

    @Test
    public void testChangePassword_InvalidPassword() {
        // Given
        String weakPassword = "WeakPwd";
        NewPasswordRequest newPasswordRequest = new NewPasswordRequest(weakPassword);

        // When/Then: Verify IllegalArgumentException thrown
        assertThrows(IllegalArgumentException.class, () -> authenticationService.changePassword(newPasswordRequest));
    }
}
