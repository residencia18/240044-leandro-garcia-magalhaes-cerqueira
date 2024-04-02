package com.airlion.airlion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airlion.airlion.modelo.Piloto;

public interface PilotoRepository extends JpaRepository<Piloto,Long> {
	
	public List<Piloto> findBynome(String nome);
}
