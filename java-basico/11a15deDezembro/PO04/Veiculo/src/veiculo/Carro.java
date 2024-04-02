package veiculo;

public class Carro extends Veiculo {
	
	public Carro(String modelo, String cor, int ano) {
		super(modelo, cor, ano);
		// TODO Auto-generated constructor stub
	}
	
	public Carro(String modelo, String cor, int ano, boolean isEletric) {
		super(modelo, cor, ano, isEletric);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ligar() {
		System.out.println("Carro ligado!");
	}
	
	@Override
	public void acelerar() {
		System.out.println("Carro acelerando!");
	}
	
	@Override
	public void parar() {
		System.out.println("Carro parado!");
	}
	
}
