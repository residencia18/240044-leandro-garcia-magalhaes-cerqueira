package br.com.techie.shoppingstore.AP003.mapper.forms;

import br.com.techie.shoppingstore.AP003.dto.form.ScoreFORM;
import br.com.techie.shoppingstore.AP003.infra.exception.EntityNotFoundException;
import br.com.techie.shoppingstore.AP003.mapper.Mapper;
import br.com.techie.shoppingstore.AP003.model.Score;
import br.com.techie.shoppingstore.AP003.repository.ProductRepository;
import br.com.techie.shoppingstore.AP003.repository.UserSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScoreFormMapper implements Mapper<ScoreFORM, Score> {
    @Autowired
    private UserSystemRepository userSystemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Score map(ScoreFORM i) {
        return new Score(
                null,
                i.score(),
                i.comment(),
                userSystemRepository.findById(i.user_id()).orElseThrow(() -> new EntityNotFoundException("User not found")),
                productRepository.findById(i.product_id()).orElseThrow(() -> new EntityNotFoundException("Product not found"))
        );
    }
}
