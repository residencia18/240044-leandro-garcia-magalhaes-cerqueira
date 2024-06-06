// package br.com.techie.shoppingstore.AP003.controller;

// import br.com.techie.shoppingstore.AP003.dto.form.CategoryFORM;
// import br.com.techie.shoppingstore.AP003.dto.form.CategoryUpdateFORM;
// import br.com.techie.shoppingstore.AP003.dto.view.CategoryVIEW;
// import br.com.techie.shoppingstore.AP003.service.CategoryService;
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

// import java.util.Collections;

// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.anyLong;
// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// class CategoryControllerTests {

//     @InjectMocks
//     private CategoryController categoryController;

//     @Mock
//     private CategoryService categoryService;

//     @Autowired
//     private MockMvc mockMvc;

//     @Autowired
//     private ObjectMapper objectMapper;

//     private Page<CategoryVIEW> page;
//     private CategoryVIEW categoryView;
//     private CategoryFORM categoryForm;
//     private CategoryUpdateFORM categoryUpdateForm;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);
//         mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();

//         categoryView = new CategoryVIEW(1L, "Electronics");
//         categoryForm = new CategoryFORM("Electronics");
//         categoryUpdateForm = new CategoryUpdateFORM(1L, "Electronics");

//         page = new PageImpl<>(Collections.singletonList(categoryView));

//         when(categoryService.findAllPaged(any(Pageable.class))).thenReturn(page);
//         when(categoryService.findById(anyLong())).thenReturn(categoryView);
//         when(categoryService.insert(any(CategoryFORM.class))).thenReturn(categoryView);
//         when(categoryService.update(any(CategoryUpdateFORM.class))).thenReturn(categoryView);
//     }

//     @Test
//     @DisplayName("Get all categories")
//     void getAllCategories() throws Exception {
//         mockMvc.perform(get("/categories")
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.content").exists());

//         verify(categoryService, times(1)).findAllPaged(any(Pageable.class));
//     }

//     @Test
//     @DisplayName("Get category by ID")
//     void getCategoryById() throws Exception {
//         mockMvc.perform(get("/categories/{id}", 1L)
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.id").value(1L));

//         verify(categoryService, times(1)).findById(1L);
//     }

//     @Test
//     @DisplayName("Create new category")
//     void createCategory() throws Exception {
//         String categoryFormJson = objectMapper.writeValueAsString(categoryForm);

//         mockMvc.perform(post("/categories")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(categoryFormJson))
//                 .andExpect(status().isCreated())
//                 .andExpect(jsonPath("$.id").value(1L));

//         verify(categoryService, times(1)).insert(any(CategoryFORM.class));
//     }

//     @Test
//     @DisplayName("Update category")
//     void updateCategory() throws Exception {
//         String categoryUpdateFormJson = objectMapper.writeValueAsString(categoryUpdateForm);

//         mockMvc.perform(put("/categories/{id}", 1L)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(categoryUpdateFormJson))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.id").value(1L));

//         verify(categoryService, times(1)).update(any(CategoryUpdateFORM.class));
//     }

//     @Test
//     @DisplayName("Delete category")
//     void deleteCategory() throws Exception {
//         doNothing().when(categoryService).delete(1L);

//         mockMvc.perform(delete("/categories/{id}", 1L)
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isNoContent());

//         verify(categoryService, times(1)).delete(1L);
//     }
// }
