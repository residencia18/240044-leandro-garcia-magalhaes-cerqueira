package exceptions;

public class DivisionByZeroException extends Exception {
	
	double dividendo;
	
	public DivisionByZeroException (int dividendo) {
		this.dividendo = dividendo;
	}
	
	public DivisionByZeroException (double dividendo) {
		this.dividendo = dividendo;
	}
	
	public String getMessage(){
		return "Impossível dividir " + dividendo + " por zero!";
	}

}
