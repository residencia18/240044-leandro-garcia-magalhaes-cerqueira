package br.com.techie.shoppingstore.AP003.infra.jwt;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import br.com.techie.shoppingstore.AP003.model.UserSystem;

public class JwtUserDetails extends User {

    private UserSystem usuario;

    public JwtUserDetails(UserSystem usuario) {
        super(usuario.getEmail(), usuario.getPassword(), AuthorityUtils.createAuthorityList(usuario.getRole().name()));
        this.usuario = usuario;
    }

    public Long getId() {
        return this.usuario.getId();
    }

    public String getRole() {
        return this.usuario.getRole().name();
    }
}
