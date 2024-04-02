package com.example.usercrud.controller.form;
import com.example.usercrud.model.Post;
import com.example.usercrud.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PostForm {
	
	//Atributos
	private String titulo;
	private String conteudo;
	private Usuario usuario;
	
	//Métodos
		public Post toPost() {
			Post post = new Post();
			post.setTitulo(titulo);
			post.setConteudo(conteudo);
			post.setUsuario(usuario);
			return post; 
		}

	
}