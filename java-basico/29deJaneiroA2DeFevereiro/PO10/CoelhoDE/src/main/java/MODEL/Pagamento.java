package MODEL;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Pagamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	private Timestamp data;
	private double valor;
	
	@ManyToOne
	@JoinColumn(name="IdFatura")
	private Fatura fatura;
	
	//Construtores
	public Pagamento() {
		super();
	}
	
	public Pagamento(Timestamp data, double valor) {
		super();
		this.data = data;
		this.valor = valor;
	}

	public Pagamento(double valor) {
		super();
		this.valor = valor;
	}
	
	public Pagamento(Timestamp data, double valor, Fatura fatura) {
		super();
		this.data = data;
		this.valor = valor;
		this.fatura = fatura;
	}

	//Getters e Setters
	public Timestamp getData() {
		
		long horarioAtual = System.currentTimeMillis();
        data = new Timestamp(horarioAtual);
		return data;
	}
	
	public void setData(Timestamp data) {
		this.data = data;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	
	@Override
	public String toString() {
		return "Pagamento [data=" + data + ", valor=" + valor + "]";
	}
	
	
	
}
