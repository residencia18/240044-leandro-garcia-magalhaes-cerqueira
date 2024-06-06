package br.com.techie.shoppingstore.AP003.dto.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserSystemUpdateFORM(
    
    @NotNull(message = "User ID is required!") 
    Long user_id,

    @NotBlank(message = "Username is required!")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters!")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Username must contain only letters!")
    String username,

    @NotBlank(message = "Email is required!")
    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$", message = "Invalid email!")
    @Size(max = 50, message = "Email must be less than 50 characters!")
    String email

) { }
