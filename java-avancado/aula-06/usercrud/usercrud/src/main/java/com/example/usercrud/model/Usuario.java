package com.example.usercrud.model;
import java.util.List;
import java.util.Set;

import com.example.usercrud.exceptions.ValorSomaExcedidoException;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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

@Entity
public class Usuario {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    

    private String nome;
    private String email;
    private String senha;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Post> posts;
 
    //Construtores

    public Usuario(Usuario usuario) {
		super();
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
	}

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "usuario_comunidade_mapping", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "comunidade_id"))
    private Set<Comunidade> comunidades;
    
	public Set<Comunidade> getComunidades() {
        return comunidades;
    }

    public void setComunidade(Set<Comunidade> comunidades) {
        this.comunidades = comunidades;
    }
    
	//Métodos
	public int calculaSoma(int a, int b) throws ValorSomaExcedidoException  {
		int soma = a + b;
		
		if(soma > 500) {
			throw new ValorSomaExcedidoException("A soma não pode ser superior a 500");
		}
		return soma;
	}
  
}
