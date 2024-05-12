package com.test.fakebook.dto;

// DTO class representing a login response containing a token
public record LoginResponse(String token) {
    // This record class automatically generates constructor, getters, equals, hashcode, and toString methods
}
