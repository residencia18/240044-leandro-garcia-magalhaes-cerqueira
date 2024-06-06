package br.com.techie.shoppingstore.AP003.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.techie.shoppingstore.AP003.dto.form.PasswordResetFORM;
import br.com.techie.shoppingstore.AP003.infra.exception.ErrorMessage;
import br.com.techie.shoppingstore.AP003.model.Token;
import br.com.techie.shoppingstore.AP003.service.TokenService;
import br.com.techie.shoppingstore.AP003.service.UserSystemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Password Reset", description = "Contains all operations for resources to reset the user's password.")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/password-reset")
public class PasswordResetController {

  private final UserSystemService userService;
  private final TokenService tokenService;

  @Operation(summary = "Update password using token", description = "Resource to update the user's password with a token", responses = {
      @ApiResponse(responseCode = "204", description = "Password successfully updated", 
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))),
      @ApiResponse(responseCode = "400", description = "Password does not match", 
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
      @ApiResponse(responseCode = "404", description = "Resource not found", 
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
      @ApiResponse(responseCode = "422", description = "Invalid or incorrectly formatted fields", 
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
  })
  @PatchMapping("/{token}")
  public ResponseEntity<Void> passwordresetconfirmation(@PathVariable String token, @Valid @RequestBody PasswordResetFORM dto) {
    ResponseEntity<Void> response = tokenService.validarTokenECodeVerifier(token, dto);
    if (response != null) {
      return response;
    }
    Token findedToken = tokenService.findByToken(token).get();
    userService.changePassword(findedToken, dto.new_password(), dto.confirm_password());
    log.info("User password changed successfully. User: {}", findedToken.getUserSystem().getEmail());
    tokenService.deleteToken(findedToken);
    return ResponseEntity.noContent().build();

  }

}