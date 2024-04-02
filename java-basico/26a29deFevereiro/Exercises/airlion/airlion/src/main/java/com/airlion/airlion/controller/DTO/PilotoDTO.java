package com.airlion.airlion.controller.DTO;

import com.airlion.airlion.modelo.Piloto;

public class PilotoDTO {
	
	private Long id;
	private String nome;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public PilotoDTO() {
		super();
	}
	
	public PilotoDTO(Piloto piloto) {
		super();
		this.id = piloto.getId();
		this.nome = piloto.getNome();
	}
	
	
	
	

}
