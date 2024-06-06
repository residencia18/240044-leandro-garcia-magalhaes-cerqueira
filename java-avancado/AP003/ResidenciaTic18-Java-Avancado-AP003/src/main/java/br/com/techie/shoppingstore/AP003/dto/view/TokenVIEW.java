package br.com.techie.shoppingstore.AP003.dto.view;

public record TokenVIEW(
    Long id,
    String token,
    UserSystemVIEW userSystem
) { }
