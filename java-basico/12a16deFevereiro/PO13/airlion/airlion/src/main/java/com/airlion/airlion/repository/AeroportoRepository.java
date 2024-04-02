package com.airlion.airlion.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.airlion.airlion.modelo.Aeroporto;

public interface AeroportoRepository extends JpaRepository<Aeroporto, Long> {
	
	public List<Aeroporto> findByicao(String fabricante);
	public List<Aeroporto> findBynome(String nome);

}
