package com.leosoft.LeilaoSecreto.controller.DTO;

import com.leosoft.LeilaoSecreto.model.Concorrente;

public class ConcorrenteDTO {
	
	private String nome;
	
	public String getNome() {
		return nome;
	}
	
	public ConcorrenteDTO() {
		super();
	}

	public ConcorrenteDTO(Concorrente concorrente) {
		super();
		this.nome = concorrente.getNome();
	}
	
	

}
