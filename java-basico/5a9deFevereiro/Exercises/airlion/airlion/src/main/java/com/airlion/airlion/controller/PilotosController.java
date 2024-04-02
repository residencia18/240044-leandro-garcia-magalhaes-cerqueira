package com.airlion.airlion.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.airlion.airlion.controller.DTO.PilotoDTO;
import com.airlion.airlion.controller.form.PilotoForm;
import com.airlion.airlion.modelo.Piloto;
import com.airlion.airlion.repository.PilotoRepository;


@RestController
@RequestMapping("/pilotos/")
public class PilotosController {

	@Autowired
	private PilotoRepository pilotoRepository;

	@GetMapping
	public List<Piloto> listaPilotos(String nome) {
		List<Piloto> listaPilotos = new ArrayList<>();

		// Verifica se o parametro é nulo
		if (nome == null) {
			listaPilotos = (ArrayList<Piloto>) pilotoRepository.findAll();

		} else {
			listaPilotos = (ArrayList<Piloto>) pilotoRepository.findBynome(nome);
		}
		return listaPilotos;
	}
	
	
	@PostMapping
	public ResponseEntity<PilotoDTO> inserir (@RequestBody PilotoForm pilotoForm, UriComponentsBuilder uriBuilder){
		Piloto piloto = pilotoForm.toPiloto();
		pilotoRepository.save(piloto);
		
		PilotoDTO pilotoDTO = new PilotoDTO(piloto);
		
		uriBuilder.path("/pilotos/{id}");
		
		URI uri = uriBuilder.buildAndExpand(piloto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(pilotoDTO);	
	}

}