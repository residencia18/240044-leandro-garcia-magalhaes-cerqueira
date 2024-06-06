package br.com.techie.shoppingstore.AP003.infra.jwt;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.techie.shoppingstore.AP003.model.UserSystem;
import br.com.techie.shoppingstore.AP003.service.UserSystemService;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserSystemService userSystemService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserSystem usuario = userSystemService.searchByEmail(email);
        return new JwtUserDetails(usuario);
    }

    public JwtToken getTokenAuthenticated(String email) {
        UserSystem.Role role = userSystemService.searchRoleByEmail(email);
        return JwtUtils.createToken(email, role.name().substring("ROLE_".length()));
    }
}
