package com.example.usercrud.controller;

import java.util.List;

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
import com.example.usercrud.UsercrudApplication;
import com.example.usercrud.controller.form.PostForm;
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
    
    public static final Logger log = LoggerFactory.getLogger(UsercrudApplication.class);

    
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

    @PostMapping("/{id}/posts")
    public ResponseEntity<Post> criarPost(@RequestBody PostForm postForm, @PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        Post post = postForm.toPost();
        post.setUsuario(usuario);
        postRepository.save(post);

        return ResponseEntity.ok(post);
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
        log.info("Post atualizado!");
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
        log.info("Post apagado com sucesso!");
        return ResponseEntity.ok().build();
    }
}
