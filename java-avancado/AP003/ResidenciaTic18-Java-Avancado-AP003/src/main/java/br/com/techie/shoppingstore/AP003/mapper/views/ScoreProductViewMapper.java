package br.com.techie.shoppingstore.AP003.mapper.views;

import br.com.techie.shoppingstore.AP003.dto.view.ScoreVIEW;
import br.com.techie.shoppingstore.AP003.mapper.Mapper;
import br.com.techie.shoppingstore.AP003.model.Score;
import org.springframework.stereotype.Component;

@Component
public class ScoreProductViewMapper implements Mapper<Score, ScoreVIEW> {
    @Override
    public ScoreVIEW map(Score i) {
        return new ScoreVIEW(
                i.getUser().getUsername(),
                i.getValue(),
                i.getComment()
        );
    }
}
