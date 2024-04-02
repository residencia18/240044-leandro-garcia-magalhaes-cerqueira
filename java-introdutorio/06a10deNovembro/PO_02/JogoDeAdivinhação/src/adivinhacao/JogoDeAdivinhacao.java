package adivinhacao;

import java.util.Random;
import java.util.Scanner;

public class JogoDeAdivinhacao {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		int numeroAleatorio;
		int palpite;
		
		Random random = new Random();	
		numeroAleatorio = random.nextInt(100);
		
		do {
			
			System.out.println("Tente adivinhar qual é o número inteiro: ");
			palpite = scanner.nextInt();
				
				if (palpite < numeroAleatorio)
				{
				 System.out.println("Muito baixo! Tente um número maior...");
				}
				else if (palpite > numeroAleatorio)
				{
					System.out.println("Muito Alto! Tente um número menor...");
				}
				else
				{
					System.out.println("Parabéns! Você acertou o número.");
				}
		
		} while (palpite != numeroAleatorio);
		
		

        scanner.close(); 
		
	}

	
	

}
