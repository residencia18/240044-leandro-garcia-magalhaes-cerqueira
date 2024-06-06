package br.com.techie.shoppingstore.AP003.controller;

import br.com.techie.shoppingstore.AP003.dto.form.PaymentFORM;
import br.com.techie.shoppingstore.AP003.dto.view.PaymentVIEW;
import br.com.techie.shoppingstore.AP003.infra.exception.ErrorMessage;
import br.com.techie.shoppingstore.AP003.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "payments", description = "Contains all operations for registering, editing and reading payments.")
@RestController
@RequestMapping("/api/v1/cart/payment")
public class PaymentController {
  
  @Autowired
  private PaymentService paymentService;

  @Operation(summary = "List all payments", description = "Request requires a Bearer Token. Access restricted to logged in ADMIN", 
  security = @SecurityRequirement(name = "bearerAuth"), responses = {
      @ApiResponse(responseCode = "200", description = "List of all payments retrieved successfully", 
      content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PaymentVIEW.class)))),
      @ApiResponse(responseCode = "403", description = "User without permission to access this resource.", 
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
  })
  @GetMapping
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<Page<PaymentVIEW>> getAll(Pageable pageable) {
    Page<PaymentVIEW> payments = paymentService.findAllPaged(pageable);
    return ResponseEntity.ok().body(payments);
  }

  @Operation(summary = "Retrieve a payment by id", description = "Request requires a Bearer Token. Access restricted to logged in ADMIN OR CLIENT", 
  security = @SecurityRequirement(name = "bearerAuth"), responses = {
      @ApiResponse(responseCode = "200", description = "Payment resource retrieved successfully.", 
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentVIEW.class))),
      @ApiResponse(responseCode = "403", description = "User without permission to access this payment resource.", 
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
      @ApiResponse(responseCode = "404", description = "Payment resource not found.", 
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
  })
  @GetMapping("/{id}")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<PaymentVIEW> getById(@PathVariable Long id) {
    PaymentVIEW paymentVIEW = paymentService.findById(id);
    return ResponseEntity.ok().body(paymentVIEW);
  }

  @Operation(summary = "Create a new payment", description = "Feature to create a new payment in the system.", responses = {
      @ApiResponse(responseCode = "201", description = "Payment created successfully.", 
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentVIEW.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request. Invalid input data.", 
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
      @ApiResponse(responseCode = "409", description = "Conflict. Payment details already registered in the system.", 
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
      @ApiResponse(responseCode = "422", description = "Unprocessable Entity. The server understands the content type of the request entity, and the syntax of the request entity is correct, but it was unable to process the contained instructions.", 
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
  })
  @PostMapping
  @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT')")
  public ResponseEntity<PaymentVIEW> create(@RequestBody @Valid PaymentFORM paymentFORM) {
    PaymentVIEW newPayment = paymentService.insert(paymentFORM);
    return ResponseEntity.status(HttpStatus.CREATED).body(newPayment);
  }

  @Operation(summary = "Delete Payment", description = "Delete an existing payment by its ID.", 
    security = @SecurityRequirement(name = "bearerAuth"), responses = {
            @ApiResponse(responseCode = "204", description = "Payment deleted successfully."),
            @ApiResponse(responseCode = "403", description = "User not allowed to access this feature.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "404", description = "Payment not found or does not exist.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
  })
  @DeleteMapping("/{id}")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    paymentService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
