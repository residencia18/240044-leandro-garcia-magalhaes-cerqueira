package com.leosoft.redesocial.controller.DTO;

import com.leosoft.redesocial.modelo.Usuario;

public class UsuarioDTO {
	
	private Long id;
	private String name;
	private String email;
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
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
		this.name = usuario.getName();
		this.email = usuario.getEmail();
	}
	

}
