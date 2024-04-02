package manipulacaodearrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
	
	public static List<String> pessoas = new ArrayList<>();
	
	public static String nome1 = "Maria";

	public static String nome2 = "José";

	public static String nome3 = "Pedro";
	
	public static void preencheArray(String nome1, String nome2, String nome3){
		pessoas.add(nome1);
		pessoas.add(nome2);
		pessoas.add(nome3);			
	}
	
	public static void buscaNome(String nomeProcurado, List pessoas) {
		
		if (pessoas.contains(nomeProcurado)) {
			System.out.println(nomeProcurado + " está contido na lista!");
		} else {
			System.out.println(nomeProcurado + " não está contido na lista.");
		}
	}

	public static void main(String[] args) {
		
		preencheArray(nome1, nome2, nome3);
		buscaNome("Pedro", pessoas);
	}
}

