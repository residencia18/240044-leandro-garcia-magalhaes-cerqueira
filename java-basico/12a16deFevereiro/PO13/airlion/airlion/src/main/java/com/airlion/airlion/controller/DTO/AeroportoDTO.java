package com.airlion.airlion.controller.DTO;

import com.airlion.airlion.modelo.Aeroporto;

public class AeroportoDTO {
	
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
	
	public AeroportoDTO() {
		super();
	}
	public AeroportoDTO(Aeroporto aeroporto) {
		super();
		this.id = aeroporto.getId();
		this.nome = aeroporto.getNome();
	}
	
	
	
	
	
	

}
