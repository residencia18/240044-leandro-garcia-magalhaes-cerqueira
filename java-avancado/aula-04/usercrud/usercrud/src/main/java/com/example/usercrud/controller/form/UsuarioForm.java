package com.example.usercrud.controller.form;

import com.example.usercrud.model.Usuario;

public class UsuarioForm {
	
	//Atributos
	private String nome;
	private String senha;
	private String email;
	
	//Getters e Setters
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	//Construtores
	public UsuarioForm() {
		super();
	}
	public UsuarioForm(String nome, String senha, String email) {
		super();
		this.nome = nome;
		this.senha = senha;
		this.email = email;
	}
	
	//Métodos
	public Usuario toUsuario() {
		Usuario usuario = new Usuario();
		
		usuario.setNome(nome);
		usuario.setSenha(senha);
		usuario.setEmail(email);
		
		return usuario; 
	}

}

