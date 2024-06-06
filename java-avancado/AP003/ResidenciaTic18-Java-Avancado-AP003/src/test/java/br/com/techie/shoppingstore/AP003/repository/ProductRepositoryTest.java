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

import br.com.techie.shoppingstore.AP003.model.Product;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Product product;

    public Faker faker;

    private Product createProduct() {
        faker = new Faker();
        Product product = new Product();
        product.setCategory(null);
        product.setDescription(null);
        product.setName(faker.funnyName().name());
        product.setPrice(BigDecimal.valueOf(faker.random().nextDouble()));
        product.setStock(faker.number().randomDigit());
        product.setUrlImage(faker.internet().image());
        return product;
    }

    @BeforeEach
    public void setUp() {
        faker = new Faker();
        product = createProduct();
        entityManager.persistAndFlush(product);
    }

    @Test
    public void testSave() {
        // Given
        Product newProduct = createProduct();

        // When
        Product savedProduct = productRepository.save(newProduct);

        // Then
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getId()).isGreaterThan(0);
    }

    @Test
    public void testFindById() {
        // Given
        Product savedProduct = productRepository.save(product);

        // When
        Optional<Product> retrievedProduct = productRepository.findById(savedProduct.getId());

        // Then
        assertTrue(retrievedProduct.isPresent());
        assertEquals(product, retrievedProduct.get());
    }

    @Test
    public void testUpdate() {
        // Given
        Product newProduct = createProduct();
        Product savedProduct = productRepository.save(newProduct);

        // When
        savedProduct.setPrice(BigDecimal.TEN); // Changing the price
        productRepository.save(savedProduct);

        // Then
        Product updatedProduct = entityManager.find(Product.class, savedProduct.getId());
        assertEquals(BigDecimal.TEN, updatedProduct.getPrice());
    }

    @Test
    public void testDelete() {
        // Given
        Product newProduct = createProduct();
        Product savedProduct = productRepository.save(newProduct);

        // When
        productRepository.delete(savedProduct);

        // Then
        assertFalse(productRepository.existsById(savedProduct.getId()));
    }
}

