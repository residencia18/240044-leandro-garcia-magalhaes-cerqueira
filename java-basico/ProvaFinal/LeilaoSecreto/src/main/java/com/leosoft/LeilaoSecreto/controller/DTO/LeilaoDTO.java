package com.leosoft.LeilaoSecreto.controller.DTO;

import java.math.BigDecimal;

import com.leosoft.LeilaoSecreto.model.Leilao;

public class LeilaoDTO {
	
	private Long id;
	private String descricao;
	private BigDecimal valorMinimo;
	private boolean statusAberto;
	
	public Long getId() {
		return id;
	}
	public String getDescricao() {
		return descricao;
	}
	public BigDecimal getValorMinimo() {
		return valorMinimo;
	}
	public boolean isStatusAberto() {
		return statusAberto;
	}
	
	public LeilaoDTO() {
		super();
	}
	public LeilaoDTO(Leilao leilao) {
		super();
		this.id = leilao.getId();
		this.descricao = leilao.getDescricao();
		this.valorMinimo = leilao.getValorMinimo();
		this.statusAberto = leilao.isStatusAberto();
	}
	
	
	
	

}
