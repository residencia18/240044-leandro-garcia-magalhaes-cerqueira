package com.leosoft.LeilaoSecreto.controller.form;

import com.leosoft.LeilaoSecreto.model.Concorrente;

public class ConcorrenteForm {
	
	private String nome;
	private String cpf;
	
	public ConcorrenteForm() {
		super();
	}
	
	public ConcorrenteForm(String nome, String cpf) {
		super();
		this.nome = nome;
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public Concorrente toConcorrente() {
		Concorrente concorrente = new Concorrente();
		concorrente.setNome(nome);
		concorrente.setCpf(cpf);
		
		return concorrente;
		
	}

}
