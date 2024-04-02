package com.example.usercrud.model;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O título não pode ser vazio")
	private String titulo;
	
	@Min(value = 1, message = "A postagem precisa conter no mínimo 1 caractere")
	@Max(value = 200, message = "A postagem precisa conter no máximo 200 caracteres")
	private String conteudo;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	@JsonBackReference
	private Usuario usuario;

	@Override
	public String toString() {
		return "Post [id=" + id + ", titulo=" + titulo + ", conteudo=" + conteudo + ", usuario=" + usuario + "]";
	}
	
	

	
}
