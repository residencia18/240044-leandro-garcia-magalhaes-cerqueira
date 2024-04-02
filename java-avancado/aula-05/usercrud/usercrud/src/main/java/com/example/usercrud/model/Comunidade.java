package com.example.usercrud.model;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;


@Entity
public class Comunidade {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String descricao;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "usuario_comunidade_mapping", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "comunidade_id"))
    private Set<Usuario> membros;
 

    public Comunidade() {
        // Construtor padrão
    }

    public Comunidade(long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
      
    }
    
    

    public Comunidade(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

	// Getters e Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<Usuario> getMembros() {
        return membros;
    }

    public void setMembros(Set<Usuario> membros) {
        this.membros = membros;
    }

	@Override
	public String toString() {
		return "Comunidade [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", membros=" + membros + "]";
	}
    
    
}
