package com.test.fakebook.validation;

import java.util.Arrays;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// Validator class for password constraints
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    // List of common passwords to be avoided
    private List<String> commonPasswords = Arrays.asList("123456", "password", "12345678", "senha123", "87654321");

    // Method to initialize the validator
    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }

    // Method to validate the password
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Check if the value is null
        if (value == null) {
            return false;
        }
        // Validate password constraints
        return value.length() >= 8 &&
                value.matches(".*[A-Z].*") &&
                value.matches(".*[a-z].*") &&
                value.matches(".*[0-9].*") &&
                value.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*") &&
                !commonPasswords.contains(value);
    }
}

