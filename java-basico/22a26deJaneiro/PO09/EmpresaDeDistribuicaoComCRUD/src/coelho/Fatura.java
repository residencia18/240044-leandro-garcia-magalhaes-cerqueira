package coelho;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Fatura {
	
	//Teste

	private Timestamp data;
	private double ultimaLeitura;
	private double penultimaLeitura;
	private double valor;
	private boolean quitado;
	
	private ArrayList<Pagamento> pagamentos; 
	
	//Construtores
	public Fatura() {
		super();
	}
	
	public Fatura(Timestamp data, double ultimaLeitura, double penultimaLeitura, double valor, boolean quitado) {
		super();
		this.data = data;
		this.ultimaLeitura = ultimaLeitura;
		this.penultimaLeitura = penultimaLeitura;
		this.valor = valor;
		this.quitado = quitado;
		this.pagamentos = new ArrayList<>();
	}
	
	public ArrayList<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(ArrayList<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
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

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public boolean isQuitado() {
		return quitado;
	}

	public void setQuitado(boolean quitado) {
		this.quitado = quitado;
	}
	
	
	
	
	

}
