package com.example.usercrud.model;
import java.util.List;
import java.util.Set;

import com.example.usercrud.exceptions.ValorSomaExcedidoException;

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



@Entity
public class Usuario {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Post> posts;
 
    
    //Construtores
    public Usuario(Long id, String nome, String email, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
    
    public Usuario(Usuario usuario) {
		super();
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
	}
    
    public Usuario() {
		super();
	}

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "usuario_comunidade_mapping", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "comunidade_id"))
    private Set<Comunidade> comunidades;
    
    
	// Getters e Setters
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
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

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", posts=" + posts
				+ ", comunidades=" + comunidades + "]";
	}
	
	
	
    
}
