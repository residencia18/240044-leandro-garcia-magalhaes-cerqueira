package br.com.techie.shoppingstore.AP003.dto.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ScoreFORM(
        @NotNull(message = "The product ID is required!")
        Long product_id,

        @NotNull(message = "The user ID is required!")
        Long user_id,

        @NotNull(message = "The score is required!")
        @Min(value = 1, message = "The score must be between 1 and 5!")
        @Max(value = 5, message = "The score must be between 1 and 5!")
        Integer score,

        @Size(max = 255, message = "The comment must have a maximum of 255 characters!")
        String comment
) { }
