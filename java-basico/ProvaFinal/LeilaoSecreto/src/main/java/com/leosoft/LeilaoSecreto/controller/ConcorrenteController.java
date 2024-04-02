package com.leosoft.LeilaoSecreto.controller;

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

import com.leosoft.LeilaoSecreto.controller.DTO.ConcorrenteDTO;
import com.leosoft.LeilaoSecreto.controller.form.ConcorrenteForm;
import com.leosoft.LeilaoSecreto.model.Concorrente;
import com.leosoft.LeilaoSecreto.repository.ConcorrenteRepository;

@RestController
@RequestMapping("/concorrente/")
public class ConcorrenteController {

	@Autowired
	private ConcorrenteRepository concorrenteRepository;

	// Lista todos os Concorrentes disponiveis
	@GetMapping
	public List<ConcorrenteDTO> listaConcorrentes() {
		List<Concorrente> listaConcorrentes = new ArrayList<>();

		listaConcorrentes = (ArrayList<Concorrente>) concorrenteRepository.findAll();

		List<ConcorrenteDTO> lista = new ArrayList<ConcorrenteDTO>();

		for (Concorrente l : listaConcorrentes) {
			ConcorrenteDTO ld = new ConcorrenteDTO(l);
			lista.add(ld);
		}
		return lista;
	}

	// Busca um concorrente pelo id
	@GetMapping("/{id}")
	public ResponseEntity<?> listaConcorrentes(@PathVariable Long id) {
		try {
			Concorrente concorrente = concorrenteRepository.getReferenceById(id);
			ConcorrenteDTO concorrenteDTO = new ConcorrenteDTO(concorrente);
			return ResponseEntity.ok(concorrenteDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	// Adiciona um leilão ao banco de dados
	@PostMapping
	public ResponseEntity<ConcorrenteDTO> inserir(@RequestBody ConcorrenteForm concorrenteForm, UriComponentsBuilder uriBuilder) {
		try {
			Concorrente concorrente = concorrenteForm.toConcorrente();
			concorrenteRepository.save(concorrente);
			ConcorrenteDTO concorrenteDTO = new ConcorrenteDTO(concorrente);
			uriBuilder.path("/concorrente/{id}");
			URI uri = uriBuilder.buildAndExpand(concorrente.getId()).toUri();
			return ResponseEntity.created(uri).body(concorrenteDTO);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> alterar(@PathVariable Long id, @RequestBody ConcorrenteForm concorrenteForm) {
		try {
			Concorrente concorrente = concorrenteRepository.getReferenceById(id);
			concorrente.setNome(concorrenteForm.getNome());
			concorrente.setCpf(concorrenteForm.getCpf());
			concorrenteRepository.save(concorrente);
			ConcorrenteDTO concorrenteDTO = new ConcorrenteDTO(concorrente);
			return ResponseEntity.ok(concorrenteDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		try {
			Concorrente concorrente = concorrenteRepository.getReferenceById(id);
			concorrenteRepository.delete(concorrente);
			ConcorrenteDTO concorrenteDTO = new ConcorrenteDTO(concorrente);
			return ResponseEntity.ok(concorrenteDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

}
