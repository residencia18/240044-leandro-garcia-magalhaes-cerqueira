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
import com.airlion.airlion.controller.DTO.ModeloAeronaveDTO;
import com.airlion.airlion.controller.form.ModeloAeronaveForm;
import com.airlion.airlion.modelo.ModeloAeronave;
import com.airlion.airlion.repository.ModeloAeronaveRepository;

@RestController
@RequestMapping("/modeloaeronave/")
public class ModeloAeronaveController {
	
	@Autowired
	private ModeloAeronaveRepository modeloAeronaveRepository;
	
	@GetMapping
	public List<ModeloAeronave> listaModeloAeronaves(String nome, String fabricante) {
		List<ModeloAeronave> listaModeloAeronaves = new ArrayList<>();
		
		if (nome != null ) {
			listaModeloAeronaves = (ArrayList<ModeloAeronave>) modeloAeronaveRepository.findBynome(nome);		
		} else if (fabricante != null) {
			listaModeloAeronaves = (ArrayList<ModeloAeronave>) modeloAeronaveRepository.findByfabricante(fabricante);
	 	} else {
			listaModeloAeronaves = (ArrayList<ModeloAeronave>) modeloAeronaveRepository.findAll();
		}
		return listaModeloAeronaves;
	}
	
	@PostMapping
	public ResponseEntity<ModeloAeronaveDTO> inserir (@RequestBody ModeloAeronaveForm modeloAeronaveForm, UriComponentsBuilder uriBuilder){
		ModeloAeronave modeloAeronave = modeloAeronaveForm.toModeloAeronave();
		modeloAeronaveRepository.save(modeloAeronave);
		
		ModeloAeronaveDTO modeloAeronaveDTO = new ModeloAeronaveDTO(modeloAeronave);
		
		uriBuilder.path("/modeloaeronave/{id}");
		
		URI uri = uriBuilder.buildAndExpand(modeloAeronave.getId()).toUri();
		
		return ResponseEntity.created(uri).body(modeloAeronaveDTO);	
	}

}