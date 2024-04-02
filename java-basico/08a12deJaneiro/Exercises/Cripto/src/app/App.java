package app;

import java.io.IOException;

import cripto.Cripto;

public class App {

	public static void main(String[] args) {
		
		String nome1 = args[0];
		String nome2 = args[1];
		String nome3 = "coringaDescriptogrado.png";
		String senha = args[2];
		
		//Apenas para teste!
		System.out.println("Parametros lidos:");
		System.out.println("Entrada: " + nome1);
		System.out.println("Saída: " + nome2);
		System.out.println("Senha: " + senha);
		System.out.println("Iniciando o processamento...");
		
		//A parte que importa:
		try {
			Cripto.proccess(nome2, nome3, "12345");
		} catch (IOException e) {
			System.out.println("Problema ao acessar o arquivo.");
		}
	}

}
