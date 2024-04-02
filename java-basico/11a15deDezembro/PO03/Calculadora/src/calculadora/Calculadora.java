package calculadora;

import exceptions.DivisionByZeroException;

public class Calculadora {
	
	//SOMA
	public void soma(int a, int b){
		
		System.out.println(a + b);
		
	}

	public void soma(double a, double b) {
		
		System.out.println(a + b);
	}
	
	//SUBTRAÇÃO
	public void subtrai(int a, int b){
		
		System.out.println(a - b);
		
	}
	
	public void subtrai(double a, double b) {
		
		System.out.println(a - b);
	}
	
	//PRODUTO
	public void produto(int a, int b){
		
		System.out.println(a * b);
		
	}

	public void produto(double a, double b) {
		
		System.out.println(a * b);
	}
	
	//DIVISÃO
	public void divisao(int a, int b)
				throws DivisionByZeroException {
		if (b == 0) {
				throw new DivisionByZeroException(a);
		} else {
		
		System.out.println(a / b);	
	}
	}

	public void divisao(double a, double b)
				throws DivisionByZeroException {
		
		if (b == 0 || b == 0.0) {
			throw new DivisionByZeroException(a);
		} else {
		
		System.out.println(a / b);	
	}
	}
	
	
	

}
