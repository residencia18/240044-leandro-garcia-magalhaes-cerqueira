package br.com.techie.shoppingstore.AP003.dto.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryUpdateFORM(
        @NotNull(message = "Category ID is required!")
        Long category_id,

        @NotBlank(message = "Category name is required!")
        String name
) {
}
