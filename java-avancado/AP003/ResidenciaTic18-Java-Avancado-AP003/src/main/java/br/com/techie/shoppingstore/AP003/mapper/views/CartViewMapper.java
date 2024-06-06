package br.com.techie.shoppingstore.AP003.mapper.views;

import br.com.techie.shoppingstore.AP003.dto.view.CartVIEW;
import br.com.techie.shoppingstore.AP003.dto.view.PaymentVIEW;
import br.com.techie.shoppingstore.AP003.mapper.Mapper;
import br.com.techie.shoppingstore.AP003.model.Cart;
import br.com.techie.shoppingstore.AP003.model.CartItem;
import br.com.techie.shoppingstore.AP003.model.Payment;
import br.com.techie.shoppingstore.AP003.repository.CartItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CartViewMapper implements Mapper<Cart, CartVIEW> {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartItemViewMapper cartItemViewMapper;

    @Autowired
    private PaymentViewMapper paymentViewMapper;

    @Autowired
    private UserSystemViewMapper userViewMapper;

    @Override
    public CartVIEW map(Cart i) {
        Set<CartItem> cartItems = cartItemRepository.findAllByCartId(i.getId());
        i.setItems(cartItems);

        Payment payment = i.getPayment();
        PaymentVIEW paymentVIEW = null;
        if(payment != null)
            paymentVIEW = paymentViewMapper.map(i.getPayment());

        return new CartVIEW(
                i.getId(),
                i.getItems().stream().map(x -> cartItemViewMapper.map(x)).collect(Collectors.toSet()),
                paymentVIEW,
                userViewMapper.map(i.getUser()),
                i.getTotalPrice(),
                i.getTotalItems(),
                i.getPurchaseDate().toString(),
                i.getStatus()
        );
    }
}
