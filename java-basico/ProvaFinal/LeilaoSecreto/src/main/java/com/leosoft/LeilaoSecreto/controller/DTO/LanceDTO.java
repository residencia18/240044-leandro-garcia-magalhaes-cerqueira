package com.leosoft.LeilaoSecreto.controller.DTO;

import java.math.BigDecimal;

import com.leosoft.LeilaoSecreto.model.Lance;

public class LanceDTO {
	
	private Long leilao;
	private Long concorrente;
	private BigDecimal valor;
	
	public Long getLeilao() {
		return leilao;
	}
	public Long getConcorrente() {
		return concorrente;
	}
	public BigDecimal getValor() {
		return valor;
	}
	
	public LanceDTO() {
		super();
	}
	
	public LanceDTO(Lance lance) {
		super();
		this.leilao = lance.getLeilao();
		this.concorrente = lance.getConcorrente();
		this.valor = lance.getValor();
	}
	
	
	
	
	

}
