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
import com.airlion.airlion.controller.DTO.AeroportoDTO;
import com.airlion.airlion.controller.form.AeroportoForm;
import com.airlion.airlion.modelo.Aeroporto;
import com.airlion.airlion.repository.AeroportoRepository;

@RestController
@RequestMapping("/aeroportos/")
public class AeroportosController {
	
	@Autowired
	private AeroportoRepository aeroportoRepository;
	
	@GetMapping
	public List<Aeroporto> listaAeroportos(String icao, String nome) {
		List<Aeroporto> listaAeroportos = new ArrayList<>();
		
		if(nome != null) {
			listaAeroportos = (ArrayList<Aeroporto>) aeroportoRepository.findBynome(nome);
		} else if (icao != null){
			listaAeroportos = (ArrayList<Aeroporto>) aeroportoRepository.findByicao(icao);
		} else {
			listaAeroportos =  (ArrayList<Aeroporto>) aeroportoRepository.findAll();
		}
			
		return listaAeroportos;	
	}
	
	
	@PostMapping
	public ResponseEntity<AeroportoDTO> inserir (@RequestBody AeroportoForm aeroportoForm, UriComponentsBuilder uriBuilder){
		Aeroporto aeroporto = aeroportoForm.toAeroporto();
		aeroportoRepository.save(aeroporto);
		
		AeroportoDTO aeroportoDTO = new AeroportoDTO(aeroporto);
		
		uriBuilder.path("/aeroportos/{id}");
		
		URI uri = uriBuilder.buildAndExpand(aeroporto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(aeroportoDTO);	
	}

	
	
}
