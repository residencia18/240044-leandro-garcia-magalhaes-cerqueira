package veiculo;

public class Moto extends Veiculo {
	
	public Moto(String modelo, String cor, int ano) {
		super(modelo, cor, ano);
		// TODO Auto-generated constructor stub
	}
	
	public Moto(String modelo, String cor, int ano, boolean isEletric) {
		super(modelo, cor, ano, isEletric);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ligar() {
		System.out.println("Moto ligada!");
	}
	
	@Override
	public void acelerar() {
		System.out.println("Moto acelerando!");
	}
	
	@Override
	public void parar() {
		System.out.println("Moto parada!");
	}
	
}
