package com.leosoft.LeilaoSecreto.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Leilao {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	private BigDecimal valor_minimo;
	private boolean status_aberto;
	
	public Leilao() {
		super();
	}

	public Leilao(Long id, String descricao, BigDecimal valorMinimo, boolean statusAberto) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.valor_minimo = valorMinimo;
		this.status_aberto = statusAberto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValorMinimo() {
		return valor_minimo;
	}

	public void setValorMinimo(BigDecimal valorMinimo) {
		this.valor_minimo = valorMinimo;
	}

	public boolean isStatusAberto() {
		return status_aberto;
	}

	public void setStatusAberto(boolean statusAberto) {
		this.status_aberto = statusAberto;
	}
	
	
	
	

}
