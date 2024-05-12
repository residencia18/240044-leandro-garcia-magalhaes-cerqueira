package com.test.fakebook.dto;

import com.test.fakebook.validation.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

// DTO class representing a request for user registration
public record RegisterRequest(@NotBlank String username, @ValidPassword String password, @NotBlank @Email String email) {
    // This record class automatically generates constructor, getters, equals, hashcode, and toString methods

    // The annotations ensure that the fields meet validation criteria:
    // - @NotBlank ensures that 'username' and 'email' are not empty or blank
    // - @ValidPassword ensures that 'password' meets custom validation criteria
    // - @Email ensures that 'email' follows the email format
}

