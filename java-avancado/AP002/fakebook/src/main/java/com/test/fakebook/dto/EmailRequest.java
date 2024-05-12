package com.test.fakebook.dto;

import jakarta.validation.constraints.Email;

// DTO class representing an email request
public record EmailRequest(@Email String email) {
    // This record class automatically generates constructor, getters, equals, hashcode, and toString methods
    // The @Email annotation ensures that the 'email' field follows the email format
}
