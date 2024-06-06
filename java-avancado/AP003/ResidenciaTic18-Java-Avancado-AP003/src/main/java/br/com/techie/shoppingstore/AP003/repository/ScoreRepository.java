package br.com.techie.shoppingstore.AP003.repository;

import br.com.techie.shoppingstore.AP003.model.Score;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    Page<Score> findAllByProductId(Long productId, Pageable pageable);
    Page<Score> findAllByUserId(Long userId, Pageable pageable);
}
