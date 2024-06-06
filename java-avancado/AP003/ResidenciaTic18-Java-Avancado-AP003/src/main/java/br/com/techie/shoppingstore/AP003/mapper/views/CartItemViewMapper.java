package br.com.techie.shoppingstore.AP003.mapper.views;

import br.com.techie.shoppingstore.AP003.dto.view.CartItemVIEW;
import br.com.techie.shoppingstore.AP003.mapper.Mapper;
import br.com.techie.shoppingstore.AP003.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartItemViewMapper implements Mapper<CartItem, CartItemVIEW> {
    @Autowired
    private ProductViewMapper productViewMapper;

    @Override
    public CartItemVIEW map(CartItem i) {
        return new CartItemVIEW(
                i.getId(),
                productViewMapper.map(i.getProduct()),
                i.getPrice(),
                i.getQuantity()
        );
    }
}
