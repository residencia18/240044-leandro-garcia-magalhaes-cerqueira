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

import com.github.javafaker.Faker;

import br.com.techie.shoppingstore.AP003.model.UserSystem;


@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserSystemRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    private UserSystem user;

    public Faker faker;

    private UserSystem criaUsuario() {
        
        faker = new Faker();
        UserSystem user = new UserSystem();
        user.setActive(faker.bool().bool());
        user.setPassword("@Test123");
        user.setPasswordConfirm("@Test123");
        user.setEmail(faker.internet().emailAddress());
        user.setUsername(faker.name().username());
        return user;
    }



    @BeforeEach
    public void setUp() {
        faker = new Faker();
        user = criaUsuario();
        entityManager.persistAndFlush(user);
    }


    @Test
    public void testSave(){

        //Given
        UserSystem newUser = criaUsuario();

        //When
        UserSystem savedUser = userRepository.save(newUser);

        //Then
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);

        System.out.println(newUser);

    }

    @Test
    public void testFindById(){
        //  Given
        UserSystem savedUser = userRepository.save(user);

        //  When
        Optional<UserSystem> retrievedUsuario = userRepository.findById(savedUser.getId());

        // Then
        assertEquals(user, retrievedUsuario.get());
        assertTrue(retrievedUsuario.isPresent());

        System.out.println(retrievedUsuario);

    }


}
