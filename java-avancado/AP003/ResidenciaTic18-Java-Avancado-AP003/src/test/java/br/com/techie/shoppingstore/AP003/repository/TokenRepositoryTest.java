package br.com.techie.shoppingstore.AP003.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.github.javafaker.Faker;

import br.com.techie.shoppingstore.AP003.model.Token;
import br.com.techie.shoppingstore.AP003.model.UserSystem;


@DataJpaTest
public class TokenRepositoryTest {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Faker faker;
    private UserSystem userSystem;
    private Token token;

    private UserSystem createUserSystem() {
        faker = new Faker();
        UserSystem userSystem = new UserSystem();
        userSystem.setEmail(faker.internet().emailAddress());
        userSystem.setUsername(faker.name().username());
        userSystem.setPassword("@Test123");
        userSystem.setPasswordConfirm("@Test123");
        userSystem.setRole(UserSystem.Role.ROLE_CLIENT);
        userSystem.setActive(faker.bool().bool());
        return userSystem;
    }

    @BeforeEach
    public void setUp() {

        faker = new Faker();
        // Criar e salvar UserSystem
        userSystem = createUserSystem();
        userSystem = entityManager.persist(userSystem);
        entityManager.flush();

        token = new Token("token123", LocalDateTime.now(), userSystem);
        entityManager.persistAndFlush(token);
    }


    @Test
    public void testFindById() {
        // When
        Optional<Token> retrievedToken = tokenRepository.findById(token.getId());

        // Then
        assertTrue(retrievedToken.isPresent());
        assertEquals(token, retrievedToken.get());
    }

    @Test
    public void testUpdate() {
        // Given
        token.setToken("updatedToken");

        // When
        tokenRepository.save(token);

        // Then
        Token updatedToken = entityManager.find(Token.class, token.getId());
        assertEquals("updatedToken", updatedToken.getToken());
    }

    @Test
    public void testDelete() {
        // When
        tokenRepository.delete(token);

        // Then
        assertFalse(tokenRepository.existsById(token.getId()));
    }
}
