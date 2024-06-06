package br.com.techie.shoppingstore.AP003.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.techie.shoppingstore.AP003.dto.form.UserSystemLoginFORM;
import br.com.techie.shoppingstore.AP003.dto.view.UserSystemVIEW;
import br.com.techie.shoppingstore.AP003.infra.exception.ErrorMessage;
import br.com.techie.shoppingstore.AP003.infra.jwt.JwtToken;
import br.com.techie.shoppingstore.AP003.infra.jwt.JwtUserDetailsService;

@Tag(name = "Authentication", description = "Resource for proceeding with API authentication")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final JwtUserDetailsService detailsService;
    private final AuthenticationManager authenticationManager;

    @Operation(summary = "Authenticate with the API", description = "API authentication resource", responses = {
        @ApiResponse(responseCode = "200", description = "Authentication successful, returning a bearer token", 
        content = @Content(mediaType = "application/json", 
        schema = @Schema(implementation = UserSystemVIEW.class))),
        @ApiResponse(responseCode = "400", description = "Invalid credentials", content = @Content(mediaType = "application/json", 
        schema = @Schema(implementation = ErrorMessage.class))),
        @ApiResponse(responseCode = "422", description = "Invalid field(s)", content = @Content(mediaType = "application/json", 
        schema = @Schema(implementation = ErrorMessage.class)))
    })
    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody @Valid UserSystemLoginFORM dto, HttpServletRequest request) {

        log.info("Authentication process via login {}", dto.email());
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());

            authenticationManager.authenticate(authenticationToken);

            JwtToken token = detailsService.getTokenAuthenticated(dto.email());

            return ResponseEntity.ok(token);
            
        } catch (AuthenticationException ex) {
            log.warn("Bad Credentials from email '{}'", dto.email());
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, "Invalid credentials: " + ex.getMessage()));
        }
    }
}
