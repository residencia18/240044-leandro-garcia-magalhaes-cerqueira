package com.airlion.airlion.controller.form;

import com.airlion.airlion.modelo.Aeroporto;

public class AeroportoForm {
	
	private String icao;
	private String nome;
	
	public String getIcao() {
		return icao;
	}
	public void setIcao(String icao) {
		this.icao = icao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public AeroportoForm() {
		super();
	}
	
	public AeroportoForm(String icao, String nome) {
		super();
		this.icao = icao;
		this.nome = nome;
	}
	
	//Métodos
		public Aeroporto toAeroporto(){
			
			Aeroporto aeroporto = new Aeroporto();
			
			aeroporto.setIcao(icao);
			aeroporto.setNome(nome);
			
			return aeroporto;
		}
	
	
	
	
	
	

}
