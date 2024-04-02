package com.example.usercrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.usercrud.model.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

}
