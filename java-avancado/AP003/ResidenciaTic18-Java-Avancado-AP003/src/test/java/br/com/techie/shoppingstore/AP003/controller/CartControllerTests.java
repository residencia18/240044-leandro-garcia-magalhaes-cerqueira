// package br.com.techie.shoppingstore.AP003.controller;

// import br.com.techie.shoppingstore.AP003.dto.form.CartFORM;
// import br.com.techie.shoppingstore.AP003.dto.form.CartItemFORM;
// import br.com.techie.shoppingstore.AP003.dto.form.CartUpdateFORM;
// import br.com.techie.shoppingstore.AP003.dto.form.PaymentFORM;
// import br.com.techie.shoppingstore.AP003.dto.view.*;
// import br.com.techie.shoppingstore.AP003.enums.PaymentStatusEnum;
// import br.com.techie.shoppingstore.AP003.enums.PaymentTypeEnum;
// import br.com.techie.shoppingstore.AP003.service.CartService;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageImpl;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;

// import java.math.BigDecimal;
// import java.time.LocalDateTime;
// import java.util.Collections;
// import java.util.HashSet;
// import java.util.Set;

// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.anyLong;
// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @SpringBootTest
// @AutoConfigureMockMvc
// class CartControllerTests {

//     @InjectMocks
//     private CartController cartController;

//     @Mock
//     private CartService cartService;

//     @Autowired
//     private MockMvc mockMvc;

//     @Autowired
//     private ObjectMapper objectMapper;

//     private Page<CartVIEW> page;
//     private CartVIEW cartView;
//     private CartFORM cartForm;
//     private CartUpdateFORM cartUpdateForm;

//     // @BeforeEach
//     // void setUp() {
//     //     MockitoAnnotations.openMocks(this);
//     //     mockMvc = MockMvcBuilders.standaloneSetup(cartController)
//     //             .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
//     //             .build();

//     //     ProductVIEW productView = new ProductVIEW(1L, "Product Name", "Product Category", BigDecimal.valueOf(150.00), "Product Description", "http://image.url", 10, "chassis", "cpu", "OS", "chipset", "memory", "slots", "storage", "network");
//     //     CartItemVIEW cartItemView = new CartItemVIEW(1L, productView, BigDecimal.valueOf(100), 1);
//     //     Set<CartItemVIEW> cartItemsView = new HashSet<>();
//     //     cartItemsView.add(cartItemView);

//     //     CartItemFORM cartItemForm = new CartItemFORM(1L, 1);
//     //     Set<CartItemFORM> cartItemsForm = new HashSet<>();
//     //     cartItemsForm.add(cartItemForm);

//         // PaymentVIEW paymentView = new PaymentVIEW(1L, LocalDateTime.now().toString(), BigDecimal.valueOf(100), PaymentTypeEnum.CREDIT_CARD);
//         // PaymentFORM paymentForm = new PaymentFORM(1L, LocalDateTime.now(), BigDecimal.valueOf(150.00), PaymentTypeEnum.MONEY);
//         // UserSystemVIEW userView = new UserSystemVIEW(1L, "user@example.com", "User Name", null);

//         // cartView = new CartVIEW(
//         //         1L,
//         //         cartItemsView,
//         //         paymentView,
//         //         userView,
//         //         BigDecimal.valueOf(100),
//         //         1,
//         //         LocalDateTime.now(),
//         //         PaymentStatusEnum.PAID
//         // );

//     //     cartForm = new CartFORM(userView.id(), cartItemsForm);
//     //     cartUpdateForm = new CartUpdateFORM(1L, cartItemsForm, paymentForm, LocalDateTime.now(), PaymentStatusEnum.PAID);

//     //     page = new PageImpl<>(Collections.singletonList(cartView));

//     //     when(cartService.findAllPaged(any(Pageable.class))).thenReturn(page);
//     //     when(cartService.findById(anyLong())).thenReturn(cartView);
//     //     when(cartService.insert(any(CartFORM.class))).thenReturn(cartView);
//     //     when(cartService.update(any(CartUpdateFORM.class))).thenReturn(cartView);
//     // }

//     @Test
//     @DisplayName("Get all carts")
//     void getAllCarts() throws Exception {
//         try {
//             mockMvc.perform(get("/carts")
//                     .param("page", "0")  // Adicione os parâmetros de paginação
//                     .param("size", "10")
//                     .contentType(MediaType.APPLICATION_JSON))
//                     .andExpect(status().isOk())
//                     .andExpect(jsonPath("$.content").exists());
    
//             verify(cartService, times(1)).findAllPaged(any(Pageable.class));
//         } catch (Exception e) {
//             e.printStackTrace();  // Imprime o stack trace da exceção no console
//             throw e;  // Relança a exceção para falhar o teste
//         }
//     }
    

//     @Test
//     @DisplayName("Get cart by ID")
//     void getCartById() throws Exception {
//         mockMvc.perform(get("/carts/{id}", 1L)
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.id").value(1L));

//         verify(cartService, times(1)).findById(1L);
//     }

//     @Test
//     @DisplayName("Create new cart")
//     void createCart() throws Exception {
//         String cartFormJson = objectMapper.writeValueAsString(cartForm);

//         mockMvc.perform(post("/carts")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(cartFormJson))
//                 .andExpect(status().isCreated())
//                 .andExpect(jsonPath("$.id").value(1L));

//         verify(cartService, times(1)).insert(any(CartFORM.class));
//     }

//     @Test
//     @DisplayName("Update cart")
//     void updateCart() throws Exception {
//         String cartUpdateFormJson = objectMapper.writeValueAsString(cartUpdateForm);

//         mockMvc.perform(put("/carts/{id}", 1L)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(cartUpdateFormJson))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.id").value(1L));

//         verify(cartService, times(1)).update(any(CartUpdateFORM.class));
//     }

//     @Test
//     @DisplayName("Delete cart")
//     void deleteCart() throws Exception {
//         doNothing().when(cartService).delete(1L);

//         mockMvc.perform(delete("/carts/{id}", 1L)
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isNoContent());

//         verify(cartService, times(1)).delete(1L);
//     }

// }