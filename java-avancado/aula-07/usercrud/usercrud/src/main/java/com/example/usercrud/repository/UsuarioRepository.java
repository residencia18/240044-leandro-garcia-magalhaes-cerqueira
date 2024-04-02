package com.example.usercrud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.usercrud.model.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
	
	//Esse é um método pré-existente da classe JpaRepository
	//O padrão é que depois de "findBy" contenha o nome exato do atributo (da classe model) para que o método funcione
	//Nesse caso, o nome do atributo é "nome"
	public List<Usuario> findByNome(String nome);

}
