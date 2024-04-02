package com.example.usercrud.controller;


import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usercrud.UsercrudApplication;
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
	public String createComunidade(@RequestBody Comunidade entity) {
		log.info("Criando comunidade...");

		// new Comunidade
		Comunidade comunidade = new Comunidade(entity.getNome(), entity.getDescricao());

		// save Comunidade
		comunidade = comunidadeRepository.save(comunidade);
		log.info("Comunidade criada: " + comunidade);
		return "Comunidade criada com sucesso!!!";
		
	}

	@PostMapping("/criaComunidadeParaUsuarios")
	public String createComunidadeForUsuario(@RequestBody Comunidade entity) {
		
		log.info("\nCria um nova Comunidade e adiciona Usuarios existentes dentro da Comunidade." + "\n");
		// get first Usuario
		int userId = 1;
		Usuario usuario1 = this.usuarioRepository.getReferenceById((long) userId);
		log.info("\nDetalhes do usuário :: " + usuario1.toString() + "\n");

		// get second Usuario
		userId = 2;
		Usuario usuario2 = this.usuarioRepository.getReferenceById((long) userId);
		log.info("\nDetalhes do usuário :: " + usuario2.toString() + "\n");

		// new Comunidade
		Comunidade comunidade = new Comunidade(entity.getNome(), entity.getDescricao());

		// create Usuario set
		Set<Usuario> membros = new HashSet<>();
		membros.add(usuario1);
		membros.add(usuario2);

		// atribui membro a Comunidade
		comunidade.setMembros(membros);

		// save Comunidade
		comunidade = comunidadeRepository.save(comunidade);
		log.info("Comunidade salva: " + comunidade);
		return "Comunidade salva com sucesso!!!";
	}

	@PostMapping("/atribuiComunidadeAUsuarios/{comunidadeId}/{userId}")
	public String assignComunidadeToUsuarios(@PathVariable(name = "comunidadeId") Long comunidadeId, @PathVariable(name = "userId") Long userId) {
		log.info("\nBusque a Comunidade existente e adicione o Usuário existente a esta Comunidade." + "\n");

		// get Usuario
		Usuario usuario = usuarioRepository.getReferenceById(userId);
		log.info("\nDetalhes do usuário :: " + usuario.toString() + "\n");

		// new Comunidade
		Comunidade comunidade = this.comunidadeRepository.getReferenceById(comunidadeId);
		log.info("\nDetalhes da comunidade :: " + comunidade.toString() + "\n");

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
	public String buscaComunidade(@PathVariable(name = "comunidadeId") Long comunidadeId) {
		log.info("Buscar Comunidade e seus Usuários." + "\n");

		// get Comunidade details
		Comunidade comunidade = this.comunidadeRepository.getReferenceById(comunidadeId);
		log.info("\nDetalhes da Comunidade :: " + comunidade.toString() + "\n");
		log.info("\nDetalhes dos Usuarios :: " + comunidade.getMembros() + "\n");
	
		log.info("Feito!!!\n");

		return "Comunidade obtida com sucesso!!!";
	}

}
