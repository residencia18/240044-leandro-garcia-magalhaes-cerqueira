package br.com.techie.shoppingstore.AP003.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.techie.shoppingstore.AP003.dto.form.UserSystemEmailFORM;
import br.com.techie.shoppingstore.AP003.dto.view.TokenVIEW;
import br.com.techie.shoppingstore.AP003.dto.view.UserSystemVIEW;
import br.com.techie.shoppingstore.AP003.infra.exception.ErrorMessage;
import br.com.techie.shoppingstore.AP003.mapper.forms.TokenFormMapper;
import br.com.techie.shoppingstore.AP003.mapper.views.UserSystemViewMapper;
import br.com.techie.shoppingstore.AP003.model.Token;
import br.com.techie.shoppingstore.AP003.model.UserSystem;
import br.com.techie.shoppingstore.AP003.service.TokenService;
import br.com.techie.shoppingstore.AP003.service.UserSystemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "Password Recovery", description = "Contains all operations for resources to receive a password recovery email.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/password-recovery")
public class PasswordRecoveryController {

  private final TokenService tokenService;
  private final UserSystemService userService;
  private final TokenFormMapper tokenFormMapper;
  private final UserSystemViewMapper userViewMapper;

  @Operation(summary = "Reset password via email", description = "Resource to reset password via email", responses = {
    @ApiResponse(responseCode = "204", description = "Password reset request successfully sent", 
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserSystemVIEW.class))),
    @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(mediaType = "application/json", 
    schema = @Schema(implementation = ErrorMessage.class))),
    @ApiResponse(responseCode = "401", description = "User not authorized", content = @Content(mediaType = "application/json", 
    schema = @Schema(implementation = ErrorMessage.class)))
  })
  @PostMapping
  public ResponseEntity<TokenVIEW> redefinepassword(@RequestBody @Valid UserSystemEmailFORM dto)throws MessagingException {
    UserSystem user = userService.searchByEmail(dto.email());
    Optional<Token> tokenExistenteOptional = tokenService.findByToken(user.getCodeVerifier());
    tokenExistenteOptional.ifPresent(token -> tokenService.deleteToken(token));

    Token token = tokenService.requestPasswordReset(user);
    log.info("Token created for the user {}", dto.email());
    TokenVIEW tokenVIEW = tokenFormMapper.map(token, userViewMapper.map(user));
    return ResponseEntity.ok(tokenVIEW);
  }

}