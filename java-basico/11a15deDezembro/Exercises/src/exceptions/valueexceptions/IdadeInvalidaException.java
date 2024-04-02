package exceptions.valueexceptions;

public class IdadeInvalidaException extends Exception {
	
	
	public IdadeInvalidaException () {
		
	}
	
	public String getMessage(){
		return "Idade inválida! Por favor, insira uma idade entre 0 e 150.";
	}

}
