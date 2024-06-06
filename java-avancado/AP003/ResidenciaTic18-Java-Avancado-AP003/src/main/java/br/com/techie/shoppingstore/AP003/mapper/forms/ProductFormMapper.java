package br.com.techie.shoppingstore.AP003.mapper.forms;

import br.com.techie.shoppingstore.AP003.dto.form.ProductFORM;
import br.com.techie.shoppingstore.AP003.mapper.Mapper;
import br.com.techie.shoppingstore.AP003.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductFormMapper implements Mapper<ProductFORM, Product> {
    @Override
    public Product map(ProductFORM i) {
        return new Product(
                null,
                i.name(),
                i.price(),
                i.description(),
                i.stock(),
                i.url_image(),
                i.chassis(),
                i.cpu(),
                i.operational_system(),
                i.chipset(),
                i.memory(),
                i.slots(),
                i.storage(),
                i.network(),
                null,
                null
        );
    }
}
