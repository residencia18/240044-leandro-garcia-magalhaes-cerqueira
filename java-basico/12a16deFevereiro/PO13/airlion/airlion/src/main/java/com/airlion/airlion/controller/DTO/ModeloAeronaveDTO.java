package com.airlion.airlion.controller.DTO;

import com.airlion.airlion.modelo.ModeloAeronave;

public class ModeloAeronaveDTO {
	
	private String fabricante;
	private String nome;
	
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public ModeloAeronaveDTO() {
		super();
	}
	
	public ModeloAeronaveDTO(ModeloAeronave modeloAeronave) {
		super();
		this.fabricante = modeloAeronave.getFabricante();
		this.nome = modeloAeronave.getNome();
	}
	
	
	
	
	
	
	

}
