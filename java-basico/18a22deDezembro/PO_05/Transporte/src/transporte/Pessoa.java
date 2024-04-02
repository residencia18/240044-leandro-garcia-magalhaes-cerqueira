package transporte;

public class Pessoa {
	
	private String nome;
	private String cpf;
	
	public Pessoa() {
		super();
	}
	
	
	public Pessoa(String nome) {
		super();
		this.nome = nome;
	}

	public Pessoa(String nome, String cpf) {
		super();
		this.nome = nome;
		this.cpf = cpf;
	}
	
	

	public String getNome() {
		return nome;
	}
	
	
	public String getCpf() {
		return cpf;
	}
			
}


