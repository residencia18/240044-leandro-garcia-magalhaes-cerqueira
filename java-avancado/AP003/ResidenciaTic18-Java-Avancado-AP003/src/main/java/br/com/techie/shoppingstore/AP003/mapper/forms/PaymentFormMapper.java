package br.com.techie.shoppingstore.AP003.mapper.forms;

import br.com.techie.shoppingstore.AP003.dto.form.PaymentFORM;
import br.com.techie.shoppingstore.AP003.mapper.Mapper;
import br.com.techie.shoppingstore.AP003.model.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFormMapper implements Mapper<PaymentFORM, Payment> {
    @Override
    public Payment map(PaymentFORM i) {
        return new Payment(
                null,
                i.payday(),
                i.payment_type(),
                null
                );
    }
}
