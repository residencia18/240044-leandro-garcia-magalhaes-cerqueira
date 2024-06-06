package br.com.techie.shoppingstore.AP003.mapper.views;

import br.com.techie.shoppingstore.AP003.dto.view.CategoryVIEW;
import br.com.techie.shoppingstore.AP003.mapper.Mapper;
import br.com.techie.shoppingstore.AP003.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryViewMapper implements Mapper<Category, CategoryVIEW> {
    @Override
    public CategoryVIEW map(Category i) {
        return new CategoryVIEW(
                i.getId(),
                i.getName()
        );
    }
}
