package br.com.techie.shoppingstore.AP003.dto.form;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

public record CartFORM(
        @NotNull(message = "User id is required!")
        Long user_id,

        @NotNull(message = "Items must be in the cart")
        Set<@Valid CartItemFORM> items
) { }
