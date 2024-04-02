package com.leosoft.LeilaoSecreto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leosoft.LeilaoSecreto.model.Lance;

public interface LanceRepository extends JpaRepository <Lance, Long> {
	
	 // Método para encontrar todos os lances de um leilão específico pelo ID do leilão
    List<Lance> findByLeilao(Long idLeilao);

    // Método para encontrar todos os lances de um concorrente específico pelo ID do concorrente
    List<Lance> findByConcorrente(Long idConcorrente);
    
 // Método de consulta personalizado para encontrar o maior lance de um leilão específico
    Lance findFirstByLeilaoOrderByValorDesc(Long leilaoId);


}
