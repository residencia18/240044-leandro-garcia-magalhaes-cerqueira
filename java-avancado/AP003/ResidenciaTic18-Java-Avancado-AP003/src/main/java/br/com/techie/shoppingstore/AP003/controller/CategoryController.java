package br.com.techie.shoppingstore.AP003.controller;

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

import br.com.techie.shoppingstore.AP003.dto.form.CategoryFORM;
import br.com.techie.shoppingstore.AP003.dto.form.CategoryUpdateFORM;
import br.com.techie.shoppingstore.AP003.dto.view.CategoryVIEW;
import br.com.techie.shoppingstore.AP003.infra.exception.ErrorMessage;
import br.com.techie.shoppingstore.AP003.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Categories", description = "Contains all operations for registering, editing, and reading a category.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "Create a new category", description = "Creates a new category with the provided details.", responses = {
            @ApiResponse(responseCode = "201", description = "Category created successfully.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryVIEW.class))),
            @ApiResponse(responseCode = "422", description = "Invalid input data.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
    })
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryVIEW> create(@RequestBody CategoryFORM categoryForm) {
        CategoryVIEW category = categoryService.insert(categoryForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @Operation(summary = "List all categories", description = "Retrieve a paginated list of all categories.", responses = {
            @ApiResponse(responseCode = "200", description = "Category list retrieved successfully.", 
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CategoryVIEW.class)))),
    })
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT')")
    public ResponseEntity<Page<CategoryVIEW>> getAll(Pageable pageable) {
        Page<CategoryVIEW> categories = categoryService.findAllPaged(pageable);
        return ResponseEntity.ok().body(categories);
    }

    @Operation(summary = "Retrieve category by ID", description = "Retrieves the details of a specific category by its ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Category successfully recovered.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryVIEW.class))),
            @ApiResponse(responseCode = "404", description = "Category not found.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT')")
    public ResponseEntity<CategoryVIEW> getById(@PathVariable Long id) {
        CategoryVIEW category = categoryService.findById(id);
        return ResponseEntity.ok().body(category);
    }

    @Operation(summary = "Update category", description = "Updates the details of an existing category.", responses = {
            @ApiResponse(responseCode = "200", description = "Category updated successfully.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryVIEW.class))),
            @ApiResponse(responseCode = "404", description = "Category not found.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "422", description = "Invalid or incorrectly formatted input data.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryVIEW> update(@RequestBody @Valid CategoryUpdateFORM categoryUpdateForm) {
        CategoryVIEW category = categoryService.update(categoryUpdateForm);
        return ResponseEntity.ok().body(category);
    }

    @Operation(summary = "Delete category", description = "Delete an existing category by its ID.", responses = {
            @ApiResponse(responseCode = "204", description = "Category deleted successfully."),
            @ApiResponse(responseCode = "404", description = "Category not found.", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
