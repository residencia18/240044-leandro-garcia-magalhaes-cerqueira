package soma_inteiros;

public class SomaInteiros {
	
	public static int soma(int a, int b) {
		int sum = a + b;
		
		System.out.println("A soma de " + a + " + " + b + " = " + sum);
		
		return sum;
	}
	
	public static void main(String[] args) {
		int a = 5;
		int b = 4;
		
		soma(a,b);
	}


}

