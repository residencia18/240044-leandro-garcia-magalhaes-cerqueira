package br.com.techie.shoppingstore.AP003.mapper.updates;

import br.com.techie.shoppingstore.AP003.dto.form.CategoryUpdateFORM;
import br.com.techie.shoppingstore.AP003.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryUpdateMapper {
    public Category map(CategoryUpdateFORM form, Category oldCategory){
        oldCategory.setName(form.name());
        return oldCategory;
    }
}
