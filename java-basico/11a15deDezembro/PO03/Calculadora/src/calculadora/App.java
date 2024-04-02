package calculadora;

import java.util.Scanner;

import exceptions.DivisionByZeroException;

public class App {
	
	public static void main(String[] args) {
		
		Calculadora calculadora = new Calculadora();
		
			Scanner scanner = new Scanner(System.in);

			System.out.println("--------- | Calculadora | --------- ");
			System.out.println("");
			
			System.out.println("Informe o primeiro valor: ");
			double A = scanner.nextInt();

			System.out.println("Informe o segundo valor: ");
			double B = scanner.nextInt();


			System.out.println("[1] - Adição \n[2] - Subtração \n[3] - Multiplicação \n[4] - Divisão \n[0] - Sair.");
			System.out.println("Digite qual operação deseja realizar: ");
			int operacao = scanner.nextInt();

			switch (operacao) {

			case 1:
				
				calculadora.soma(A, B);		
				break;
				
			case 2:
				
				calculadora.subtrai(A, B);
				break;

			case 3:
				
				calculadora.produto(A, B);
				break;
				
			case 4:
				
				try {
				calculadora.divisao(A, B);
				break;
				} catch (DivisionByZeroException e) {
				System.out.println(e.getMessage());	
				break;
				}
				
			case 0:
				
				System.out.println("Calculadora finalizada.");
				System.exit(0);
				
			default:
				
				System.out.println("Operação inválida ou inexistente.");
				System.exit(0);
			}

		scanner.close(); // Fecha o scanner.
		
		}
				
}
	

