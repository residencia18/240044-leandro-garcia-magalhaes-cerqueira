package com.test.fakebook.security;

import java.time.Instant;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

// Service class for JWT token generation
@Service
@RequiredArgsConstructor
public class JwtProvider {
    // JwtEncoder for encoding JWT tokens
    private final JwtEncoder jwtEncoder;
    // Expiration time for JWT tokens in seconds
    public static final Long EXPIRATION_TIME_IN_SECONDS = 3600L;

    // Generate a JWT token from authentication details
    public String generateToken(Authentication authentication) {
        // Extract user details from authentication
        User principal = (User) authentication.getPrincipal();
        // Get current time
        var now = Instant.now();
        // Build JWT claims
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(EXPIRATION_TIME_IN_SECONDS))
                .subject(principal.getUsername())
                .claim("scope", "ROLE_USER")
                .build();

        // Encode JWT token with specified claims
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
