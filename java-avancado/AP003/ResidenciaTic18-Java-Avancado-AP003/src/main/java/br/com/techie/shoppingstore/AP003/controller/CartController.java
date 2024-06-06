package br.com.techie.shoppingstore.AP003.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import br.com.techie.shoppingstore.AP003.dto.form.CartFORM;
import br.com.techie.shoppingstore.AP003.dto.form.CartUpdateFORM;
import br.com.techie.shoppingstore.AP003.dto.view.CartVIEW;
import br.com.techie.shoppingstore.AP003.infra.exception.ErrorMessage;
import br.com.techie.shoppingstore.AP003.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "carts", description = "Contains all operations for registering, editing and reading shopping carts.")
@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @Operation(summary = "List all shopping carts", description = "Retrieve a paginated list of all shopping carts.", 
    security = @SecurityRequirement(name = "security"), responses = {
            @ApiResponse(responseCode = "200", description = "Cart list successfully retrieved.", 
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CartVIEW.class)))),
            @ApiResponse(responseCode = "403", description = "User without permission to access this resource.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
    })
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Page<CartVIEW>> getAll(Pageable pageable) {
        Page<CartVIEW> carts = cartService.findAllPaged(pageable);
        return ResponseEntity.ok().body(carts);
    }

    @Operation(summary = "List all unpaid shopping carts", description = "Retrieve a paginated list of all unpaid shopping carts.", 
    security = @SecurityRequirement(name = "bearerAuth"), responses = {
            @ApiResponse(responseCode = "200", description = "Unpaid cart list successfully retrieved.", 
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CartVIEW.class)))),
            @ApiResponse(responseCode = "403", description = "Access denied. Only ADMIN and CLIENT roles can access this resource.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @GetMapping("/paid")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Page<CartVIEW>> getAllPaid(Pageable pageable) {
        Page<CartVIEW> carts = cartService.findAllPaidPaged(pageable);
        return ResponseEntity.ok().body(carts);
    }

    @Operation(summary = "List all unpaid shopping carts", description = "Retrieve a paginated list of all unpaid shopping carts.", 
    security = @SecurityRequirement(name = "bearerAuth"), responses = {
            @ApiResponse(responseCode = "200", description = "Unpaid cart list successfully retrieved.", 
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CartVIEW.class)))),
            @ApiResponse(responseCode = "403", description = "Access denied. Only ADMIN and CLIENT roles can access this resource.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @GetMapping("/unpaid")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Page<CartVIEW>> getAllUnpaid(Pageable pageable) {
        Page<CartVIEW> carts = cartService.findAllUnpaidPaged(pageable);
        return ResponseEntity.ok().body(carts);
    }

    @Operation(summary = "Recover cart by ID", description = "Retrieves the details of a specific cart by its ID.", 
    security = @SecurityRequirement(name = "security"), responses = {
            @ApiResponse(responseCode = "200", description = "Cart successfully recovered.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CartVIEW.class))),
            @ApiResponse(responseCode = "403", description = "User without permission to access this resource.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "404", description = "Cart not found.", content = @Content(mediaType = "application/json", 
            schema = @Schema(implementation = ErrorMessage.class)))
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT')")
    public ResponseEntity<CartVIEW> getById(@PathVariable Long id) {
        CartVIEW cart = cartService.findById(id);
        return ResponseEntity.ok().body(cart);
    }

    @Operation(summary = "Create a new cart", description = "Create a new shopping cart with the details provided.", responses = {
            @ApiResponse(responseCode = "201", description = "Cart created successfully.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CartVIEW.class))),
            @ApiResponse(responseCode = "422", description = "Invalid input data.", content = @Content(mediaType = "application/json", 
            schema = @Schema(implementation = ErrorMessage.class))),
    })
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT')")
    public ResponseEntity<CartVIEW> create(@RequestBody CartFORM cartForm) {
        CartVIEW novoCart = cartService.insert(cartForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCart);
    }

    @Operation(summary = "Update Cart", description = "Updates the details of an existing cart.", 
    security = @SecurityRequirement(name = "security"), responses = {
            @ApiResponse(responseCode = "200", description = "Cart updated successfully.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CartVIEW.class))),
            @ApiResponse(responseCode = "403", description = "User not allowed to access this feature.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "422", description = "Invalid or incorrectly formatted input data.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT')")
    public ResponseEntity<CartVIEW> update(@RequestBody CartUpdateFORM cartUpdateForm) {
        CartVIEW cart = cartService.update(cartUpdateForm);
        return ResponseEntity.ok().body(cart);
    }

    @Operation(summary = "Delete Cart", description = "Delete an existing cart by its ID.", 
    security = @SecurityRequirement(name = "security"), responses = {
            @ApiResponse(responseCode = "204", description = "Cart deleted successfully."),
            @ApiResponse(responseCode = "403", description = "User not allowed to access this feature.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "422", description = "Invalid or incorrectly formatted input data.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cartService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
