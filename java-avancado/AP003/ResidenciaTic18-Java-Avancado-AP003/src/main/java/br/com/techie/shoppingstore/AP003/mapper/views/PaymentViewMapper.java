package br.com.techie.shoppingstore.AP003.mapper.views;

import br.com.techie.shoppingstore.AP003.dto.view.PaymentVIEW;
import br.com.techie.shoppingstore.AP003.mapper.Mapper;
import br.com.techie.shoppingstore.AP003.model.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentViewMapper implements Mapper<Payment, PaymentVIEW> {
    @Override
    public PaymentVIEW map(Payment i) {
        return new PaymentVIEW(
                i.getId(),
                i.getPayday().toString(),
                i.getAmount(),
                i.getPaymentType()
        );
    }
}
