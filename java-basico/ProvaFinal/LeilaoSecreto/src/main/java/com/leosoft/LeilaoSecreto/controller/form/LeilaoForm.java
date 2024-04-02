package com.leosoft.LeilaoSecreto.controller.form;

import java.math.BigDecimal;

import com.leosoft.LeilaoSecreto.model.Leilao;

public class LeilaoForm {
	
	private String descricao;
	private BigDecimal valorMinimo;
	private boolean statusAberto;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getValorMinimo() {
		return valorMinimo;
	}
	public void setValorMinimo(BigDecimal valorMinimo) {
		this.valorMinimo = valorMinimo;
	}
	public boolean isStatusAberto() {
		return statusAberto;
	}
	public void setStatusAberto(boolean statusAberto) {
		this.statusAberto = statusAberto;
	}
	
	public LeilaoForm() {
		super();
	}
	public LeilaoForm(String descricao, BigDecimal valorMinimo, boolean statusAberto) {
		super();
		this.descricao = descricao;
		this.valorMinimo = valorMinimo;
		this.statusAberto = statusAberto;
	}
	
	//Métodos
	
	public Leilao toLeilao() {
		Leilao leilao = new Leilao();
		
		leilao.setDescricao(descricao);
		leilao.setValorMinimo(valorMinimo);
		leilao.setStatusAberto(statusAberto);
		
		return leilao;
	}
	
	
	
	

}
