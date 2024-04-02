package conversor;

import java.util.Scanner;

public class ConversorDeMoedas {
	
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("--------- | Conversor de moedas | --------- ");
		System.out.println("");

		System.out.println("Informe quantos dólares (U$) você deseja converter: ");
		double dolar = scanner.nextDouble();
		scanner.nextLine();
		
		System.out.println("Insira qual moeda deseja que o dólar seja convertido (ex: Euro,Real...): ");
		String moeda = scanner.nextLine();

		System.out.println("Informe a taxa de câmbio da moeda desejada: ");
		double taxa = scanner.nextDouble();
		scanner.nextLine();
		

		double conversao = dolar * taxa;
		
		System.out.printf(" %.2f Dólares (US) convertido em %s: %.2f. ", dolar, moeda, conversao);

		scanner.close();
		
}
	
}



