package concatena_strings;
import java.util.Scanner;

public class ConcatenaStrings {
	
	public static void concatenaString(String a, String b){
	
		String stringConcatenada = a + b;
		
		System.out.println("A string contatenada é " + stringConcatenada);
	}
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		String a = "Olá,";
		String b = "mundo!";
		String c = a + b;
		System.out.println(c);
		
		System.out.println("Insira a primeira palavra: ");
		String A = scanner.nextLine();
		
		System.out.println("Insira a segunda palavra: ");
		String B = scanner.nextLine();
		
		concatenaString(A, B);
		
		
	}

}
