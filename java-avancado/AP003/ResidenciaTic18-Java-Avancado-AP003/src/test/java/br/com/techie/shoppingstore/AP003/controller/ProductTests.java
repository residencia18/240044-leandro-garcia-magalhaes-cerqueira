// package br.com.techie.shoppingstore.AP003.controller;

// import br.com.techie.shoppingstore.AP003.dto.form.ProductFORM;
// import br.com.techie.shoppingstore.AP003.dto.form.ProductUpdateFORM;
// import br.com.techie.shoppingstore.AP003.dto.view.ProductVIEW;
// import br.com.techie.shoppingstore.AP003.service.ProductService;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageImpl;
// import org.springframework.data.domain.Pageable;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;

// import java.math.BigDecimal;
// import java.util.Collections;

// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.anyLong;
// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// class
// ProductControllerTest {

//     @InjectMocks
//     private ProductController productController;

//     @Mock
//     private ProductService productService;

//     @Autowired
//     private MockMvc mockMvc;

//     @Autowired
//     private ObjectMapper objectMapper;

//     private Page<ProductVIEW> page;
//     private ProductVIEW productView;
//     private ProductFORM productForm;
//     private ProductUpdateFORM productUpdateForm;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);
//         mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

//         productView = new ProductVIEW(1L, "Product Name", "Product Category", BigDecimal.valueOf(150.00), "Product Description", 4.0, "http://image.url" ,10, "chassis", "cpu", "OS", "chipset", "memory", "slots", "storage", "network");
//         productForm = new ProductFORM("Product Name", 1L, BigDecimal.valueOf(100.00), "Product Description", 10, "http://image.url", "chassis", "cpu", "OS", "chipset", "memory", "slots", "storage", "network");
//         productUpdateForm = new ProductUpdateFORM(1L, "Updated Product Name", 1L, BigDecimal.valueOf(150.00), "Updated Product Description", 5, "http://updated.image.url", "updated chassis", "updated cpu", "updated OS", "updated chipset", "updated memory", "updated slots", "updated storage", "updated network");

//         page = new PageImpl<>(Collections.singletonList(productView));

//         when(productService.findAllPaged(any(Pageable.class))).thenReturn(page);
//         when(productService.findById(anyLong())).thenReturn(productView);
//         when(productService.insert(any(ProductFORM.class))).thenReturn(productView);
//         when(productService.update(any(ProductUpdateFORM.class))).thenReturn(productView);
//     }

//     @Test
//     @DisplayName("Get all products")
//     void getAllProducts() throws Exception {
//         mockMvc.perform(get("/products")
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.content").exists());

//         verify(productService, times(1)).findAllPaged(any(Pageable.class));
//     }

//     @Test
//     @DisplayName("Get product by ID")
//     void getProductById() throws Exception {
//         mockMvc.perform(get("/products/{id}", 1L)
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.id").value(1L));

//         verify(productService, times(1)).findById(1L);
//     }

//     @Test
//     @DisplayName("Create new product")
//     void createProduct() throws Exception {
//         String productFormJson = objectMapper.writeValueAsString(productForm);

//         mockMvc.perform(post("/products")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(productFormJson))
//                 .andExpect(status().isCreated())
//                 .andExpect(jsonPath("$.id").value(1L));

//         verify(productService, times(1)).insert(any(ProductFORM.class));
//     }

//     @Test
//     @DisplayName("Update product")
//     void updateProduct() throws Exception {
//         String productUpdateFormJson = objectMapper.writeValueAsString(productUpdateForm);

//         mockMvc.perform(put("/products/{id}", 1L)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(productUpdateFormJson))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.id").value(1L));

//         verify(productService, times(1)).update(any(ProductUpdateFORM.class));
//     }

//     @Test
//     @DisplayName("Delete product")
//     void deleteProduct() throws Exception {
//         doNothing().when(productService).delete(1L);

//         mockMvc.perform(delete("/products/{id}", 1L)
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isNoContent());

//         verify(productService, times(1)).delete(1L);
//     }
// }
