package conversor;
import java.util.Scanner;
import java.text.DecimalFormat;

public class ConversorDeTemperatura {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("--------- | Conversor de Temperatura | --------- ");
		System.out.println("");

		System.out.println("Informe a temperatura: ");
		float temp = scanner.nextInt();

		System.out.println("[1] - De Celcius(Cº) para Fahrenheit(Fº)" + " \n[2] - De Fahrenheit(Fº) para Celcius(Cº)"
				+ "\n[0] - Sair.");

		System.out.println("Digite qual operação deseja realizar: ");
		int operacao = scanner.nextInt();

		if (operacao == 1) {
			double F = 0;
			F = (temp * 9.0 / 5.0) + 32;
			
			DecimalFormat formato = new DecimalFormat("#.#"); //Formatando a saida de dados para 1 casa decimal.
			String fFormatado = formato.format(F);
			
			System.out.println(temp + "Cº convertido em Fahrenheit: " + fFormatado +"ºF");
			System.out.println("Programa finalizado.");
			System.exit(0);
		} else if (operacao == 2) {
				double C = 0;
				C = (5.0 / 9.0) * (temp - 32);
				
				DecimalFormat formato = new DecimalFormat("#.#"); //Formatando a saida de dados para 1 casa decimal.
				String cFormatado = formato.format(C);
				
				
				System.out.println(temp + "Fº convertido em Celcius: " + cFormatado +"ºC");
				System.out.println("Programa finalizado.");
				System.exit(0);
		}

	}

}
