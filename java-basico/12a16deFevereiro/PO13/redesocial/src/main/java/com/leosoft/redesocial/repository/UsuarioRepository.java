package com.leosoft.redesocial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leosoft.redesocial.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
	
	public List<Usuario> findBynome(String nome);
	
}
