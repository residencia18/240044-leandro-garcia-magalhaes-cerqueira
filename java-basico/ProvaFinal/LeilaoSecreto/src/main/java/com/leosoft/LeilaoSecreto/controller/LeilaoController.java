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
import com.leosoft.LeilaoSecreto.controller.DTO.LeilaoDTO;
import com.leosoft.LeilaoSecreto.controller.form.LeilaoForm;
import com.leosoft.LeilaoSecreto.model.Leilao;
import com.leosoft.LeilaoSecreto.repository.LeilaoRepository;
import java.util.Optional;


@RestController
@RequestMapping("/leilao/")
public class LeilaoController {

	@Autowired
	private LeilaoRepository leilaoRepository;


	// Lista todos os leiloes disponiveis
	@GetMapping
	public List<LeilaoDTO> listaLeiloes() {
		List<Leilao> listaLeiloes = new ArrayList<>();

		listaLeiloes = (ArrayList<Leilao>) leilaoRepository.findAll();

		List<LeilaoDTO> lista = new ArrayList<LeilaoDTO>();

		for (Leilao l : listaLeiloes) {
			LeilaoDTO ld = new LeilaoDTO(l);
			lista.add(ld);
		}
		return lista;
	}

	// Busca um leilão pelo id
	@GetMapping("/{id}")
	public ResponseEntity<?> listaLeiloes(@PathVariable Long id) {
		try {
			Leilao leilao = leilaoRepository.getReferenceById(id);
			LeilaoDTO leilaoDTO = new LeilaoDTO(leilao);
			return ResponseEntity.ok(leilaoDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	// Adiciona um leilão ao banco de dados
	@PostMapping
	public ResponseEntity<LeilaoDTO> inserir(@RequestBody LeilaoForm leilaoForm, UriComponentsBuilder uriBuilder) {
		try {
			Leilao leilao = leilaoForm.toLeilao();
			leilaoRepository.save(leilao);
			LeilaoDTO leilaoDTO = new LeilaoDTO(leilao);
			uriBuilder.path("/leilao/{id}");
			URI uri = uriBuilder.buildAndExpand(leilao.getId()).toUri();
			return ResponseEntity.created(uri).body(leilaoDTO);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> alterar(@PathVariable Long id, @RequestBody LeilaoForm leilaoForm) {
		try {
			Leilao leilao = leilaoRepository.getReferenceById(id);
			leilao.setDescricao(leilaoForm.getDescricao());
			leilao.setValorMinimo(leilaoForm.getValorMinimo());
			leilao.setStatusAberto(leilaoForm.isStatusAberto());
			leilaoRepository.save(leilao);
			LeilaoDTO leilaoDTO = new LeilaoDTO(leilao);
			return ResponseEntity.ok(leilaoDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		try {
			Leilao leilao = leilaoRepository.getReferenceById(id);
			leilaoRepository.delete(leilao);
			LeilaoDTO leilaoDTO = new LeilaoDTO(leilao);
			return ResponseEntity.ok(leilaoDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@GetMapping("/vencedor_leilao/{id}")
    public ResponseEntity<?> obterVencedorLeilao(@PathVariable Long id) {
        try {
            // Verifica se o ID é válido
            if (id == null) {
                return ResponseEntity.badRequest().build(); // Retorna 400 se o ID não for passado
            }

            // Busca o leilão pelo ID
            Optional<Leilao> leilaoOptional = leilaoRepository.findById(id);
            if (leilaoOptional.isEmpty()) {
                return ResponseEntity.notFound().build(); // Retorna 404 se o leilão não for encontrado
            }

            Leilao leilao = leilaoOptional.get();

            // Verifica se o leilão está fechado
            if (!leilao.isStatusAberto()) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Retorna 403 se o leilão estiver fechado
            }
            
            LeilaoDTO leilaoDTO = new LeilaoDTO(leilao);
   
            return ResponseEntity.ok(leilaoDTO); // Retorna 200 com o JSON contendo os dados do leilão, maior lance e concorrente
        } catch (Exception e) {
            return ResponseEntity.badRequest().build(); // Retorna 400 se ocorrer algum erro
        }
    }


}
