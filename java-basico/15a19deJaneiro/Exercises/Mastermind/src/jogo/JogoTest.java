package jogo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import configuracao.Configuracao;

class JogoTest {

	@Test
	void testSetSenha () {
		
		Configuracao configuracao = new Configuracao();
		Jogo jogo = new Jogo(configuracao);
		
		String senha = "BDCAE";
		
		//Caso geral: inserir uma senha válida
		
		try {		
		configuracao.setAlfabeto("ABCDE");
		configuracao.setTamanhoSenha(3);
		} catch (Exception e) {
			fail("Gerou excecao indevida");
		}
		assertEquals(senha,jogo.getSenha());
		
		
		
		
		
		
		jogo = new Jogo(configuracao);
		
		try {
			
		}
		
		
		
		
		//Caso 1: uma senha é inválida por não respeitar
		//o tamanho (definido na Configuraçao)
		//Gera uma exception ("Senha deve ter N caracteres") N o tamanho da senha
		configuracao = new Configuracao();
		configuracao.setAlfabeto("ABCDE");
		configuracao.setTamanhoSenha(3);
		
		//Cenário 1. Tentar inserir uma senha menor que 3 caracteres
		
		senha = "BD";
		
		jogo = new Jogo(configuracao);
		
		
		
		
		
		
	}

}
