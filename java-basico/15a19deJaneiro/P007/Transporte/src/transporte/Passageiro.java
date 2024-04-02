package transporte;

public class Passageiro extends Pessoa {

	private String tipoCartao;
	
	public String getTipoCartao() {
		return tipoCartao;
	}
	
	public Passageiro() {
		super();
	}
	
	public Passageiro(String nome, String cpf, String tipoCartao) {
		super(nome, cpf);
		this.tipoCartao = tipoCartao;
	}
	
}
