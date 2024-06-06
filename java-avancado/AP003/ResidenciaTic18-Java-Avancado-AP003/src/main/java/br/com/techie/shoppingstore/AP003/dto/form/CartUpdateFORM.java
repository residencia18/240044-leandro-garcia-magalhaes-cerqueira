package br.com.techie.shoppingstore.AP003.dto.form;

import br.com.techie.shoppingstore.AP003.enums.PaymentStatusEnum;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Set;

public record CartUpdateFORM(
        @NotNull(message = "Cart ID is required!")
        Long cart_id,

        Set<@Valid CartItemFORM> items,

        @Valid
        PaymentFORM payment,

        @JsonAlias({"date", "datePurchase", "purchaseDate"})
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime purchase_date,

        PaymentStatusEnum status
) { }
