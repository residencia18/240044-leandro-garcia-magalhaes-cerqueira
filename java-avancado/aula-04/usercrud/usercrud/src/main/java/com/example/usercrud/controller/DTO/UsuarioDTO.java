package com.example.usercrud.controller.DTO;

import java.util.Set;

import com.example.usercrud.model.Comunidade;
import com.example.usercrud.model.Usuario;

public class UsuarioDTO {
	
	private Long id;
	private String nome;
	private String email;
	
	
	private Set<Comunidade> comunidades;
	
	
	public Set<Comunidade> getComunidades() {
		return comunidades;
	}
	public void setComunidades(Set<Comunidade> comunidades) {
		this.comunidades = comunidades;
	}
	public Long getId() {
		return id;
	}
	public String getnome() {
		return nome;
	}
	public String getEmail() {
		return email;
	}
	public UsuarioDTO() {
		super();
	}
	public UsuarioDTO(Usuario usuario) {
		super();
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
	}
	
	@Override
	public String toString() {
		return "UsuarioDTO [id=" + id + ", nome=" + nome + ", email=" + email + "]";
	}
	
	
	

}
