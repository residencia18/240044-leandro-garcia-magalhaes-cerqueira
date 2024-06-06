package br.com.techie.shoppingstore.AP003.dto.form;

import jakarta.validation.constraints.NotBlank;

public record CategoryFORM(
        @NotBlank(message = "Category name is required!")
        String name
) { }
