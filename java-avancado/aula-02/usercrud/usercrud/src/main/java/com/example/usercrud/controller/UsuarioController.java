package com.example.usercrud.controller;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.usercrud.model.Usuario;
import com.example.usercrud.repository.UsuarioRepository;



@RestController
@RequestMapping("/usuario/")
public class UsuarioController {
	

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	//O ideal é que retorne um DTO, já que nem todos os dados devem ser enviados para o usuário da aplicação
	public List<Usuario> listaUsuarios() {
		
		List<Usuario> listaUsuarios = new ArrayList<>();
		
			listaUsuarios = (ArrayList<Usuario>) usuarioRepository.findAll();

		List<Usuario> lista = new ArrayList<Usuario>();
		for (Usuario u : listaUsuarios) {
			Usuario usuario = new Usuario(u);
			lista.add(usuario);
		}
		return lista;
	}
			
	
	@GetMapping("/{id}")
	public ResponseEntity<?> listaUsuarios(@PathVariable Long id) {
		try {
		Usuario usuario = usuarioRepository.getReferenceById(id);
		Hibernate.initialize(usuario); // Força a inicialização do proxy Hibernate
		return ResponseEntity.ok(usuario);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<Usuario> inserir (@RequestBody Usuario usuario, UriComponentsBuilder uriBuilder){
		
		usuarioRepository.save(usuario);		
		uriBuilder.path("/usuario/{id}");	
		URI uri = uriBuilder.buildAndExpand(usuario.getId()).toUri();	
		return ResponseEntity.created(uri).body(usuario);	
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> alterar(@PathVariable Long id,
				@RequestBody Usuario usuario){
		try {
			usuario = usuarioRepository.getReferenceById(id);
			usuario.setName(usuario.getName());
			usuario.setEmail(usuario.getEmail());
			usuario.setSenha(usuario.getSenha());
			usuarioRepository.save(usuario);
			return ResponseEntity.ok(usuario);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
					
			}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id){
		try {
			Usuario usuario = usuarioRepository.getReferenceById(id);
			usuarioRepository.delete(usuario);
			return ResponseEntity.ok(usuario);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}	
	}
	
}
	


