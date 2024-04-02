package paridade;
import java.util.Scanner;

public class VerificacaoDeParidade {
	
	public static void isPar(int numero){
		if (numero%2 == 0){
			System.out.println("É par.");
		} else {
			System.out.println("É impar.");
		}
	}
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		
		System.out.println("Insira um número inteiro: ");
		int num = scanner.nextInt();
		
		isPar(num);
		
		scanner.close();
	}
}
