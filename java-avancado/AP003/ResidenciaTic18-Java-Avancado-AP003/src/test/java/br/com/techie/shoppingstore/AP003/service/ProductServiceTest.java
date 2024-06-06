package br.com.techie.shoppingstore.AP003.service;

import br.com.techie.shoppingstore.AP003.dto.form.ProductFORM;
import br.com.techie.shoppingstore.AP003.dto.form.ProductUpdateFORM;
import br.com.techie.shoppingstore.AP003.dto.view.ProductVIEW;
import br.com.techie.shoppingstore.AP003.infra.exception.ResourceNotFoundException;
import br.com.techie.shoppingstore.AP003.mapper.forms.ProductFormMapper;
import br.com.techie.shoppingstore.AP003.mapper.updates.ProductUpdateMapper;
import br.com.techie.shoppingstore.AP003.mapper.views.ProductViewMapper;
import br.com.techie.shoppingstore.AP003.model.Category;
import br.com.techie.shoppingstore.AP003.model.Product;
import br.com.techie.shoppingstore.AP003.model.Score;
import br.com.techie.shoppingstore.AP003.repository.CategoryRepository;
import br.com.techie.shoppingstore.AP003.repository.ProductRepository;
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

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductFormMapper productFormMapper;

    @Mock
    private ProductViewMapper productViewMapper;

    @Mock
    private ProductUpdateMapper productUpdateMapper;

    private Product product;
    private Category category;
    private Set<Score> scores;
    private ProductFORM productForm;
    private ProductUpdateFORM productUpdateForm;
    private ProductVIEW productView;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setId(1L);
        category.setName("Servers");

        scores = null;
        
        product = new Product(1L, "Dell PowerEdge R740", BigDecimal.valueOf(4999.99), "A powerful Dell server", 5, 
                "url/dell_poweredge_r740.png", "Rack", "Intel Xeon Gold", "Windows Server 2019", 
                "Intel C620", "64GB DDR4", "16 DIMM slots", "4TB SSD", "10Gb Ethernet", category, scores);

        productForm = new ProductFORM("Dell PowerEdge R740", 1L, BigDecimal.valueOf(4999.99), "A powerful Dell server", 5, 
                "url/dell_poweredge_r740.png", "Rack", "Intel Xeon Gold", "Windows Server 2019", 
                "Intel C620", "64GB DDR4", "16 DIMM slots", "4TB SSD", "10Gb Ethernet");

        productUpdateForm = new ProductUpdateFORM(1L, "Dell PowerEdge R740 Updated", 1L, BigDecimal.valueOf(5999.99), 
                "An updated powerful Dell server", 4, "url/dell_poweredge_r740_updated.png", "Rack", "Intel Xeon Platinum", 
                "Windows Server 2022", "Intel C621", "128GB DDR4", "16 DIMM slots", "8TB SSD", "10Gb Ethernet");

        productView = new ProductVIEW(1L, "Dell PowerEdge R740", "Servers", BigDecimal.valueOf(4999.99), 
                "A powerful Dell server", 4.5, "url/dell_poweredge_r740.png", 5, "Rack", "Intel Xeon Gold", 
                "Windows Server 2019", "Intel C620", "64GB DDR4", "16 DIMM slots", "4TB SSD", "10Gb Ethernet");
    }

    @Test
    public void testFindAllPaged() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> page = new PageImpl<>(Collections.singletonList(product));

        when(productRepository.findAll(pageable)).thenReturn(page);
        when(productViewMapper.map(product)).thenReturn(productView);

        Page<ProductVIEW> result = productService.findAllPaged(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(productRepository, times(1)).findAll(pageable);
        verify(productViewMapper, times(1)).map(product);
    }

    @Test
    public void testFindById() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productViewMapper.map(product)).thenReturn(productView);

        ProductVIEW result = productService.findById(1L);

        assertNotNull(result);
        assertEquals(productView, result);
        verify(productRepository, times(1)).findById(1L);
        verify(productViewMapper, times(1)).map(product);
    }

    @Test
    public void testFindByIdNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.findById(1L));
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    public void testInsert() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(productFormMapper.map(productForm)).thenReturn(product);
        when(productViewMapper.map(product)).thenReturn(productView);

        ProductVIEW result = productService.insert(productForm);

        assertNotNull(result);
        assertEquals(productView, result);
        verify(categoryRepository, times(1)).findById(1L);
        verify(productFormMapper, times(1)).map(productForm);
        verify(productRepository, times(1)).save(product);
        verify(productViewMapper, times(1)).map(product);
    }

    @Test
    public void testInsertCategoryNotFound() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> productService.insert(productForm));
        verify(categoryRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdate() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(productUpdateMapper.map(productUpdateForm, product)).thenReturn(product);
        when(productViewMapper.map(product)).thenReturn(productView);

        ProductVIEW result = productService.update(productUpdateForm);

        assertNotNull(result);
        assertEquals(productView, result);
        verify(productRepository, times(1)).findById(1L);
        verify(categoryRepository, times(1)).findById(1L);
        verify(productUpdateMapper, times(1)).map(productUpdateForm, product);
        verify(productRepository, times(1)).save(product);
        verify(productViewMapper, times(1)).map(product);
    }

    @Test
    public void testUpdateProductNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> productService.update(productUpdateForm));
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateCategoryNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> productService.update(productUpdateForm));
        verify(productRepository, times(1)).findById(1L);
        verify(categoryRepository, times(1)).findById(1L);
    }

    @Test
    public void testDelete() {
        doNothing().when(productRepository).deleteById(1L);

        productService.delete(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }
}
