package br.com.techie.shoppingstore.AP003.service;

import br.com.techie.shoppingstore.AP003.dto.form.CategoryFORM;
import br.com.techie.shoppingstore.AP003.dto.form.CategoryUpdateFORM;
import br.com.techie.shoppingstore.AP003.dto.view.CategoryVIEW;
import br.com.techie.shoppingstore.AP003.infra.exception.ResourceNotFoundException;
import br.com.techie.shoppingstore.AP003.mapper.forms.CategoryFormMapper;
import br.com.techie.shoppingstore.AP003.mapper.updates.CategoryUpdateMapper;
import br.com.techie.shoppingstore.AP003.mapper.views.CategoryViewMapper;
import br.com.techie.shoppingstore.AP003.model.Category;
import br.com.techie.shoppingstore.AP003.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryFormMapper categoryFormMapper;

    @Mock
    private CategoryViewMapper categoryViewMapper;

    @Mock
    private CategoryUpdateMapper categoryUpdateMapper;

    private Category category;
    private CategoryVIEW categoryView;
    private CategoryFORM categoryForm;
    private CategoryUpdateFORM categoryUpdateForm;
    private Pageable pageable;
    private Page<Category> categoryPage;

    @BeforeEach
    public void setUp() {
        category = new Category();
        category.setId(1L);
        category.setName("Electronics");

        categoryView = new CategoryVIEW(
                1L,
                "Electronics"
        );

        categoryForm = new CategoryFORM(
                "Electronics"
        );

        categoryUpdateForm = new CategoryUpdateFORM(
                1L,
                "Electronics"
        );

        pageable = PageRequest.of(0, 10);
        categoryPage = new PageImpl<>(Collections.singletonList(category), pageable, 1);
    }

    @Test
    public void testFindAllPaged() {
        when(categoryRepository.findAll(pageable)).thenReturn(categoryPage);
        when(categoryViewMapper.map(category)).thenReturn(categoryView);

        Page<CategoryVIEW> result = categoryService.findAllPaged(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(categoryRepository, times(1)).findAll(pageable);
        verify(categoryViewMapper, times(1)).map(category);
    }

    @Test
    public void testFindById() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categoryViewMapper.map(category)).thenReturn(categoryView);

        CategoryVIEW result = categoryService.findById(1L);

        assertNotNull(result);
        assertEquals(categoryView, result);
        verify(categoryRepository, times(1)).findById(1L);
        verify(categoryViewMapper, times(1)).map(category);
    }

    @Test
    public void testFindByIdNotFound() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> categoryService.findById(1L));
        verify(categoryRepository, times(1)).findById(1L);
    }

    @Test
    public void testInsert() {
        when(categoryFormMapper.map(categoryForm)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(category);
        when(categoryViewMapper.map(category)).thenReturn(categoryView);

        CategoryVIEW result = categoryService.insert(categoryForm);

        assertNotNull(result);
        assertEquals(categoryView, result);
        verify(categoryFormMapper, times(1)).map(categoryForm);
        verify(categoryRepository, times(1)).save(category);
        verify(categoryViewMapper, times(1)).map(category);
    }

    @Test
    public void testUpdate() {
        when(categoryRepository.findById(categoryUpdateForm.category_id())).thenReturn(Optional.of(category));
        when(categoryUpdateMapper.map(categoryUpdateForm, category)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(category);
        when(categoryViewMapper.map(category)).thenReturn(categoryView);

        CategoryVIEW result = categoryService.update(categoryUpdateForm);

        assertNotNull(result);
        assertEquals(categoryView, result);
        verify(categoryRepository, times(1)).findById(categoryUpdateForm.category_id());
        verify(categoryUpdateMapper, times(1)).map(categoryUpdateForm, category);
        verify(categoryRepository, times(1)).save(category);
        verify(categoryViewMapper, times(1)).map(category);
    }

    @Test
    public void testUpdateNotFound() {
        when(categoryRepository.findById(categoryUpdateForm.category_id())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> categoryService.update(categoryUpdateForm));
        verify(categoryRepository, times(1)).findById(categoryUpdateForm.category_id());
    }

    @Test
    public void testDelete() {
        doNothing().when(categoryRepository).deleteById(1L);

        categoryService.delete(1L);

        verify(categoryRepository, times(1)).deleteById(1L);
    }
}
