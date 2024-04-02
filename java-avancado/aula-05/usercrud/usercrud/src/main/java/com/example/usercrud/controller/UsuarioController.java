package com.example.usercrud.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.example.usercrud.model.Comunidade;
import com.example.usercrud.model.Usuario;
import com.example.usercrud.repository.ComunidadeRepository;
import com.example.usercrud.repository.UsuarioRepository;
import com.example.usercrud.UsercrudApplication;
import com.example.usercrud.controller.DTO.UsuarioDTO;
import com.example.usercrud.controller.form.UsuarioForm;

@RestController
@RequestMapping("/usuario/")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ComunidadeRepository comunidadeRepository;

    public static final Logger log = LoggerFactory.getLogger(UsercrudApplication.class);

    // GET METHODS ----------------------->

    @GetMapping
    public List<UsuarioDTO> listaUsuarios(String nome) {

        //Formatação do parametro nome para remover os espacamentos indevidos
        String nomeFormatado = nome.trim();

        log.info(nomeFormatado);
        List<Usuario> listaUsuarios = new ArrayList<>();
        // Verifica se o parametro é nulo
        if (nomeFormatado == null) {
            listaUsuarios = (ArrayList<Usuario>) usuarioRepository.findAll();
        } else {
            listaUsuarios = (ArrayList<Usuario>) usuarioRepository.findByNome(nomeFormatado);
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
        log.info("Buscando detalhes de Usuario e Comunidade");
        try {
            Usuario usuario = usuarioRepository.getReferenceById(id);
            UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);

            //get detalhes do Usuario

            log.info("Detalhes do usuario: " + usuarioDTO.toString() + "\n");
            log.info("Detalhes da comunidade: " + usuarioDTO.getComunidades() + "\n");

            log.info("Resolvido!");

            return ResponseEntity.ok(usuarioDTO);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // POST METHODS ----------------------->

    @PostMapping(value = "/criaUsuario")
    public ResponseEntity<UsuarioDTO> inserir(@RequestBody UsuarioForm usuarioForm, UriComponentsBuilder uriBuilder) {
        Usuario usuario = usuarioForm.toUsuario();
        usuarioRepository.save(usuario);
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
        uriBuilder.path("/usuario/{id}");
        URI uri = uriBuilder.buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(usuarioDTO);
    }

    @PostMapping(value = "/atribuiUsuarioNaComunidade/{comunidadeId}")
    public String atribuiUsuarioNaComunidade(@PathVariable(name = "comunidadeId") Long comunidadeId) {
        log.info("\nBuscar detalhes de usuários existentes e atribuí-los a uma comunidade existente." + "\n");

        // get first Usuario
        int userId = 1;
        Usuario usuario1 = this.usuarioRepository.getReferenceById((long) userId);
        log.info("\nUsuario details :: " + usuario1.toString() + "\n");

        // get second Usuario
        userId = 2;
        Usuario usuario2 = this.usuarioRepository.getReferenceById((long) userId);
        log.info("\nUsuario details :: " + usuario2.toString() + "\n");

        // get a Comunidade
        Comunidade comunidade = this.comunidadeRepository.getReferenceById((long) userId);
        log.info("\nComunidade details :: " + comunidade.toString() + "\n");

        // create Usuario set
        Set<Usuario> membros = new HashSet<>();
        membros.add(usuario1);
        membros.add(usuario2);

        // atribuir Usuario Set to Comunidade
        comunidade.setMembros(membros);

        // save Comunidade
        comunidade = comunidadeRepository.save(comunidade);

        log.info("Membro associado na Comunidade." + "\n");

        return "Usuario salvo!!!";
    }

    // PUT METHODS ------------------------->

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable Long id, @RequestBody UsuarioForm usuarioForm) {
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

    // DELETE METHODS ----------------------->

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
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
