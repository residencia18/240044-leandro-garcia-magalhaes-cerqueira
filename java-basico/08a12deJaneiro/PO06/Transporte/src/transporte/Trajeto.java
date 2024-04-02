package transporte;

import java.util.ArrayList;
import java.util.Date;

public class Trajeto {

	private ArrayList<Trecho> trechos;
	
	
	private Date inicioTrajeto;
	private Date Checkpoint;

	public Trajeto() {
		super();
	}
	
	
	public Trajeto(ArrayList<Trecho> trechos) {
		super();
		this.trechos = new ArrayList<>();
		this.trechos.addAll(trechos);
	}

	public ArrayList<Trecho> getTrechos() {
		return trechos;
	}
	

	public Date getInicioTrajeto() {
		return inicioTrajeto;
	}
	
	public void setInicioTrajeto(Date inicioTrajeto) {
		this.inicioTrajeto = inicioTrajeto;
	}

	public Date getCheckpoint() {
		return Checkpoint;
	}


	public void setCheckpoint(Date checkpoint) {
		Checkpoint = checkpoint;
	}
	
	@Override
    public String toString() {
        // Aqui você pode personalizar a representação do trajeto conforme desejado
        return "Trechos: " + getTrechos();
    }


}
