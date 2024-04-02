package MODEL;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cliente {
	
	@Id
	private String CPF;
	private String Nome;
	
	//Construtores
	
	
	public Cliente(String cpf, String nome) {
		super();
		CPF = cpf;
		this.Nome = nome;
	}
	
	public Cliente() {
		super();
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
		return Nome;
	}

	public void setNome(String nome) {
		this.Nome = nome;
	}

	
	
	@Override
	public String toString() {
		return "Cliente [CPF=" + CPF + ", Nome=" + Nome + "]";
	}
	
	
	
}
