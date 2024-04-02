package com.example.usercrud.model;
import java.util.List;

import com.example.usercrud.exceptions.ValorSomaExcedidoException;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;



@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String senha;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Post> posts;
 
    
    //Construtores
    public Usuario(Long id, String name, String email, String senha) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.senha = senha;
	}
    
    public Usuario(Usuario usuario) {
		super();
		this.id = usuario.getId();
		this.name = usuario.getName();
		this.email = usuario.getEmail();
	}
    
    

    public Usuario() {
		super();
	}

	// Getters e Setters
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	//Métodos
	
	public int calculaSoma(int a, int b) throws ValorSomaExcedidoException  {
		int soma = a + b;
		
		if(soma > 500) {
			throw new ValorSomaExcedidoException("A soma não pode ser superior a 500");
		}
		return soma;
	}
	
    
}
