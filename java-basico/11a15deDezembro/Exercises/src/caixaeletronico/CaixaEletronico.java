package caixaeletronico;
import exceptions.valueexceptions.*;

public class CaixaEletronico {
	
	public static void sacar (int valor)
			throws ValorInvalidoException {
		if (valor % 10 == 0){
			return;
		} else {
			throw new ValorInvalidoException();
		}
	}
	
	

}
