package com.airlion.airlion.controller.form;

import com.airlion.airlion.modelo.ModeloAeronave;

public class ModeloAeronaveForm {
	
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
	
	public ModeloAeronaveForm() {
		super();
	}
	public ModeloAeronaveForm(String fabricante, String nome) {
		super();
		this.fabricante = fabricante;
		this.nome = nome;
	}
	
	//Métodos

		public ModeloAeronave toModeloAeronave() {
			ModeloAeronave modeloAeronave = new ModeloAeronave();
			
			modeloAeronave.setFabricante(fabricante);
			modeloAeronave.setNome(nome);
			
			return modeloAeronave;
		}
	
	
	
	
	
	

}
