package com.example.usercrud.controller;

import java.net.URI;
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

import com.example.usercrud.controller.DTO.UsuarioDTO;
import com.example.usercrud.model.Post;
import com.example.usercrud.model.Usuario;
import com.example.usercrud.repository.PostRepository;
import com.example.usercrud.repository.UsuarioRepository;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    
    //GET METHODS ------------------------------------------------>
    
    @GetMapping
    public List<Post> listaPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> buscarPostPorId(@PathVariable Long id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post != null) {
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    //POST METHODS --------------------------------------------->

    @PostMapping("/criaPost")
    public ResponseEntity<Post> criarPost(@RequestBody Post post, UriComponentsBuilder uriBuilder) {
        Usuario usuario = usuarioRepository.findById(post.getUsuario().getId()).orElse(null);
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
        if (usuario == null) {
            return ResponseEntity.badRequest().build();
        }
        post.setUsuario(usuario);
        postRepository.save(post);
        URI uri = uriBuilder.path("/post/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(uri).body(post);
    }
    
    //PUT METHODS ------------------------------------------------->

    @PutMapping("/{id}")
    public ResponseEntity<Post> atualizarPost(@PathVariable Long id, @RequestBody Post postAtualizado) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }
        post.setTitulo(postAtualizado.getTitulo());
        post.setConteudo(postAtualizado.getConteudo());
        postRepository.save(post);
        return ResponseEntity.ok(post);
    }

    //DELETE METHODS --------------------------------------------->
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPost(@PathVariable Long id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }
        postRepository.delete(post);
        return ResponseEntity.ok().build();
    }
}
