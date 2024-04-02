package com.example.usercrud.controller.DTO;

import java.util.Set;

import com.example.usercrud.model.Comunidade;
import com.example.usercrud.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
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

	public UsuarioDTO(Usuario usuario) {
		super();
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
	}
	
}
