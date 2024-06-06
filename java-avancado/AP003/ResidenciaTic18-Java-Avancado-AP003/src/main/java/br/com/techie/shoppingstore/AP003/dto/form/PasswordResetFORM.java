package br.com.techie.shoppingstore.AP003.dto.form;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PasswordResetFORM(

        @NotBlank(message = "New password is required!")
        @Size(min = 8, message = "Password must be at least 8 characters long!")
        @Pattern(regexp = "^(?=.*[a-z]).*$", message = "Password must contain at least one lowercase letter!")
        @Pattern(regexp = "^(?=.*[A-Z]).*$", message = "Password must contain at least one uppercase letter!")
        @Pattern(regexp = "^(?=.*\\d).*$", message = "Password must contain at least one digit!")
        @Pattern(regexp = "^(?=.*[@$!%*?&]).*$", message = "Password must contain at least one special character!")
        @JsonAlias({"newPassword"})
        String new_password,

        @NotBlank(message = "Password confirmation is required!")
        @Size(min = 8, message = "Password confirmation must be at least 8 characters long!")
        @JsonAlias({"confirmPassword", "passwordConfirmation"})
        String confirm_password,

        String codeVerifier
) {
}
