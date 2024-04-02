package exceptions.valueexceptions;

public class ValorInvalidoException extends Exception {

	public ValorInvalidoException (){
	}
	
	public String getMessage(){
		return "O valor inserido é invalido.";
	}
}
