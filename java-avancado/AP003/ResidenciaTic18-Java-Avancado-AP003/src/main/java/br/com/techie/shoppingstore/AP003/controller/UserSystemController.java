package br.com.techie.shoppingstore.AP003.controller;

import br.com.techie.shoppingstore.AP003.dto.form.PasswordUpdateFORM;
import br.com.techie.shoppingstore.AP003.dto.form.UserSystemFORM;
import br.com.techie.shoppingstore.AP003.dto.form.UserSystemUpdateFORM;
import br.com.techie.shoppingstore.AP003.dto.view.ScoreVIEW;
import br.com.techie.shoppingstore.AP003.dto.view.UserSystemVIEW;
import br.com.techie.shoppingstore.AP003.infra.exception.ErrorMessage;
import br.com.techie.shoppingstore.AP003.service.EmailService;
import br.com.techie.shoppingstore.AP003.service.ScoreService;
import br.com.techie.shoppingstore.AP003.service.UserSystemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users", description = "Contains all operations to resources for registering, editing and reading a user.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserSystemController {

  private final UserSystemService userService;
  private final EmailService emailService;

  @Autowired
  private ScoreService scoreService;

  @Operation(summary = "Create a new user", description = "Feature to create a new user in the system.", responses = {
      @ApiResponse(responseCode = "201", description = "User created successfully.", 
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserSystemVIEW.class))),
      @ApiResponse(responseCode = "409", description = "Email user already registered in the system.", 
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
      @ApiResponse(responseCode = "422", description = "Resources not processed due to invalid input data.", 
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
  })
  @PostMapping
  public ResponseEntity<UserSystemVIEW> create(@Valid @RequestBody UserSystemFORM createDto) throws MessagingException {
    UserSystemVIEW view = userService.save(createDto);
    emailService.RegistrationConfirmationEmail(createDto.email());
    return ResponseEntity.status(HttpStatus.CREATED).body(view);
  }

  @Operation(summary = "Retrieve a user by id", description = "Request requires a Bearer Token. Access restricted to logged in ADMIN OR CLIENT", 
  security = @SecurityRequirement(name = "security"), responses = {
      @ApiResponse(responseCode = "200", description = "Resource retrieved successfully.", 
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserSystemVIEW.class))),
      @ApiResponse(responseCode = "403", description = "User without permission to access this resource.", 
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
      @ApiResponse(responseCode = "404", description = "Resource not found.", content = @Content(mediaType = "application/json", 
      schema = @Schema(implementation = ErrorMessage.class)))

  })
  @GetMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN') OR ( hasRole('CLIENT') AND #id == authentication.principal.id )")
  public ResponseEntity<UserSystemVIEW> getById(@PathVariable Long id) {
    UserSystemVIEW view = userService.searchById(id);
    return ResponseEntity.ok(view);
  }

  @Operation(summary = "Update password", description = "Request requires a Bearer Token. Access restricted to logged in ADMIN OR CLIENT", 
  security = @SecurityRequirement(name = "security"), responses = {
      @ApiResponse(responseCode = "204", description = "Password updated successfully."),
      @ApiResponse(responseCode = "400", description = "Password does not match.", 
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
      @ApiResponse(responseCode = "403", description = "User without permission to access this resource.", 
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
      @ApiResponse(responseCode = "422", description = "Invalid or incorrectly formatted fields.", 
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
  })
  @PatchMapping("/{id}")
  @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT') AND (#id == authentication.principal.id)")
  public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid @RequestBody PasswordUpdateFORM dto) {
    userService.editPassword(id, dto.old_password(), dto.new_password(), dto.confirm_password());
    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "Update user", description = "Updates the details of an existing user.", responses = {
      @ApiResponse(responseCode = "200", description = "User updated successfully.", 
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserSystemVIEW.class))),
      @ApiResponse(responseCode = "404", description = "User not found.", content = @Content(mediaType = "application/json", 
      schema = @Schema(implementation = ErrorMessage.class))),
      @ApiResponse(responseCode = "422", description = "Invalid or incorrectly formatted input data.", 
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
  })
  @PutMapping
  @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT')")
  public ResponseEntity<UserSystemVIEW> update(@RequestBody @Valid UserSystemUpdateFORM userSystemUpdateForm) {
    UserSystemVIEW user = userService.update(userSystemUpdateForm);
    return ResponseEntity.ok().body(user);
  }

  @Operation(summary = "List all registered users", description = "Request requires a Bearer Token. Access restricted to logged in ADMIN", 
  security = @SecurityRequirement(name = "security"), responses = {
      @ApiResponse(responseCode = "200", description = "List of all registered users", 
      content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserSystemVIEW.class)))),
      @ApiResponse(responseCode = "403", description = "User without permission to access this resource.", 
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
  })
  @GetMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<List<UserSystemVIEW>> getAll(Pageable pageable) {
    List<UserSystemVIEW> users = userService.searchAll(pageable);
    return ResponseEntity.ok(users);
  }

  @Operation(summary = "Retrieve scores", description = "Request requires a Bearer Token. Access restricted to logged in ADMIN OR CLIENT", 
  security = @SecurityRequirement(name = "security"), responses = {
      @ApiResponse(responseCode = "200", description = "Scores retrieved successfully."),
      @ApiResponse(responseCode = "403", description = "User without permission to access this resource.", 
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
      @ApiResponse(responseCode = "422", description = "Invalid or incorrectly formatted fields.", 
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
  })
  @GetMapping("{id}/scores")
  @PreAuthorize("hasRole('ADMIN') OR ( hasRole('CLIENT') AND #id == authentication.principal.id )")
  public ResponseEntity<?> getScores(@PathVariable Long id, Pageable pageable) {
    Page<ScoreVIEW> scores = scoreService.findAllByUser(id, pageable);
    return ResponseEntity.ok().body(scores);
  }

  @Operation(summary = "Delete user", description = "Request requires a Bearer Token. Access restricted to logged in ADMIN", 
  security = @SecurityRequirement(name = "security"), responses = {
      @ApiResponse(responseCode = "204", description = "User deleted successfully."),
      @ApiResponse(responseCode = "403", description = "User without permission to access this resource.", 
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
      @ApiResponse(responseCode = "404", description = "User not found.", 
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
  })
  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "Confirm or deactivate user registration", description = "Request requires a Bearer Token. Access restricted to logged in ADMIN. This endpoint activates or deactivates a user based on the provided code.", 
  security = @SecurityRequirement(name = "security"), responses = {
      @ApiResponse(responseCode = "204", description = "User registration status updated successfully."),
      @ApiResponse(responseCode = "400", description = "Invalid code provided.", 
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
      @ApiResponse(responseCode = "403", description = "User without permission to access this resource.", 
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
      @ApiResponse(responseCode = "404", description = "User not found or code invalid.", 
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
  })
  @GetMapping("/confirm/register")
//  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Void> Registrationconfirmationresponse(@RequestParam("code") String code) {
    userService.activateUserRegistration(code);
    return ResponseEntity.noContent().build();
  }

}