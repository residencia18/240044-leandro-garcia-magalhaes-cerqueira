package MODEL;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Imovel {
	
	@Id
	private String matricula;
	private String endereco;
	private double ultimaLeitura;
	private double penultimaLeitura;
	
	@OneToMany (mappedBy="imovel")
	List<Fatura> listaFaturas;

	//Construtores
	public Imovel() {
		super();
	}
	
	public Imovel(String matricula, String endereco) {
		super();
		this.matricula = matricula;
		this.endereco = endereco;
	}
	
	public Imovel(String matricula) {
		super();
		this.matricula = matricula;
	}
	
	public Imovel(String matricula, String endereco, double ultimaLeitura, double penultimaLeitura) {
		super();
		this.matricula = matricula;
		this.endereco = endereco;
		this.ultimaLeitura = ultimaLeitura;
		this.penultimaLeitura = penultimaLeitura;
	}

	
	//Getters e Setters
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public double getUltimaLeitura() {
		return ultimaLeitura;
	}

	public void setUltimaLeitura(double ultimaLeitura) {
		this.ultimaLeitura = ultimaLeitura;
	}

	public double getPenultimaLeitura() {
		return penultimaLeitura;
	}

	public void setPenultimaLeitura(double penultimaLeitura) {
		this.penultimaLeitura = penultimaLeitura;
	}

	@Override
	public String toString() {
		return "Imovel [matricula=" + matricula + ", endereco=" + endereco + ", ultimaLeitura=" + ultimaLeitura
				+ ", penultimaLeitura=" + penultimaLeitura + ", listaFaturas=" + listaFaturas + "]";
	}
	
	
	
	
	

}
