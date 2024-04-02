package jogo;

import configuracao.Configuracao;

public class Jogo {
	
	private Configuracao configuracao;
	private String senha;
	private int qtTentativas;
	
	public Jogo(Configuracao configuracao) {
		super();
		this.configuracao = configuracao;
	}

	public Configuracao getConfiguracao() {
		return configuracao;
	}

	public void setConfiguracao(Configuracao configuracao) {
		this.configuracao = configuracao;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getQtTentativas() {
		return qtTentativas;
	}

	public void setQtTentativas(int qtTentativas) {
		this.qtTentativas = qtTentativas;
	}
	
	public int fimDeJogo() {
		return 0;
	}
	
	
	

}
