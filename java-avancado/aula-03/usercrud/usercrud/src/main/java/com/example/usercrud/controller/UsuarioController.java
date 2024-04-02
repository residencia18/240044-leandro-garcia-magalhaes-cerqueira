package com.example.usercrud.controller;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;
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
import com.example.usercrud.controller.DTO.UsuarioDTO;
import com.example.usercrud.controller.form.UsuarioForm;


@RestController
@RequestMapping("/usuario/")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	public List<UsuarioDTO> listaUsuarios(String nome) {
		List<Usuario> listaUsuarios = new ArrayList<>();
		//Verifica se o parametro é nulo
		if (nome == null) {
			listaUsuarios = (ArrayList<Usuario>) usuarioRepository.findAll();
		} else {
			listaUsuarios = (ArrayList<Usuario>) usuarioRepository.findBynome(nome);
		}
		List<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();
		for (Usuario u : listaUsuarios) {
			UsuarioDTO ud = new UsuarioDTO(u);
			lista.add(ud);
		}
		return lista;
	}
			
	
	@GetMapping("/{id}")
	public ResponseEntity<?> listaUsuarios(@PathVariable Long id) {
		try {
		Usuario usuario = usuarioRepository.getReferenceById(id);
		UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
		return ResponseEntity.ok(usuarioDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> inserir (@RequestBody UsuarioForm usuarioForm, UriComponentsBuilder uriBuilder){
		Usuario usuario = usuarioForm.toUsuario();
		usuarioRepository.save(usuario);	
		UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);	
		uriBuilder.path("/usuario/{id}");	
		URI uri = uriBuilder.buildAndExpand(usuario.getId()).toUri();	
		return ResponseEntity.created(uri).body(usuarioDTO);	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> alterar(@PathVariable Long id,
				@RequestBody UsuarioForm usuarioForm){
		try {
			Usuario usuario = usuarioRepository.getReferenceById(id);
			usuario.setNome(usuarioForm.getNome());
			usuario.setEmail(usuarioForm.getEmail());
			usuario.setSenha(usuarioForm.getSenha());
			usuarioRepository.save(usuario);
			UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
			return ResponseEntity.ok(usuarioDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
					
				}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id){
		try {
			Usuario usuario = usuarioRepository.getReferenceById(id);
			usuarioRepository.delete(usuario);
			UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
			return ResponseEntity.ok(usuarioDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}	
	}
}
	
	