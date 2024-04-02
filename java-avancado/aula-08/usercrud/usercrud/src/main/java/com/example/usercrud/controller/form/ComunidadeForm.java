package com.example.usercrud.controller.form;

import com.example.usercrud.model.Comunidade;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ComunidadeForm {
	
	//Atributos
	private String nome;
	private String descricao;
	
	//Métodos
	public Comunidade toComunidade() {
		Comunidade comunidade = new Comunidade();
		
		comunidade.setNome(nome);
		comunidade.setDescricao(descricao);
		
		return comunidade;
		
	}

}
