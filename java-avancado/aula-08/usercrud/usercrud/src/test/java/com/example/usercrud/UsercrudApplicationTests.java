package com.example.usercrud;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.example.usercrud.controller.form.PostForm;
import com.example.usercrud.controller.form.UsuarioForm;
import com.example.usercrud.exceptions.ValorSomaExcedidoException;
import com.example.usercrud.model.Post;
import com.example.usercrud.model.Usuario;
import com.example.usercrud.repository.PostRepository;
import com.example.usercrud.repository.UsuarioRepository;
import com.github.javafaker.Faker;

@SpringBootTest
class UsercrudApplicationTests {
	
	private UsuarioRepository usuarioRepository;
	private PostRepository postRepository; 
	
	@Test
	void contextLoads() {
	}
	
	 @Test
	    public void testCalculaSoma() {
	    	
	    	System.out.println("Foi chamado.");
	        Usuario usuario = new Usuario();
	        
	        // Teste para soma dentro do limite
	        try {
	            int resultado = usuario.calculaSoma(100, 200);
	            assertEquals(300, resultado);
	        } catch (ValorSomaExcedidoException e) {
	            // Se houver uma exceção, o teste falha
	            e.printStackTrace();
	        }
	        
	        // Teste para soma excedendo o limite
	        try {
	            usuario.calculaSoma(300, 300);
	            // Se não lançar a exceção, o teste falha
	        } catch (ValorSomaExcedidoException e) {
	            // Se lançar a exceção, o teste passa
	            assertEquals("A soma não pode ser superior a 500", e.getMessage());
	        }
	    }
	 
	 	public void populaTabelaUsuario(){
		 Faker brFaker = new Faker(new Locale("pt-BR"));
		 
		 	for(int c = 0; c < 100; c++) {
			 
			 UsuarioForm usuarioForm = new UsuarioForm();
			 usuarioForm.setNome(brFaker.name().fullName());
			 usuarioForm.setEmail(brFaker.internet().emailAddress());
			 usuarioForm.setSenha(brFaker.internet().password(8, 15));
			 Usuario usuario = usuarioForm.toUsuario();
		        usuarioRepository.save(usuario);
		 	}
	 	}
	 	
	 	public void populaTabelaPost(){
	 		
	 		  Usuario usuario1 = usuarioRepository.findById((long) 1).orElse(null);
	 		  Usuario usuario2 = usuarioRepository.findById((long) 2).orElse(null);
	 		  Usuario usuario3 = usuarioRepository.findById((long) 3).orElse(null);
	 	     
	 		  PostForm postForm = new PostForm();

	 	        Post post = postForm.toPost();
	 	        post.setUsuario(usuario);
	 	        postRepository.save(post);

			 //Faker brFaker = new Faker(new Locale("pt-BR"));
			 
			 	for(int c = 0; c < 100; c++) {
				 
				 Post post = new Post();
				 post.setUsuario(usuario1);
				 post.setTitulo("Testing");
				 post.setConteudo("Conteudo do post....");
	
			        postRepository.save(post);
			 	}
		 	}

}
