package br.com.techie.shoppingstore.AP003.mapper.views;

import br.com.techie.shoppingstore.AP003.dto.view.UserSystemVIEW;
import br.com.techie.shoppingstore.AP003.mapper.Mapper;
import br.com.techie.shoppingstore.AP003.model.UserSystem;
import org.springframework.stereotype.Component;

@Component
public class UserSystemViewMapper implements Mapper<UserSystem, UserSystemVIEW> {
    @Override
    public UserSystemVIEW map(UserSystem i) {
        return new UserSystemVIEW(
                i.getId(),
                i.getUsername(),
                i.getEmail(),
                i.getRole());
    }
}
