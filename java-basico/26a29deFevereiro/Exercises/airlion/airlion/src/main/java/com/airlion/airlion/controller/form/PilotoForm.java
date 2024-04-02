package com.airlion.airlion.controller.form;

import com.airlion.airlion.modelo.Piloto;

public class PilotoForm {
	
	private String nome;
	private String numbreve;
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumbreve() {
		return numbreve;
	}

	public void setNumbreve(String numbreve) {
		this.numbreve = numbreve;
	}
	
	public PilotoForm() {
		super();
	}

	public PilotoForm(String nome, String numbreve) {
		super();
		this.nome = nome;
		this.numbreve = numbreve;
	}

	//Métodos
	public Piloto toPiloto(){
		Piloto piloto = new Piloto();
		
		piloto.setNome(nome);
		piloto.setNumBreve(numbreve);
		
		return piloto;
	}
	
	
}
