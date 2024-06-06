package br.com.techie.shoppingstore.AP003.mapper.views;

import br.com.techie.shoppingstore.AP003.dto.view.ProductVIEW;
import br.com.techie.shoppingstore.AP003.mapper.Mapper;
import br.com.techie.shoppingstore.AP003.model.Product;
import br.com.techie.shoppingstore.AP003.model.Score;
import br.com.techie.shoppingstore.AP003.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ProductViewMapper implements Mapper<Product, ProductVIEW> {
    @Autowired
    private ScoreRepository scoreRepository;

    @Override
    public ProductVIEW map(Product i) {
        Set<Score> scores = scoreRepository.findAllByProductId(i.getId(), Pageable.unpaged()).toSet();
        Double average = scores.stream().mapToDouble(Score::getValue).average().orElse(0);

        return new ProductVIEW(
                i.getId(),
                i.getName(),
                i.getCategory().getName(),
                i.getPrice(),
                i.getDescription(),
                average,
                i.getUrlImage(),
                i.getStock(),
                i.getChassis(),
                i.getCpu(),
                i.getOperationalSystem(),
                i.getChipset(),
                i.getMemory(),
                i.getSlots(),
                i.getStorage(),
                i.getNetwork()
        );
    }
}
