package br.com.techie.shoppingstore.AP003.mapper.forms;

import br.com.techie.shoppingstore.AP003.dto.form.CategoryFORM;
import br.com.techie.shoppingstore.AP003.mapper.Mapper;
import br.com.techie.shoppingstore.AP003.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryFormMapper implements Mapper<CategoryFORM, Category> {
    @Override
    public Category map(CategoryFORM i) {
        return new Category(
                null,
                i.name()
        );
    }
}
