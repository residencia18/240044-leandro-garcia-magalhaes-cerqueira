package br.com.techie.shoppingstore.AP003.mapper.updates;

import org.springframework.stereotype.Component;

import br.com.techie.shoppingstore.AP003.dto.form.UserSystemUpdateFORM;
import br.com.techie.shoppingstore.AP003.model.UserSystem;

@Component
public class UserSystemUpdateMapper {

  public UserSystem map(UserSystemUpdateFORM form, UserSystem oldUserSystem) {
    oldUserSystem.setUsername(form.username());
    oldUserSystem.setEmail(form.email());

    return oldUserSystem;
  }
}
