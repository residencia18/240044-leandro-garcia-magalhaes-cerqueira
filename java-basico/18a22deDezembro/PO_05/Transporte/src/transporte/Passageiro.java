package transporte;

public class Passageiro extends Pessoa {

	private int tipoCartao;
	
	public int getTipoCartao() {
		return tipoCartao;
	}
	
	public Passageiro() {
		super();
	}

	
	public Passageiro(String nome) {
		super(nome);
	}
	
	public Passageiro(int tipoCartao) {
		super();
		this.tipoCartao = tipoCartao;
	}

	public Passageiro(String nome, String cpf, int tipoCartao) {
		super(nome, cpf);
		this.tipoCartao = tipoCartao;
	}
	
}
