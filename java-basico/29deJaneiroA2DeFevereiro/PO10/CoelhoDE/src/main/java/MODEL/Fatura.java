package MODEL;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Fatura {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	private Timestamp data;
	private double ultimaLeitura;
	private double penultimaLeitura;
	private double valor;
	private boolean quitado;
	
	
	@ManyToOne
	@JoinColumn(name="IdImovel")
	private Imovel imovel;
	
	
	@OneToMany (mappedBy="fatura")
	private List<Pagamento> pagamentos; 
	
	//Construtores
	public Fatura() {
		super();
	}
	
	public Fatura(Timestamp data, double ultimaLeitura, double penultimaLeitura, double valor,
			boolean quitado, Imovel imovel) {
		super();
		this.data = data;
		this.ultimaLeitura = ultimaLeitura;
		this.penultimaLeitura = penultimaLeitura;
		this.valor = valor;
		this.quitado = quitado;
		this.imovel = imovel;
		
	}

	public List<Pagamento> getPagamentos() {
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

	
	@Override
	public String toString() {
		return "Fatura [Id=" + Id + ", data=" + data + ", ultimaLeitura=" + ultimaLeitura + ", penultimaLeitura="
				+ penultimaLeitura + ", valor=" + valor + ", quitado=" + quitado;
	}
	
	
	
	
	

}
