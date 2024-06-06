package br.com.techie.shoppingstore.AP003.dto.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserSystemLoginFORM(
        @NotBlank(message = "Email is required!")
        @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$", message = "Invalid email!")
        @Size(max = 50, message = "Email must be less than 50 characters!")
        String email,

        @NotBlank(message = "Password is required!")
        @Size(min = 8, message = "Password must be at least 8 characters long!")
        @Pattern(regexp = "^(?=.*[a-z]).*$", message = "Password must contain at least one lowercase letter!")
        @Pattern(regexp = "^(?=.*[A-Z]).*$", message = "Password must contain at least one uppercase letter!")
        @Pattern(regexp = "^(?=.*\\d).*$", message = "Password must contain at least one digit!")
        @Pattern(regexp = "^(?=.*[@$!%*?&]).*$", message = "Password must contain at least one special character!")
        String password
) { }