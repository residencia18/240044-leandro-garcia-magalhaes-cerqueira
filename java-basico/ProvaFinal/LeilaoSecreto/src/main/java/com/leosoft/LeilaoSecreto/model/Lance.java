package com.leosoft.LeilaoSecreto.model;

import java.math.BigDecimal;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Lance {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Long leilao;
	private Long concorrente;
	private BigDecimal valor;
	
	public Lance() {
		super();
	}

	public Lance(Long id, Long leilao, Long concorrente, BigDecimal valor) {
		super();
		this.id = id;
		this.leilao = leilao;
		this.concorrente = concorrente;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLeilao() {
		return leilao;
	}

	public void setLeilao(Long leilao) {
		this.leilao = leilao;
	}

	public Long getConcorrente() {
		return concorrente;
	}

	public void setConcorrente(Long concorrente) {
		this.concorrente = concorrente;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	
	
	
	
	
	
	
	
	

}
