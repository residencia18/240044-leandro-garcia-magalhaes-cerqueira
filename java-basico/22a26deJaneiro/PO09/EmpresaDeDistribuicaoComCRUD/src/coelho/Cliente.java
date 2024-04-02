package coelho;

public class Cliente {
	
	private String CPF;
	private String nome;
	
	//Construtor
	public Cliente(String cpf, String nome) {
		super();
		CPF = cpf;
		this.nome = nome;
	}
	
	public Cliente(String cPF) {
		super();
		CPF = cPF;
	}

	//Getters e Setters
	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cpf) {
		CPF = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
