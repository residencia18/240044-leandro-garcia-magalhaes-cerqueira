package coelho;

import java.sql.Timestamp;

public class Reembolso {
	
	private Timestamp data;
	private double valor;
	
	//Construtores
	public Reembolso() {
		super();
	}

	public Reembolso(Timestamp data, double valor) {
		super();
		this.data = data;
		this.valor = valor;
	}
	
	
	public Reembolso(double valor) {
		super();
		this.valor = valor;
	}

	//Getters e Setters

	public double getValor() {
		return valor;
	}

	public Timestamp getData() {
		
		long horarioAtual = System.currentTimeMillis();
        data = new Timestamp(horarioAtual);
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public void setValor(double valor) {
	

}
	
}
