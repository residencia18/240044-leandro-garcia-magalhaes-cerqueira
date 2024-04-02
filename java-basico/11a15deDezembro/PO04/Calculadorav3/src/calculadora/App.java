package calculadora;

import java.util.ArrayList;
import java.util.Scanner;

import exceptions.DivisionByZeroException;

public class App {
	
	public static void main(String[] args) {
		
		Calculadora calculadora = new Calculadora();
		ArrayList <Double> numeros = new ArrayList<>();
		
			Scanner scanner = new Scanner(System.in);

			System.out.println("--------- | Calculadora | --------- ");
			System.out.println("");
						
				
				System.out.println("SOMA DE INTEIROS");
				calculadora.soma(3, 7);	
				System.out.println("SOMA DE DECIMAIS");
				calculadora.soma(5.7, 10.5);
				System.out.println("-------------");
				System.out.println("\n");

				
				System.out.println("SUBTRAÇÃO DE INTEIROS");
				calculadora.subtrai(6, 2);
				System.out.println("SUBTRAÇÃO DE DECIMAIS");
				calculadora.subtrai(12.4, 6.5);
				System.out.println("-------------");
				System.out.println("\n");
		
				
				System.out.println("PRODUTO DE INTEIROS");
				calculadora.produto(3, 4);
				System.out.println("PRODUTO DE DECIMAIS");
				calculadora.produto(7.8,6.6);
				System.out.println("-------------");
				System.out.println("\n");
				
	
				System.out.println("DIVISÃO DE INTEIROS");
				try {
				calculadora.divisao(2, 5);
				} catch (DivisionByZeroException e) {
				System.out.println(e.getMessage());	
				}
				System.out.println("DIVISÃO DE DECIMAIS");
				try {
					calculadora.divisao(2.4, 3.3);
					} catch (DivisionByZeroException e) {
					System.out.println(e.getMessage());	
					}
				System.out.println("DIVISÃO DE DECIMAIS (COM EXCEÇÃO EM FUNCIONAMENTO)");
				try {
					calculadora.divisao(2.4, 0);
					} catch (DivisionByZeroException e) {
					System.out.println(e.getMessage());	
					}
				System.out.println("-------------");
				System.out.println("\n");
				
				
				//Objetos para método que recebe uma lista de números
				numeros.add(6.0);
				numeros.add(2.3);
				numeros.add(7.8);
				numeros.add(12.7);
				
				calculadora.operacoes(numeros);
	
				System.out.println("Calculadora finalizada.");
				System.exit(0);
	
				scanner.close(); // Fecha o scanner.
		
		}
				
}
	

