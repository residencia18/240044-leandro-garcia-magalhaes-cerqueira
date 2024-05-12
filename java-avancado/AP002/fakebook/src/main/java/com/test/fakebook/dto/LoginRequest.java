package com.test.fakebook.dto;

// DTO class representing a login request
public record LoginRequest(String username, String password) {
    // This record class automatically generates constructor, getters, equals, hashcode, and toString methods
}
