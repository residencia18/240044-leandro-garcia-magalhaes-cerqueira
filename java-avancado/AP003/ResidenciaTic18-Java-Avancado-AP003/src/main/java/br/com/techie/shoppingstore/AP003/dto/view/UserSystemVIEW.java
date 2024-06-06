package br.com.techie.shoppingstore.AP003.dto.view;

import br.com.techie.shoppingstore.AP003.model.UserSystem;

public record UserSystemVIEW(
        Long id,
        String username,
        String email,
        UserSystem.Role role    
) { }
