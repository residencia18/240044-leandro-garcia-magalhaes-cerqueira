package com.example.usercrud.controller;


import java.util.HashSet;
import java.util.Set;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usercrud.UsercrudApplication;
import com.example.usercrud.controller.form.ComunidadeForm;
import com.example.usercrud.model.Comunidade;
import com.example.usercrud.model.Usuario;
import com.example.usercrud.repository.ComunidadeRepository;
import com.example.usercrud.repository.UsuarioRepository;


@RestController
@RequestMapping("/comunidade")
public class ComunidadeController {
	
	
	@Autowired
	private ComunidadeRepository comunidadeRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public static final Logger log = LoggerFactory.getLogger(UsercrudApplication.class);

	
	//POST METHODS -------------------------------------------------------------------->
	
	@PostMapping("/criaComunidade")
	public String createComunidade(@RequestBody ComunidadeForm entity) {
		log.info("Criando comunidade...");

		// new Comunidade
		Comunidade comunidade = entity.toComunidade();

		// save Comunidade
		comunidade = comunidadeRepository.save(comunidade);
		log.info("Comunidade criada: " + comunidade);
		return "Comunidade criada com sucesso!!!";
		
	}

	@PostMapping("/atribuiComunidadeAUsuarios/{comunidadeId}/{userId}")
	public String assignComunidadeToUsuarios(@PathVariable Long comunidadeId, @PathVariable Long userId) {
		log.info("\nBusque a Comunidade existente e adicione o Usuário existente a esta Comunidade." + "\n");

		// get Usuario
		Usuario usuario = usuarioRepository.getReferenceById(userId);
		log.info("\nDetalhes do usuário :: " + usuario+ "\n");

		// new Comunidade
		Comunidade comunidade =  comunidadeRepository.getReferenceById(comunidadeId);
		log.info("\nDetalhes da comunidade :: " + comunidade + "\n");

		// create Usuario set
		Set<Usuario> membros = new HashSet<>();
		membros.add(usuario);

		// assign Usuario Set to Comunidade
		comunidade.setMembros(membros);

		// save Comunidade
		comunidade = comunidadeRepository.save(comunidade);
		log.info("\nComunidade atribuida :: " + comunidade + "\n");

		return "Comunidade atribuida com sucesso!!!";
	}
	
	//GET METHODS -------------------------------------------------------------------->

	@GetMapping(value = "/comunidades/{comunidadeId}")
	public ResponseEntity<?> buscaComunidade(@PathVariable(name = "comunidadeId") Long comunidadeId) {
		log.info("Buscar Comunidade e seus Usuários." + "\n");

		// Obtenha a Comunidade e desproximo ela
	    Comunidade comunidade = comunidadeRepository.getReferenceById(comunidadeId);
	    if (comunidade != null) {
	    	log.info("\nDetalhes da Comunidade :: " + comunidade+ "\n");
	        comunidade = (Comunidade) Hibernate.unproxy(comunidade);
	    }
		log.info("\nDetalhes da Comunidade :: " + comunidade.toString() + "\n");
		log.info("\nDetalhes dos Usuarios :: " + comunidade.getMembros() + "\n");
	
		log.info("Feito!!!\n");

		return ResponseEntity.ok(comunidade);
	}

}
