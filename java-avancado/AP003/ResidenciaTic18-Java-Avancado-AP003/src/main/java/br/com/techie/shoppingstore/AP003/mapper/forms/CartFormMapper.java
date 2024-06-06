package br.com.techie.shoppingstore.AP003.mapper.forms;

import br.com.techie.shoppingstore.AP003.dto.form.CartFORM;
import br.com.techie.shoppingstore.AP003.dto.form.CartItemFORM;
import br.com.techie.shoppingstore.AP003.enums.PaymentStatusEnum;
import br.com.techie.shoppingstore.AP003.mapper.Mapper;
import br.com.techie.shoppingstore.AP003.model.Cart;
import br.com.techie.shoppingstore.AP003.model.CartItem;
import br.com.techie.shoppingstore.AP003.repository.CartItemRepository;
import br.com.techie.shoppingstore.AP003.repository.UserSystemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
public class CartFormMapper implements Mapper<CartFORM, Cart> {


    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserSystemRepository userRepository;

    @Autowired
    private CartItemFormMapper cartItemFormMapper;

    @Override
    public Cart map(CartFORM i) {
        Cart cart = new Cart();
        cart.setUser(userRepository.findById(i.user_id()).orElseThrow(() -> new EntityNotFoundException("User not found!")));

        Set<CartItem> items = new HashSet<>();
        BigDecimal totalPrice = BigDecimal.ZERO;
        int totalItems = 0;

        for (CartItemFORM item : i.items()){
            CartItem cartItem = cartItemFormMapper.map(item);
            cartItemRepository.save(cartItem);
            items.add(cartItem);
            totalItems = totalItems + cartItem.getQuantity();
            totalPrice = totalPrice.add(cartItem.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
        }

        cart.setItems(items);
        cart.setTotalItems(totalItems);
        cart.setTotalPrice(totalPrice);
        cart.setPurchaseDate(LocalDateTime.now());
        cart.setStatus(PaymentStatusEnum.PENDING);

        return cart;
    }
}
