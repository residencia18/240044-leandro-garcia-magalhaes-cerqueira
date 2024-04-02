package coelho;

import java.sql.Timestamp;

public class Pagamento {
	
	private Timestamp data;
	private double valor;
	
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
