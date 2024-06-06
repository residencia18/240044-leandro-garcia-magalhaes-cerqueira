package br.com.techie.shoppingstore.AP003.service;

import br.com.techie.shoppingstore.AP003.dto.form.CartFORM;
import br.com.techie.shoppingstore.AP003.dto.form.CartUpdateFORM;
import br.com.techie.shoppingstore.AP003.dto.view.CartVIEW;
import br.com.techie.shoppingstore.AP003.enums.PaymentStatusEnum;
import br.com.techie.shoppingstore.AP003.infra.exception.ResourceNotFoundException;
import br.com.techie.shoppingstore.AP003.mapper.forms.CartFormMapper;
import br.com.techie.shoppingstore.AP003.mapper.updates.CartUpdateMapper;
import br.com.techie.shoppingstore.AP003.mapper.views.CartViewMapper;
import br.com.techie.shoppingstore.AP003.model.Cart;
import br.com.techie.shoppingstore.AP003.repository.CartRepository;
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
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @InjectMocks
    private CartService cartService;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartViewMapper cartViewMapper;

    @Mock
    private CartFormMapper cartFormMapper;

    @Mock
    private CartUpdateMapper cartUpdateMapper;

    private Cart cart;
    private CartVIEW cartView;
    private CartFORM cartForm;
    private CartUpdateFORM cartUpdateForm;
    private Pageable pageable;
    private Page<Cart> cartPage;

    @BeforeEach
    public void setUp() {
        cart = new Cart();
        cart.setId(1L);
        cart.setTotalPrice(BigDecimal.valueOf(100));
        cart.setTotalItems(2);
        cart.setPurchaseDate(LocalDateTime.now());
        cart.setStatus(PaymentStatusEnum.PENDING);

        cartView = new CartVIEW(
                1L,
                Set.of(), // Add appropriate CartItemVIEW instances
                null, // Add appropriate PaymentVIEW instance
                null, // Add appropriate UserSystemVIEW instance
                BigDecimal.valueOf(100),
                2,
                LocalDateTime.now().toString(),
                PaymentStatusEnum.PENDING
        );

        cartForm = new CartFORM(
                1L,
                Set.of() // Add appropriate CartItemFORM instances
        );

        cartUpdateForm = new CartUpdateFORM(
                1L,
                Set.of(), // Add appropriate CartItemFORM instances
                null, // Add appropriate PaymentFORM instance
                LocalDateTime.now(),
                PaymentStatusEnum.PENDING
        );

        pageable = PageRequest.of(0, 10);
        cartPage = new PageImpl<>(Collections.singletonList(cart), pageable, 1);
    }

    @Test
    public void testFindAllPaged() {
        when(cartRepository.findAll(pageable)).thenReturn(cartPage);
        when(cartViewMapper.map(cart)).thenReturn(cartView);

        Page<CartVIEW> result = cartService.findAllPaged(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(cartRepository, times(1)).findAll(pageable);
        verify(cartViewMapper, times(1)).map(cart);
    }

    @Test
    public void testFindAllPaidPaged() {
        when(cartRepository.findAllByStatus(PaymentStatusEnum.PAID, pageable)).thenReturn(cartPage);
        when(cartViewMapper.map(cart)).thenReturn(cartView);

        Page<CartVIEW> result = cartService.findAllPaidPaged(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(cartRepository, times(1)).findAllByStatus(PaymentStatusEnum.PAID, pageable);
        verify(cartViewMapper, times(1)).map(cart);
    }

    @Test
    public void testFindAllUnpaidPaged() {
        when(cartRepository.findAllByStatus(PaymentStatusEnum.PENDING, pageable)).thenReturn(cartPage);
        when(cartViewMapper.map(cart)).thenReturn(cartView);

        Page<CartVIEW> result = cartService.findAllUnpaidPaged(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(cartRepository, times(1)).findAllByStatus(PaymentStatusEnum.PENDING, pageable);
        verify(cartViewMapper, times(1)).map(cart);
    }

    @Test
    public void testFindById() {
        when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));
        when(cartViewMapper.map(cart)).thenReturn(cartView);

        CartVIEW result = cartService.findById(1L);

        assertNotNull(result);
        assertEquals(cartView, result);
        verify(cartRepository, times(1)).findById(1L);
        verify(cartViewMapper, times(1)).map(cart);
    }

    @Test
    public void testFindByIdNotFound() {
        when(cartRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> cartService.findById(1L));
        verify(cartRepository, times(1)).findById(1L);
    }

    @Test
    public void testInsert() {
        when(cartFormMapper.map(cartForm)).thenReturn(cart);
        when(cartRepository.save(cart)).thenReturn(cart);
        when(cartViewMapper.map(cart)).thenReturn(cartView);

        CartVIEW result = cartService.insert(cartForm);

        assertNotNull(result);
        assertEquals(cartView, result);
        verify(cartFormMapper, times(1)).map(cartForm);
        verify(cartRepository, times(1)).save(cart);
        verify(cartViewMapper, times(1)).map(cart);
    }

    @Test
    public void testUpdate() {
        when(cartRepository.findById(cartUpdateForm.cart_id())).thenReturn(Optional.of(cart));
        when(cartUpdateMapper.map(cartUpdateForm, cart)).thenReturn(cart);
        when(cartRepository.save(cart)).thenReturn(cart);
        when(cartViewMapper.map(cart)).thenReturn(cartView);

        CartVIEW result = cartService.update(cartUpdateForm);

        assertNotNull(result);
        assertEquals(cartView, result);
        verify(cartRepository, times(1)).findById(cartUpdateForm.cart_id());
        verify(cartUpdateMapper, times(1)).map(cartUpdateForm, cart);
        verify(cartRepository, times(1)).save(cart);
        verify(cartViewMapper, times(1)).map(cart);
    }

    @Test
    public void testUpdateNotFound() {
        when(cartRepository.findById(cartUpdateForm.cart_id())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> cartService.update(cartUpdateForm));
        verify(cartRepository, times(1)).findById(cartUpdateForm.cart_id());
    }

    @Test
    public void testDelete() {
        doNothing().when(cartRepository).deleteById(1L);

        cartService.delete(1L);

        verify(cartRepository, times(1)).deleteById(1L);
    }
}
