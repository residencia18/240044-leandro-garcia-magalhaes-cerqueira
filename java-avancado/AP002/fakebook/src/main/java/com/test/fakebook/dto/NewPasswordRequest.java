package com.test.fakebook.dto;

import com.test.fakebook.validation.ValidPassword;

// DTO class representing a request for a new password
public record NewPasswordRequest(@ValidPassword String password) {
    // This record class automatically generates constructor, getters, equals, hashcode, and toString methods

    // The @ValidPassword annotation ensures that the 'password' field meets custom validation criteria
}

