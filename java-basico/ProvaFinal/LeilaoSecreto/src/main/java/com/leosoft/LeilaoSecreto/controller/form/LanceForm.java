package com.leosoft.LeilaoSecreto.controller.form;

import java.math.BigDecimal;

import com.leosoft.LeilaoSecreto.model.Lance;

public class LanceForm {
	
	private Long leilao;
	private Long concorrente;
	private BigDecimal valor;
	
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

	public LanceForm() {
		super();
	}

	public LanceForm(Long leilao, Long concorrente, BigDecimal valor) {
		super();
		this.leilao = leilao;
		this.concorrente = concorrente;
		this.valor = valor;
	}
	
	//Métodos
	public Lance toLance() {
		Lance lance = new Lance();
		
		lance.setLeilao(leilao);
		lance.setConcorrente(concorrente);
		lance.setValor(valor);
		
		return lance;
	}
	
	

}
