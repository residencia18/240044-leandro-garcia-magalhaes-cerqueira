package com.test.fakebook.repository;

import com.test.fakebook.entity.User;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @Test
    public void testSaveUser() {
        // Given
        Faker faker = new Faker();
        User user = new User();
        user.setEmail(faker.internet().emailAddress());
        user.setUsername(faker.name().username());
        user.setPassword(faker.internet().password());

        // Stubbing the repository method
        when(userRepository.save(user)).thenReturn(user);

        // When
        User savedUser = userRepository.save(user);

        // Then
        assertNotNull(savedUser);
        assertEquals(user.getEmail(), savedUser.getEmail());
        assertEquals(user.getUsername(), savedUser.getUsername());
        assertEquals(user.getPassword(), savedUser.getPassword());
        verify(userRepository, times(1)).save(user);
    }
}
