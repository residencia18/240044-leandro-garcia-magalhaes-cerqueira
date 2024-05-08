package com.test.fakebook.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.test.fakebook.entity.PasswordResetToken;
import com.test.fakebook.entity.User;
import com.test.fakebook.service.TokenService;
import com.test.fakebook.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth/password-recovery")
@RequiredArgsConstructor
public class PasswordResetController {

	@Autowired
    private final TokenService tokenService;
	
	@Autowired
    private final UserService userService;
	
    private final PasswordEncoder passwordEncoder;

    
    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestBody Map<String, String> request) {
    	
        String newPassword = request.get("password");
        System.out.println(newPassword);

        // Busca o token
        PasswordResetToken passwordResetToken = tokenService.findByToken(token);
        
        System.out.println("ATENÇÃO: " + passwordResetToken);
        
        // Verifica se o token é válido
        if (passwordResetToken != null) {
            
            // Verifica se o token está expirado
            if (!tokenService.isTokenExpired(passwordResetToken)) {
            	
            	
            	Optional<User> updateUser = userService.findById(passwordResetToken.getUser().getId());
           	
         
                
                User user = updateUser.get();
                
                // Atualiza a senha do usuário associado ao token
                user.setPassword(passwordEncoder.encode(newPassword));
                               
                userService.saveUser(user);
                
                // Invalida o token para que não possa ser reutilizado
                tokenService.invalidateToken(token);
                
                return ResponseEntity.ok("Senha redefinida com sucesso.");
                
           } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token expirado.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token inválido.");
        }
    }

}

