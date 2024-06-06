package br.com.techie.shoppingstore.AP003.dto.view;

import java.math.BigDecimal;

public record ProductVIEW(
        Long id,
        String name,
        String category,
        BigDecimal price,
        String description,
        Double score,
        String urlImage,
        Integer stock,
        String chassis,
        String cpu,
        String operational_system,
        String chipset,
        String memory,
        String slots,
        String storage,
        String network
) { }
