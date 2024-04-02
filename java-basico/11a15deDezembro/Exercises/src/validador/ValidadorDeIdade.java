package validador;
import java.util.Scanner;

import exceptions.valueexceptions.*;

public class ValidadorDeIdade {
	
	public static void validaIdade(int idade)
			throws IdadeInvalidaException {
		
			if (idade < 0 || idade > 150) {
				throw new IdadeInvalidaException();
			} else {
				return;
			}	
	}
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("======= Mini Cadastro =======");
		System.out.println("\n");
		System.out.println("Forneça sua idade: ");
		int idade = scanner.nextInt();
		
		try {
			validaIdade(idade);
		} catch (IdadeInvalidaException e) {
			System.out.println(e.getMessage());
		}
		
		
		scanner.close();
		
	}

}
