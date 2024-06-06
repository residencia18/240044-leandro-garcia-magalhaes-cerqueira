package br.com.techie.shoppingstore.AP003.mapper.updates;

import br.com.techie.shoppingstore.AP003.dto.form.ProductUpdateFORM;
import br.com.techie.shoppingstore.AP003.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductUpdateMapper {
    public Product map(ProductUpdateFORM form, Product oldProduct){
        oldProduct.setStock(form.stock());
        oldProduct.setName(form.name());
        oldProduct.setPrice(form.price());

        if(form.description() != null) oldProduct.setDescription(form.description());
        if(form.url_image() != null) oldProduct.setUrlImage(form.url_image());
        if(form.chassis() != null) oldProduct.setChassis(form.chassis());
        if(form.cpu() != null) oldProduct.setCpu(form.cpu());
        if(form.operational_system() != null) oldProduct.setOperationalSystem(form.operational_system());
        if(form.chipset() != null) oldProduct.setChipset(form.chipset());
        if(form.memory() != null) oldProduct.setMemory(form.memory());
        if(form.slots() != null) oldProduct.setSlots(form.slots());
        if(form.storage() != null) oldProduct.setStorage(form.storage());
        if(form.network() != null) oldProduct.setNetwork(form.network());

        return oldProduct;
    }
}
