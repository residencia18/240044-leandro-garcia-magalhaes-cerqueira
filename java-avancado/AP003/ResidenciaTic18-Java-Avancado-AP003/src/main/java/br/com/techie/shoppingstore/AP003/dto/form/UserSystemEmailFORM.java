package br.com.techie.shoppingstore.AP003.dto.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserSystemEmailFORM(
        @NotBlank(message = "Email is required!")
        @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$", message = "Invalid email!")
        @Size(max = 50, message = "Email must be less than 50 characters!")
        String email
) { } 