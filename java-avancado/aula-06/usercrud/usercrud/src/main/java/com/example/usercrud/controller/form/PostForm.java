package com.example.usercrud.controller.form;
import com.example.usercrud.controller.DTO.UsuarioDTO;
import com.example.usercrud.model.Post;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private UsuarioDTO usuario;
	
	//Métodos
		public Post toPost() {
			Post post = new Post();
			
			post.setTitulo(titulo);
			post.setConteudo(conteudo);
			
			return post; 
		}

	
}