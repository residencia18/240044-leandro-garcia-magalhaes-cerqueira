package com.test.fakebook.service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.test.fakebook.entity.PasswordResetToken;
import com.test.fakebook.entity.User;
import com.test.fakebook.repository.PasswordResetTokenRepository;

@Service
public class TokenService {

	@Autowired
	private PasswordResetTokenRepository tokenRepository;

	// Método para gerar um token único
	public String generateToken() {
		return UUID.randomUUID().toString();
	}

	// Método para armazenar um token com a referência ao e-mail do usuário
	public void saveToken(String token, User user) {
		PasswordResetToken passwordResetToken = new PasswordResetToken();
		passwordResetToken.setToken(token);
		passwordResetToken.setUser(user);
		// Defina a data de expiração do token (por exemplo, 24 horas a partir de agora)
		passwordResetToken
				.setExpiryDate(new Date(System.currentTimeMillis() + PasswordResetToken.getExpiration() * 60 * 1000));
		tokenRepository.save(passwordResetToken);
	}

	// Método para recuperar o e-mail associado a um token
	public String getEmailByToken(String token) {
		Optional<PasswordResetToken> optionalToken = tokenRepository.findByToken(token);
		System.out.println("O token buscado é: " + token);

		if (optionalToken.isPresent()) {
			PasswordResetToken passwordResetToken = optionalToken.get();
			System.out.println("OK OBJETO: " + passwordResetToken.getUser().toString());

			if (!isTokenExpired(passwordResetToken)) {
				System.out.println("O E-MAIL FOI ENCONTRADO: " + passwordResetToken.getUser().getEmail());
				return passwordResetToken.getUser().getEmail();
			} else {
				System.out.println("Token expirado: " + token);
				System.out.println("OK OBJETO: " + passwordResetToken.getUser().toString());
			}
		} else {
			System.out.println("Token não encontrado: " + token);

		}

		return null;
	}

	// Método para invalidar um token
	public void invalidateToken(String token) {
		Optional<PasswordResetToken> optionalToken = tokenRepository.findByToken(token);
		optionalToken.ifPresent(tokenRepository::delete);
	}

	// Método auxiliar para verificar se um token está expirado
	private boolean isTokenExpired(PasswordResetToken token) {
		return token.getExpiryDate().before(new Date());
	}

}
