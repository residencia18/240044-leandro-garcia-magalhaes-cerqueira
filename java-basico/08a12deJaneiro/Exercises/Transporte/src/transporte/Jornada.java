package transporte;

import java.util.ArrayList;


public class Jornada {
		
	 private ArrayList<Trajeto> trajetosJornada;
	 private Motorista motorista;
	 private Cobrador cobrador; // Pode ser null se não houver cobrador
	 private Veiculo veiculo;
	 
	 
	public Jornada(ArrayList<Trajeto> trajetos, Motorista motorista, Veiculo veiculo) {
		super();
		this.trajetosJornada = trajetos;
		this.motorista = motorista;
		this.veiculo = veiculo;	
	}
	
	public Jornada(ArrayList<Trajeto> trajetos, Motorista motorista, Cobrador cobrador, Veiculo veiculo) {
		super();
		this.trajetosJornada = trajetos;
		this.motorista = motorista;
		this.cobrador = cobrador;
		this.veiculo = veiculo;	
	}
		
	public ArrayList<Trajeto> getTrajetos() {
		return trajetosJornada;
	}
	public Motorista getMotorista() {
		return motorista;
	}
	public Cobrador getCobrador() {
		return cobrador;
	}
	public Veiculo getVeiculo() {
		return veiculo;
	}
	
	 @Override
	    public String toString() {
	        StringBuilder sb = new StringBuilder();
	        sb.append("Trajetos: ").append(getTrajetos());
	        
	        if (motorista != null) {
	            sb.append(" - Motorista: ").append(motorista.getNome()); // Adicione detalhes do motorista, se disponível
	        } else {
	            sb.append(" - Motorista: null");
	        }
	        
	        if (cobrador != null) {
	            sb.append(" - Cobrador: ").append(cobrador.getNome()); // Adicione detalhes do cobrador, se disponível
	        } else {
	            sb.append(" - Cobrador: null");
	        }
	        
	        if (veiculo != null) {
	            sb.append(" - Veiculo: ").append(veiculo.getModelo()); // Adicione detalhes do veículo, se disponível
	        } else {
	            sb.append(" - Veiculo: null");
	        }
	        
	        return sb.toString();
	    }
	 

}
