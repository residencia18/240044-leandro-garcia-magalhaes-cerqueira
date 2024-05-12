package com.test.fakebook.service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.test.fakebook.entity.PasswordResetToken;
import com.test.fakebook.entity.User;
import com.test.fakebook.repository.PasswordResetTokenRepository;

// Service class for managing password reset tokens
@Service
public class TokenService {

	// Autowired repository for password reset tokens
	@Autowired
	private PasswordResetTokenRepository tokenRepository;

	// Method to generate a unique token
	public String generateToken() {
		return UUID.randomUUID().toString();
	}

	// Method to save a token with a reference to the user's email
	public void saveToken(String token, User user) {
		PasswordResetToken passwordResetToken = new PasswordResetToken();
		passwordResetToken.setToken(token);
		passwordResetToken.setUser(user);
		// Set the token expiration date (e.g., 24 hours from now)
		passwordResetToken
				.setExpiryDate(new Date(System.currentTimeMillis() + PasswordResetToken.getExpiration() * 60 * 1000));
		// Save the token
		tokenRepository.save(passwordResetToken);
	}

	// Method to find a token by its value
	public PasswordResetToken findByToken(String token) {
		Optional<PasswordResetToken> optionalToken = tokenRepository.findByToken(token);
		if (optionalToken.isPresent()) {
			PasswordResetToken passwordResetToken = optionalToken.get();
			if (!isTokenExpired(passwordResetToken)) {
				return passwordResetToken;
			} else {
				System.out.println("Expired token: " + token);
			}
		} else {
			System.out.println("Token not found: " + token);
		}
		return null;
	}

	// Method to invalidate a token
	public void invalidateToken(String token) {
		Optional<PasswordResetToken> optionalToken = tokenRepository.findByToken(token);
		if (optionalToken.isPresent()) {
			PasswordResetToken passwordResetToken = optionalToken.get();
			tokenRepository.deleteByToken(passwordResetToken.getToken());
		} else {
			System.out.println("Token not found: " + token);
		}
	}

	// Method to check if a token is expired
	public boolean isTokenExpired(PasswordResetToken token) {
		return token.getExpiryDate().before(new Date());
	}
}
