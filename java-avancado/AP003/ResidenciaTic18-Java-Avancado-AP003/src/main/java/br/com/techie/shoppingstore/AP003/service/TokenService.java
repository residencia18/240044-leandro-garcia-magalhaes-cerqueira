package br.com.techie.shoppingstore.AP003.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.techie.shoppingstore.AP003.dto.form.PasswordResetFORM;
import br.com.techie.shoppingstore.AP003.infra.jwt.JwtToken;
import br.com.techie.shoppingstore.AP003.infra.jwt.JwtUserDetailsService;
import br.com.techie.shoppingstore.AP003.infra.jwt.JwtUtils;
import br.com.techie.shoppingstore.AP003.model.Token;
import br.com.techie.shoppingstore.AP003.model.UserSystem;
import br.com.techie.shoppingstore.AP003.repository.TokenRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class TokenService {

  @Autowired
  private TokenRepository tokenRepository;

  @Autowired
  JwtUserDetailsService jwtUserDetailsService;

  @Autowired
  private EmailService emailService;

  @Transactional(readOnly = false)
  public Token requestPasswordReset(UserSystem user) throws MessagingException {

    JwtToken token = jwtUserDetailsService.getTokenAuthenticated(user.getEmail());

    String verificador = token.getToken();
    LocalDateTime dataCriacao = LocalDateTime.now();
    user.setCodeVerifier(verificador);
    Token tokenEntity = new Token(verificador, dataCriacao, user);
    tokenRepository.save(tokenEntity);

    emailService.sendOrderResetPassword(user.getEmail(), verificador);
    log.info("Token criado para o usuário {}", user.getEmail());
    return tokenEntity;
  }

  public ResponseEntity<Void> validarTokenECodeVerifier(String token, PasswordResetFORM dto) {
    // Verifica se o token é válido
    if (!JwtUtils.isTokenValid(token)) {
      log.error("Token inválido" + token);
      return ResponseEntity.badRequest().build();
    }

    // Busca o token do usuário pelo token fornecido
    Optional<Token> tokenDoUsuario = findByToken(token);
    if (tokenDoUsuario.isEmpty()) {
      log.error("Token não encontrado para o token {}", token);
      return ResponseEntity.notFound().build();
    }

    // Verifica se o código verificador é válido
    Token tokenEncontrado = tokenDoUsuario.get();
    if (!dto.codeVerifier().equals(tokenEncontrado.getToken())) {
      log.error("Código de verificação inválido para o token {}", tokenEncontrado);
      return ResponseEntity.badRequest().build();
    }

    // Se tudo estiver válido, retorna null
    return null;
  }

  @Transactional(readOnly = true)
  public Optional<Token> findByToken(String token) {
    return tokenRepository.findByToken(token);
  }

  public void deleteToken(Token token) {
    tokenRepository.delete(token);
  }

}