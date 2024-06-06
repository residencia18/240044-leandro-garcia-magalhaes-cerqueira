package br.com.techie.shoppingstore.AP003.controller;

import br.com.techie.shoppingstore.AP003.dto.form.ScoreFORM;
import br.com.techie.shoppingstore.AP003.dto.view.ScoreVIEW;
import br.com.techie.shoppingstore.AP003.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.techie.shoppingstore.AP003.dto.form.ProductFORM;
import br.com.techie.shoppingstore.AP003.dto.form.ProductUpdateFORM;
import br.com.techie.shoppingstore.AP003.dto.view.ProductVIEW;
import br.com.techie.shoppingstore.AP003.infra.exception.ErrorMessage;
import br.com.techie.shoppingstore.AP003.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Products", description = "Contains all operations related to product management including creating, updating, retrieving, and deleting products.")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ScoreService scoreService;

    @Operation(summary = "Create a new product", description = "Create a new product with the provided details.",
    security = @SecurityRequirement(name = "security"), responses = {
            @ApiResponse(responseCode = "201", description = "Product created successfully.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductVIEW.class))),
            @ApiResponse(responseCode = "422", description = "Invalid input data.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "403", description = "User not allowed to access this feature.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
    })
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductVIEW> create(@RequestBody ProductFORM productForm) {
        ProductVIEW newProduct = productService.insert(productForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @Operation(summary = "List all products", description = "Retrieve a paginated list of all products.",
    security = @SecurityRequirement(name = "security"), responses = {
            @ApiResponse(responseCode = "200", description = "Product list successfully retrieved.", 
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductVIEW.class)))),
            @ApiResponse(responseCode = "403", description = "Access denied. Only ADMIN and CLIENT roles can access this resource.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT')")
    public ResponseEntity<Page<ProductVIEW>> getAll(Pageable pageable) {
        Page<ProductVIEW> products = productService.findAllPaged(pageable);
        return ResponseEntity.ok().body(products);
    }

    @Operation(summary = "Retrieve product by ID", description = "Retrieves the details of a specific product by its ID.", 
    security = @SecurityRequirement(name = "security"), responses = {
            @ApiResponse(responseCode = "200", description = "Product successfully recovered.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductVIEW.class))),
            @ApiResponse(responseCode = "404", description = "Product not found.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "403", description = "Access denied. Only ADMIN and CLIENT roles can access this resource.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT')")
    public ResponseEntity<ProductVIEW> getById(@PathVariable Long id) {
        ProductVIEW product = productService.findById(id);
        return ResponseEntity.ok().body(product);
    }

    @Operation(summary = "Update product", description = "Updates the details of an existing product.", 
    security = @SecurityRequirement(name = "security"), responses = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductVIEW.class))),
            @ApiResponse(responseCode = "404", description = "Product not found.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "422", description = "Invalid or incorrectly formatted input data.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "403", description = "User not allowed to access this feature.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductVIEW> update(@RequestBody @Valid ProductUpdateFORM productUpdateForm) {
        ProductVIEW product = productService.update(productUpdateForm);
        return ResponseEntity.ok().body(product);
    }

    @Operation(summary = "Delete product", description = "Delete an existing product by its ID.", 
    security = @SecurityRequirement(name = "security"), responses = {
            @ApiResponse(responseCode = "204", description = "Product deleted successfully."),
            @ApiResponse(responseCode = "404", description = "Product not found.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "403", description = "User not allowed to access this feature.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Create new score", description = "Request requires a Bearer Token. Access restricted to logged in ADMIN or CLIENT", 
    security = @SecurityRequirement(name = "security"), responses = {
            @ApiResponse(responseCode = "201", description = "Score created successfully.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ScoreVIEW.class))),
            @ApiResponse(responseCode = "400", description = "Invalid score data provided.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "403", description = "User without permission to access this resource.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "422", description = "Invalid or incorrectly formatted fields.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @PostMapping("/score")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT')")
    public ResponseEntity<ScoreVIEW> newScore(@RequestBody @Valid ScoreFORM scoreFORM){
        ScoreVIEW view = scoreService.insert(scoreFORM);
        return ResponseEntity.status(HttpStatus.CREATED).body(view);
    }

    @Operation(summary = "List all scores for a product", description = "Request requires a Bearer Token. Access restricted to logged in ADMIN or CLIENT", 
    security = @SecurityRequirement(name = "security"), responses = {
            @ApiResponse(responseCode = "200", description = "List of all scores for the specified product", 
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ScoreVIEW.class)))),
            @ApiResponse(responseCode = "403", description = "User without permission to access this resource.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
    })
    @GetMapping("/{id}/scores")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT')")
    public ResponseEntity<?> getAllScores(@PathVariable Long id, Pageable pageable){
        Page<ScoreVIEW> scores = scoreService.findAllByProduct(id, pageable);
        return ResponseEntity.ok().body(scores);
    }

    @Operation(summary = "Delete score", description = "Request requires a Bearer Token. Access restricted to logged in ADMIN", 
    security = @SecurityRequirement(name = "security"), responses = {
            @ApiResponse(responseCode = "204", description = "Score deleted successfully."),
            @ApiResponse(responseCode = "403", description = "User without permission to access this resource.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "404", description = "Score not found.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @DeleteMapping("{id}/scores/{scoreId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteScore(@PathVariable Long id, @PathVariable Long scoreId){
        scoreService.delete(id, scoreId);
        return ResponseEntity.noContent().build();
    }
}
