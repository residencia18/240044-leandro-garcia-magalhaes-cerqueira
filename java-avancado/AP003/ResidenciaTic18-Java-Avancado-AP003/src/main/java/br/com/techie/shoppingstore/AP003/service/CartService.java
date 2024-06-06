package br.com.techie.shoppingstore.AP003.service;

import java.util.Optional;

import br.com.techie.shoppingstore.AP003.dto.form.CartFORM;
import br.com.techie.shoppingstore.AP003.dto.form.CartUpdateFORM;
import br.com.techie.shoppingstore.AP003.dto.view.CartVIEW;
import br.com.techie.shoppingstore.AP003.enums.PaymentStatusEnum;
import br.com.techie.shoppingstore.AP003.infra.exception.ResourceNotFoundException;
import br.com.techie.shoppingstore.AP003.mapper.forms.CartFormMapper;
import br.com.techie.shoppingstore.AP003.mapper.updates.CartUpdateMapper;
import br.com.techie.shoppingstore.AP003.mapper.views.CartViewMapper;
import br.com.techie.shoppingstore.AP003.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.techie.shoppingstore.AP003.model.Cart;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CartService {

  @Autowired
  private CartRepository cartRepository;

  @Autowired
  private CartViewMapper cartViewMapper;

  @Autowired
  private CartFormMapper cartFormMapper;

  @Autowired
  private CartUpdateMapper cartUpdateMapper;

  @Cacheable("Carts")
  @Transactional(readOnly = true)
  public Page<CartVIEW> findAllPaged(Pageable pageable) {
    return cartRepository.findAll(pageable).map(x -> cartViewMapper.map(x));
  }

  @Transactional(readOnly = true)
  public Page<CartVIEW> findAllPaidPaged(Pageable pageable) {
    return cartRepository.findAllByStatus(PaymentStatusEnum.PAID, pageable).map(x -> cartViewMapper.map(x));
  }

  @Transactional(readOnly = true)
  public Page<CartVIEW> findAllUnpaidPaged(Pageable pageable) {
    return cartRepository.findAllByStatus(PaymentStatusEnum.PENDING, pageable).map(x -> cartViewMapper.map(x));
  }

  @Transactional(readOnly = true)
  public CartVIEW findById(Long id) {
    Optional<Cart> obj = cartRepository.findById(id);
    Cart entity = obj.orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
    return cartViewMapper.map(entity);
  }

  @Transactional
  @CacheEvict(cacheNames = {"Carts"}, allEntries = true)
  public CartVIEW insert(CartFORM cartForm) {
    Cart entity = cartFormMapper.map(cartForm);
    cartRepository.save(entity);
    return cartViewMapper.map(entity);
  }

  @Transactional
  @CacheEvict(cacheNames = {"Carts"}, allEntries = true)
  public CartVIEW update(CartUpdateFORM dto) {
    Cart entity = cartRepository.findById(dto.cart_id())
        .orElseThrow(() -> new EntityNotFoundException("Cart not found!"));
    entity = cartUpdateMapper.map(dto, entity);
    cartRepository.save(entity);
    return cartViewMapper.map(entity);
  }

  @Transactional
  @CacheEvict(cacheNames = {"Carts"}, allEntries = true)
  public void delete(Long id) {
    cartRepository.deleteById(id);
  }
}
