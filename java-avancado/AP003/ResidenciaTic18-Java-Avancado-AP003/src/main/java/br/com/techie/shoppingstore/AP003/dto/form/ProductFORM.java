package br.com.techie.shoppingstore.AP003.dto.form;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductFORM(
        @NotBlank(message = "Name is required!")
        String name,

        @NotBlank(message = "Category ID is required!")
        Long category_id,

        @NotNull(message = "Price is required!")
        @DecimalMin(value = "0.01", message = "Price must be greater than or equal to 0.01")
        BigDecimal price,
        String description,

        @NotNull(message = "Stock is required!")
        @Min(value = 0, message = "Stock must be greater than or equal to 0")
        Integer stock,
        String url_image,
        String chassis,
        String cpu,
        String operational_system,
        String chipset,
        String memory,
        String slots,
        String storage,
        String network
) { }
