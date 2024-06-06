package br.com.techie.shoppingstore.AP003.dto.form;

import br.com.techie.shoppingstore.AP003.enums.PaymentTypeEnum;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentFORM(
        @NotNull(message = "Cart id is required!")
        @JsonAlias({"cartId", "idCart", "id_cart"})
        Long cart_id,

        @NotNull(message = "Payment date is required!")
        @JsonAlias({"date", "datePayment", "dayPayment", "paymentDate", "paymentDay"})
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime payday,

        @NotNull(message = "Payment type is required!")
        PaymentTypeEnum payment_type
) { }
