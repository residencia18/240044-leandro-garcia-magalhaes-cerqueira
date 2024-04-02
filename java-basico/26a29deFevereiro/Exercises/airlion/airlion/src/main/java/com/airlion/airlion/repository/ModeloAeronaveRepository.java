package com.airlion.airlion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airlion.airlion.modelo.ModeloAeronave;

public interface ModeloAeronaveRepository extends JpaRepository<ModeloAeronave,Long> {
	
	public List<ModeloAeronave> findByfabricante(String fabricante);
	
	public List<ModeloAeronave> findBynome(String nome);
}
