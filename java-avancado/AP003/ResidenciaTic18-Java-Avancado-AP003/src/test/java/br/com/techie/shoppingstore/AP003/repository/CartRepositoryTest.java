package br.com.techie.shoppingstore.AP003.repository;

import br.com.techie.shoppingstore.AP003.enums.PaymentStatusEnum;
import br.com.techie.shoppingstore.AP003.model.Cart;
import br.com.techie.shoppingstore.AP003.model.Payment;
import br.com.techie.shoppingstore.AP003.model.UserSystem;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Faker faker;
    private Cart cart;
    private UserSystem userSystem;

    private Cart createCart(UserSystem userSystem) {
        faker = new Faker();
        Cart cart = new Cart();
        cart.setTotalPrice(BigDecimal.valueOf(faker.random().nextDouble()));
        cart.setTotalItems(faker.random().nextInt(1, 100));
        Date date = faker.date().between(Date.from(Instant.now()), Date.from(Instant.now().plusSeconds(172800L)));
        cart.setPurchaseDate(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        cart.setStatus(PaymentStatusEnum.PENDING);
        cart.setPayment(new Payment());
        cart.setUser(userSystem);
        cart.setItems(new HashSet<>());
        return cart;
    }

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
        // Criar e associar o Cart ao UserSystem salvo
        cart = createCart(userSystem);
        cart = entityManager.merge(cart);
        entityManager.flush();
    }

    @Test
    public void testSave() {
        // Given
        // Use o mesmo UserSystem persistido no setUp()
        Cart newCart = createCart(userSystem);

        // When
        Cart savedCart = cartRepository.save(newCart);

        // Then
        assertThat(savedCart).isNotNull();
        assertThat(savedCart.getId()).isPositive();
    }

    @Test
    public void testFindById() {
        // Given
        // Não é necessário criar um novo UserSystem, pois já foi criado no setUp()

        // When
        Optional<Cart> retrievedCart = cartRepository.findById(cart.getId());

        // Then
        assertTrue(retrievedCart.isPresent());
        assertEquals(cart, retrievedCart.get());
    }

    @Test
    public void testUpdate() {
        // Given
        // Use o mesmo UserSystem persistido no setUp()
        Cart newCart = createCart(userSystem);
        Cart savedCart = cartRepository.save(newCart);

        // When
        savedCart.setTotalPrice(BigDecimal.TEN); // Alterando o preço total para um valor diferente
        cartRepository.save(savedCart);

        // Then
        Cart updatedCart = entityManager.find(Cart.class, savedCart.getId());
        assertEquals(BigDecimal.TEN, updatedCart.getTotalPrice());
    }

    @Test
    public void testDelete() {
        // Given
        // Use o mesmo UserSystem persistido no setUp()
        Cart newCart = createCart(userSystem);
        Cart savedCart = cartRepository.save(newCart);

        // When
        cartRepository.delete(savedCart);

        // Then
        assertFalse(cartRepository.existsById(savedCart.getId()));
    }


}
