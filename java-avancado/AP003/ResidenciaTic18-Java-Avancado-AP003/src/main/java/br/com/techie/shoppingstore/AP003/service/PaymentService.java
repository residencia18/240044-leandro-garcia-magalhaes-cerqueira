package br.com.techie.shoppingstore.AP003.service;

import br.com.techie.shoppingstore.AP003.dto.form.PaymentFORM;
import br.com.techie.shoppingstore.AP003.dto.view.PaymentVIEW;
import br.com.techie.shoppingstore.AP003.enums.PaymentStatusEnum;
import br.com.techie.shoppingstore.AP003.infra.exception.ResourceNotFoundException;
import br.com.techie.shoppingstore.AP003.mapper.forms.PaymentFormMapper;
import br.com.techie.shoppingstore.AP003.mapper.views.PaymentViewMapper;
import br.com.techie.shoppingstore.AP003.model.Cart;
import br.com.techie.shoppingstore.AP003.model.Payment;
import br.com.techie.shoppingstore.AP003.repository.CartRepository;
import br.com.techie.shoppingstore.AP003.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PaymentService {
  
  @Autowired
  private PaymentRepository paymentRepository;

  @Autowired
  private CartRepository cartRepository;

  @Autowired
  private PaymentFormMapper paymentFormMapper;

  @Autowired
  private PaymentViewMapper paymentViewMapper;

  @Transactional(readOnly = true)
  public Page<PaymentVIEW> findAllPaged(Pageable pageable) {
    return paymentRepository.findAll(pageable).map(x -> paymentViewMapper.map(x));
  }

  @Transactional(readOnly = true)
  public PaymentVIEW findById(Long id) {
    Optional<Payment> obj = paymentRepository.findById(id);
    Payment entity = obj.orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
    return paymentViewMapper.map(entity);
  }

  @Transactional
  public PaymentVIEW insert(PaymentFORM paymentFORM) {
    Cart cart = cartRepository.findById(paymentFORM.cart_id())
        .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
    Payment entity = paymentFormMapper.map(paymentFORM);
    entity.setAmount(cart.getTotalPrice());
    paymentRepository.save(entity);
    cart.setPayment(entity);
    cart.setStatus(PaymentStatusEnum.PAID);
    return paymentViewMapper.map(entity);
  }

  @Transactional
  public void delete(Long id) {
    paymentRepository.deleteById(id);
  }
}
