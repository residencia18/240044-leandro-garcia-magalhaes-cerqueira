package br.com.techie.shoppingstore.AP003.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.github.javafaker.Faker;

import br.com.techie.shoppingstore.AP003.model.Category;

@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Category category;

    public Faker faker;

    private Category createCategory() {
        faker = new Faker();
        Category category = new Category();
        category.setName(faker.commerce().material());
        return category;
    }


    @BeforeEach
    public void setUp() {
        faker = new Faker();
        category = createCategory();
        entityManager.persistAndFlush(category);
    }


    @Test
    public void testSave() {
        // Given
        Category newCategory = createCategory();

        // When
        Category savedCategory = categoryRepository.save(newCategory);

        // Then
        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getId()).isGreaterThan(0);
    }

    @Test
    public void testFindById() {
        // Given
        Category savedCategory = categoryRepository.save(category);

        // When
        Optional<Category> retrievedCategory = categoryRepository.findById(savedCategory.getId());

        // Then
        assertTrue(retrievedCategory.isPresent());
        assertEquals(category, retrievedCategory.get());
    }

    @Test
    public void testUpdate() {
        // Given
        Category newCategory = createCategory();
        Category savedCategory = categoryRepository.save(newCategory);

        // When
        savedCategory.setName("Updated Category"); // Changing the category name
        categoryRepository.save(savedCategory);

        // Then
        Category updatedCategory = entityManager.find(Category.class, savedCategory.getId());
        assertEquals("Updated Category", updatedCategory.getName());
    }

    @Test
    public void testDelete() {
        // Given
        Category newCategory = createCategory();
        Category savedCategory = categoryRepository.save(newCategory);

        // When
        categoryRepository.delete(savedCategory);

        // Then
        assertFalse(categoryRepository.existsById(savedCategory.getId()));
    }
}
