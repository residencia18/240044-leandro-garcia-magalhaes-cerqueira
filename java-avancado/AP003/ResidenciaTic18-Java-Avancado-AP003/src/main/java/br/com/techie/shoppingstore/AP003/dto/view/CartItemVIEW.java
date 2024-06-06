package br.com.techie.shoppingstore.AP003.dto.view;

import java.math.BigDecimal;

public record CartItemVIEW(
        Long id,
        ProductVIEW product,
        BigDecimal price,
        Integer quantity
) { }
