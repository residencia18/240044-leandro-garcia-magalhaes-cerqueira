package br.com.techie.shoppingstore.AP003.service;

import br.com.techie.shoppingstore.AP003.dto.form.PaymentFORM;
import br.com.techie.shoppingstore.AP003.dto.view.PaymentVIEW;
import br.com.techie.shoppingstore.AP003.enums.PaymentTypeEnum;
import br.com.techie.shoppingstore.AP003.infra.exception.ResourceNotFoundException;
import br.com.techie.shoppingstore.AP003.mapper.forms.PaymentFormMapper;
import br.com.techie.shoppingstore.AP003.mapper.views.PaymentViewMapper;
import br.com.techie.shoppingstore.AP003.model.Cart;
import br.com.techie.shoppingstore.AP003.model.Payment;
import br.com.techie.shoppingstore.AP003.repository.CartRepository;
import br.com.techie.shoppingstore.AP003.repository.PaymentRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private PaymentFormMapper paymentFormMapper;

    @Mock
    private PaymentViewMapper paymentViewMapper;

    private Payment payment;
    private Cart cart;
    private PaymentFORM paymentForm;
    private PaymentVIEW paymentView;

    @BeforeEach
    void setUp() {
        payment = new Payment(1L, LocalDateTime.now().plusDays(1), PaymentTypeEnum.CREDIT_CARD, BigDecimal.valueOf(100));
        cart = new Cart();
        cart.setId(1L);
        cart.setTotalPrice(BigDecimal.valueOf(100));
        paymentForm = new PaymentFORM(1L, LocalDateTime.now().plusDays(1), BigDecimal.valueOf(100), PaymentTypeEnum.CREDIT_CARD);
        paymentView = new PaymentVIEW(1L, LocalDateTime.now().plusDays(1).toString(), BigDecimal.valueOf(100), PaymentTypeEnum.CREDIT_CARD);
    }

    @Test
    public void testFindAllPaged() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Payment> page = new PageImpl<>(Collections.singletonList(payment));

        when(paymentRepository.findAll(pageable)).thenReturn(page);
        when(paymentViewMapper.map(payment)).thenReturn(paymentView);

        Page<PaymentVIEW> result = paymentService.findAllPaged(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(paymentRepository, times(1)).findAll(pageable);
        verify(paymentViewMapper, times(1)).map(payment);
    }

    @Test
    public void testFindById() {
        when(paymentRepository.findById(1L)).thenReturn(Optional.of(payment));
        when(paymentViewMapper.map(payment)).thenReturn(paymentView);

        PaymentVIEW result = paymentService.findById(1L);

        assertNotNull(result);
        assertEquals(paymentView, result);
        verify(paymentRepository, times(1)).findById(1L);
        verify(paymentViewMapper, times(1)).map(payment);
    }

    @Test
    public void testFindByIdNotFound() {
        when(paymentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> paymentService.findById(1L));
        verify(paymentRepository, times(1)).findById(1L);
    }

    @Test
    public void testInsert() {
        when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));
        when(paymentFormMapper.map(paymentForm)).thenReturn(payment);
        when(paymentViewMapper.map(payment)).thenReturn(paymentView);

        PaymentVIEW result = paymentService.insert(paymentForm);

        assertNotNull(result);
        assertEquals(paymentView, result);
        verify(cartRepository, times(1)).findById(1L);
        verify(paymentFormMapper, times(1)).map(paymentForm);
        verify(paymentRepository, times(1)).save(payment);
        verify(paymentViewMapper, times(1)).map(payment);
    }

    @Test
    public void testInsertCartNotFound() {
        when(cartRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> paymentService.insert(paymentForm));
        verify(cartRepository, times(1)).findById(1L);
    }

    @Test
    public void testDelete() {
        doNothing().when(paymentRepository).deleteById(1L);

        paymentService.delete(1L);

        verify(paymentRepository, times(1)).deleteById(1L);
    }
}
