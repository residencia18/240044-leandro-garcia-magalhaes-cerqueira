package br.com.techie.shoppingstore.AP003.repository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.github.javafaker.Faker;

import br.com.techie.shoppingstore.AP003.model.CartItem;


@DataJpaTest
public class CartItemRepositoryTest {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private TestEntityManager entityManager;

    private CartItem cartItem;

    public Faker faker;

    private CartItem createCartItem() {
        faker = new Faker();
        CartItem cartItem = new CartItem();
        cartItem.setPrice(BigDecimal.valueOf(faker.random().nextDouble()));
        cartItem.setQuantity(faker.number().randomDigit());
        cartItem.setProduct(null);
        return cartItem;
    }

    @BeforeEach
    public void setUp() {
        faker = new Faker();
        cartItem = createCartItem();
        entityManager.persistAndFlush(cartItem);
    }

    @Test
    public void testSave() {
        // Given
        CartItem newCartItem = createCartItem();

        // When
        CartItem savedCartItem = cartItemRepository.save(newCartItem);

        // Then
        assertThat(savedCartItem).isNotNull();
        assertThat(savedCartItem.getId()).isGreaterThan(0);
    }

    @Test
    public void testFindById() {
        // Given
        CartItem savedCartItem = cartItemRepository.save(cartItem);

        // When
        Optional<CartItem> retrievedCartItem = cartItemRepository.findById(savedCartItem.getId());

        // Then
        assertTrue(retrievedCartItem.isPresent());
        assertEquals(cartItem, retrievedCartItem.get());
    }

    @Test
    public void testUpdate() {
        // Given
        CartItem newCartItem = createCartItem();
        CartItem savedCartItem = cartItemRepository.save(newCartItem);

        // When
        savedCartItem.setPrice(BigDecimal.TEN); // Changing the price
        cartItemRepository.save(savedCartItem);

        // Then
        CartItem updatedCartItem = entityManager.find(CartItem.class, savedCartItem.getId());
        assertEquals(BigDecimal.TEN, updatedCartItem.getPrice());
    }

    @Test
    public void testDelete() {
        // Given
        CartItem newCartItem = createCartItem();
        CartItem savedCartItem = cartItemRepository.save(newCartItem);

        // When
        cartItemRepository.delete(savedCartItem);

        // Then
        assertFalse(cartItemRepository.existsById(savedCartItem.getId()));
    }
}

