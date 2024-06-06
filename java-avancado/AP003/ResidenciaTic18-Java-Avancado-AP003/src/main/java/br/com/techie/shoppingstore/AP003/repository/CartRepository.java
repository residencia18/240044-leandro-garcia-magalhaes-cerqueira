package br.com.techie.shoppingstore.AP003.repository;

import br.com.techie.shoppingstore.AP003.enums.PaymentStatusEnum;
import br.com.techie.shoppingstore.AP003.model.Cart;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

  Page<Cart> findAllByStatus(PaymentStatusEnum status, Pageable pageable);
}
