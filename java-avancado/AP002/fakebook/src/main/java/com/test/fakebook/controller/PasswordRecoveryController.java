package com.test.fakebook.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.test.fakebook.entity.User;
import com.test.fakebook.service.EmailService;
import com.test.fakebook.service.TokenService;
import com.test.fakebook.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth/password-recovery")
@RequiredArgsConstructor
public class PasswordRecoveryController {
	
		@Autowired
	 	private final UserService userService;
		
		@Autowired
	    private final TokenService tokenService;
		
		@Autowired
	    private final EmailService emailService;

		@PostMapping("/request")
		public ResponseEntity<String> requestPasswordRecovery(@RequestBody Map<String, String> request) {
		    String email = request.get("email");
		    Optional<User> userOptional = userService.findByEmail(email);
		    if (userOptional.isPresent()) {
		        User user = userOptional.get();
		        String token = tokenService.generateToken();
		        tokenService.saveToken(token, user);
		        String recoveryLink = "http://seusite.com/recuperar-senha?token=" + token;
		        String emailBody = "Olá,\n\nVocê solicitou a recuperação de senha. Por favor, clique no link a seguir para cadastrar uma nova senha:\n" + recoveryLink;
		        emailService.sendEmail(email, "Recuperação de Senha", emailBody);
		        return ResponseEntity.ok("Um e-mail de recuperação foi enviado para " + email);
		    } else {
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado para o e-mail fornecido");
		    }
		}


}
