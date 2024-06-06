package br.com.techie.shoppingstore.AP003.mapper.updates;

import br.com.techie.shoppingstore.AP003.dto.form.CartItemFORM;
import br.com.techie.shoppingstore.AP003.dto.form.CartUpdateFORM;
import br.com.techie.shoppingstore.AP003.mapper.forms.CartItemFormMapper;
import br.com.techie.shoppingstore.AP003.mapper.forms.PaymentFormMapper;
import br.com.techie.shoppingstore.AP003.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartUpdateMapper {
    @Autowired
    private PaymentFormMapper paymentFormMapper;

    @Autowired
    private CartItemFormMapper cartItemFormMapper;

    public Cart map(CartUpdateFORM form, Cart oldCart) {
        if (form.status() != null) oldCart.setStatus(form.status());
        if (form.purchase_date() != null) oldCart.setPurchaseDate(form.purchase_date());
        if (form.payment() != null) oldCart.setPayment(paymentFormMapper.map(form.payment()));
        if (form.items() != null) {
            oldCart.getItems().clear();
            for(CartItemFORM item : form.items()){
                oldCart.getItems().add(cartItemFormMapper.map(item));
            }
        }
        return oldCart;
    }
}
