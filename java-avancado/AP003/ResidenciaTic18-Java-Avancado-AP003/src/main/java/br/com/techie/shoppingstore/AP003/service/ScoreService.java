package br.com.techie.shoppingstore.AP003.service;

import br.com.techie.shoppingstore.AP003.dto.form.ScoreFORM;
import br.com.techie.shoppingstore.AP003.dto.view.ScoreVIEW;
import br.com.techie.shoppingstore.AP003.infra.exception.EntityNotFoundException;
import br.com.techie.shoppingstore.AP003.mapper.forms.ScoreFormMapper;
import br.com.techie.shoppingstore.AP003.mapper.views.ScoreProductViewMapper;
import br.com.techie.shoppingstore.AP003.mapper.views.ScoresUserViewMapper;
import br.com.techie.shoppingstore.AP003.model.Score;
import br.com.techie.shoppingstore.AP003.repository.ProductRepository;
import br.com.techie.shoppingstore.AP003.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ScoreFormMapper scoreFormMapper;
    
    @Autowired
    private ScoreProductViewMapper scoreProductViewMapper;
    
    @Autowired
    private ScoresUserViewMapper scoresUserViewMapper;
    
    @Transactional
    @CacheEvict(cacheNames = {"products", "scoresUser", "scoresProduct"}, allEntries = true)
    public ScoreVIEW insert(ScoreFORM scoreFORM){
        Score score = scoreFormMapper.map(scoreFORM);
        scoreRepository.save(score);
        return scoreProductViewMapper.map(score);
    }
    
    @Cacheable("scoresUser")
    @Transactional(readOnly = true)
    public Page<ScoreVIEW> findAllByUser(Long userId, Pageable pageable){
        return scoreRepository.findAllByUserId(userId, pageable).map( x -> scoresUserViewMapper.map(x));
    }

    @Cacheable("scoresProduct")
    @Transactional(readOnly = true)
    public Page<ScoreVIEW> findAllByProduct(long productId, Pageable pageable){
        return scoreRepository.findAllByProductId(productId, pageable).map(x -> scoreProductViewMapper.map(x));
    }

    @Transactional
    @CacheEvict(cacheNames = {"products", "scoresUser", "scoresProduct"}, allEntries = true)
    public void delete(Long id, Long scoreId) throws EntityNotFoundException{
        if(!productRepository.existsById(id)) throw new EntityNotFoundException("Product not found!");
        scoreRepository.deleteById(id);
    }

}
