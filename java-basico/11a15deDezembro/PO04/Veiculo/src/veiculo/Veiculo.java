package veiculo;

public class Veiculo {

	private String modelo;
	private String cor;
	private int ano;
	private boolean isEletric;
	
	public Veiculo(String modelo, String cor, int ano) {
		this.modelo = modelo;
		this.cor = cor;
		this.ano = ano;
	}
	
	public Veiculo(String modelo, String cor, int ano, boolean isEletric) {
		super();
		this.modelo = modelo;
		this.cor = cor;
		this.ano = ano;
		this.isEletric = isEletric;
	}

	public void ligar() {
		System.out.println("Veículo ligado!");
	}
	
	public void acelerar() {
		System.out.println("Veículo acelerando!");
	}
	
	public void parar() {
		System.out.println("Veículo parado!");
	}
	
	public void estacionar(Garagem garagem) {
		System.out.println("Veículo estacionado!");
		if (garagem.isTomadaEletrica() == true && isEletric == true){
			System.out.println("Veículo carregando...");
		}
	}
	
	
	
	
	
	
}

