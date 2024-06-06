package br.com.techie.shoppingstore.AP003.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import br.com.techie.shoppingstore.AP003.model.UserSystem;
import br.com.techie.shoppingstore.AP003.model.UserSystem.Role;

@DataJpaTest
public class UserSystemRepositoryTest {

    @Autowired
    private UserSystemRepository userSystemRepository;

    @Autowired
    private TestEntityManager entityManager;

    private UserSystem userSystem;

    @BeforeEach
    public void setUp() {
        userSystem = new UserSystem();
        userSystem.setEmail("test@example.com");
        userSystem.setUsername("testuser");
        userSystem.setPassword("password");
        userSystem.setRole(Role.ROLE_CLIENT);
        userSystem.setActive(true);

        entityManager.persistAndFlush(userSystem);
    }

    @Test
    public void testFindByEmail() {
        // Given
        String email = "test@example.com";

        // When
        Optional<UserSystem> foundUserOptional = userSystemRepository.findByEmail(email);

        // Then
        assertTrue(foundUserOptional.isPresent());
        assertEquals(userSystem, foundUserOptional.get());
    }

    @Test
    public void testFindRoleByEmail() {
        // Given
        String email = "test@example.com";

        // When
        Role role = userSystemRepository.findRoleByEmail(email);

        // Then
        assertThat(role).isEqualTo(userSystem.getRole());
    }
}

