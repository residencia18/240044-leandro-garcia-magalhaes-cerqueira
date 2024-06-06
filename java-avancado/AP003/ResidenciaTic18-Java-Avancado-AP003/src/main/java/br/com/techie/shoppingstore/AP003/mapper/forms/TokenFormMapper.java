package br.com.techie.shoppingstore.AP003.mapper.forms;

import org.springframework.stereotype.Component;

import br.com.techie.shoppingstore.AP003.dto.view.TokenVIEW;
import br.com.techie.shoppingstore.AP003.dto.view.UserSystemVIEW;
import br.com.techie.shoppingstore.AP003.model.Token;

@Component
public class TokenFormMapper {

    public TokenVIEW map(Token token, UserSystemVIEW user) {
        return new TokenVIEW(token.getId(), token.getToken(), user);
    }
}
