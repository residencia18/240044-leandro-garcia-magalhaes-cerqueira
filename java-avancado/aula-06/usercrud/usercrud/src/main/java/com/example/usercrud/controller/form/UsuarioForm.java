package com.example.usercrud.controller.form;
import com.example.usercrud.model.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioForm {
	
	//Atributos
	private String nome;
	private String senha;
	private String email;

	
	//Métodos
	public Usuario toUsuario() {
		Usuario usuario = new Usuario();
		
		usuario.setNome(nome);
		usuario.setSenha(senha);
		usuario.setEmail(email);
		
		return usuario; 
	}

}

