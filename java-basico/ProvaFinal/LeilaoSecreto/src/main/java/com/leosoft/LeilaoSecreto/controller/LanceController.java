package com.leosoft.LeilaoSecreto.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.leosoft.LeilaoSecreto.controller.DTO.LanceDTO;
import com.leosoft.LeilaoSecreto.controller.form.LanceForm;
import com.leosoft.LeilaoSecreto.model.Lance;
import com.leosoft.LeilaoSecreto.repository.LanceRepository;


@RestController
@RequestMapping("/lance/")
public class LanceController {

	@Autowired
	private LanceRepository lanceRepository;
	
	// Lista todos os Lances disponiveis
	@GetMapping
	public List<LanceDTO> listaLances() {
		List<Lance> listaLances = new ArrayList<>();

		listaLances = (ArrayList<Lance>) lanceRepository.findAll();

		List<LanceDTO> lista = new ArrayList<LanceDTO>();

		for (Lance l : listaLances) {
			LanceDTO ld = new LanceDTO(l);
			lista.add(ld);
		}
		return lista;
	}

	// Busca um Lance pelo id
	@GetMapping("/{id}")
	public ResponseEntity<?> listaLances(@PathVariable Long id) {
		try {
			Lance lance = lanceRepository.getReferenceById(id);
			LanceDTO lanceDTO = new LanceDTO(lance);
			return ResponseEntity.ok(lanceDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	
	@GetMapping("/lance/leilao={idLeilao}")
	public ResponseEntity<List<LanceDTO>> listaLancesPorLeilao(@PathVariable Long idLeilao) {
	    List<Lance> listaLances = lanceRepository.findByLeilao(idLeilao);

	    if (listaLances.isEmpty()) {
	        return ResponseEntity.notFound().build(); // Retorna 404 se não houver lances para o leilão
	    }

	    List<LanceDTO> lista = new ArrayList<>();
	    for (Lance lance : listaLances) {
	        LanceDTO lanceDTO = new LanceDTO(lance);
	        lista.add(lanceDTO);
	    }

	    return ResponseEntity.ok().body(lista); // Retorna 200 com a lista de lances do leilão
	}

	@GetMapping("/lance/concorrente={idConcorrente}")
	public ResponseEntity<List<LanceDTO>> listaLancesPorConcorrente(@PathVariable Long idConcorrente) {
	    List<Lance> listaLances = lanceRepository.findByConcorrente(idConcorrente);

	    if (listaLances.isEmpty()) {
	        return ResponseEntity.notFound().build(); // Retorna 404 se não houver lances para o concorrente
	    }

	    List<LanceDTO> lista = new ArrayList<>();
	    for (Lance lance : listaLances) {
	        LanceDTO lanceDTO = new LanceDTO(lance);
	        lista.add(lanceDTO);
	    }

	    return ResponseEntity.ok().body(lista); // Retorna 200 com a lista de lances do concorrente
	}

	// Adiciona um Lance ao banco de dados
	@PostMapping
	public ResponseEntity<LanceDTO> inserir(@RequestBody LanceForm lanceForm, UriComponentsBuilder uriBuilder) {
	    try {
	        Long idConcorrente = lanceForm.getConcorrente();
	        Long idLeilao = lanceForm.getLeilao();
	        
	        // Verifica se o ID do concorrente existe
	        List<Lance> listaConcorrente = lanceRepository.findByConcorrente(idConcorrente);
	        if (listaConcorrente.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Retorna 403 se o concorrente não existir
	        }
	        
	        // Verifica se o ID do leilão existe
	        List<Lance> listaLeilao = lanceRepository.findByLeilao(idLeilao);
	        if (listaLeilao.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Retorna 400 se o leilão não existir
	        }

	        Lance lance = lanceForm.toLance();
	        lanceRepository.save(lance);
	        LanceDTO lanceDTO = new LanceDTO(lance);
	        
	        uriBuilder.path("/lance/{id}");
	        URI uri = uriBuilder.buildAndExpand(lance.getId()).toUri();
	        
	        return ResponseEntity.created(uri).body(lanceDTO); // Retorna 201 se tudo estiver OK
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().build(); // Retorna 400 se ocorrer algum erro
	    }
	}
	
	

	@PutMapping("/{id}")
	public ResponseEntity<?> alterar(@PathVariable Long id, @RequestBody LanceForm lanceForm) {
	    try {
	    	Long idConcorrente = lanceForm.getConcorrente();
	        Long idLeilao = lanceForm.getLeilao();
	        // Verifica se o ID é válido
	        if (id == null) {
	            return ResponseEntity.notFound().build(); // Retorna 404 se o ID for inválido ou não for passado
	        }
	        
	        // Verifica se o ID do concorrente existe
	        List<Lance> listaConcorrente = lanceRepository.findByConcorrente(idConcorrente);
	        if (listaConcorrente.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Retorna 403 se o concorrente não existir
	        }
	         
	        // Verifica se o ID do leilão existe
	        List<Lance> listaLeilao = lanceRepository.findByLeilao(idLeilao);
	        if (listaLeilao.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Retorna 400 se o leilão não existir
	        }
	        
//	        // Verifica se o leilão está fechado
//	        Leilao leilao = leilaoOptional.get();
//	        if (leilao.getStatus().equals(StatusLeilao.FECHADO)) {
//	            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Retorna 403 se o leilão estiver fechado
//	        }

	        Lance lance = lanceRepository.getReferenceById(id);
	        lance.setLeilao(lanceForm.getLeilao());
	        lance.setConcorrente(lanceForm.getConcorrente());
	        lanceRepository.save(lance);
	        LanceDTO lanceDTO = new LanceDTO(lance);
	        
	        return ResponseEntity.ok(lanceDTO); // Retorna 200 com o DTO atualizado
	    } catch (Exception e) {
	        return ResponseEntity.notFound().build(); // Retorna 404 se ocorrer algum erro
	    }
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		try {
			Lance lance = lanceRepository.getReferenceById(id);
			lanceRepository.delete(lance);
			LanceDTO lanceDTO = new LanceDTO(lance);
			return ResponseEntity.ok(lanceDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

}

