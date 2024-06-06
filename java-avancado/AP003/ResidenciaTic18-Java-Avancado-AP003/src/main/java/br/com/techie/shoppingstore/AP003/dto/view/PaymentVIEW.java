package br.com.techie.shoppingstore.AP003.dto.view;

import br.com.techie.shoppingstore.AP003.enums.PaymentTypeEnum;

import java.math.BigDecimal;

public record PaymentVIEW(
        Long id,
        String payday,
        BigDecimal amount,
        PaymentTypeEnum paymentType
) { }
