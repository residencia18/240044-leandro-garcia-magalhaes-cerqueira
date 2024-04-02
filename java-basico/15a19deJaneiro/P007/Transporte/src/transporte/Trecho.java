package transporte;

public class Trecho {
	
	private String trecho;
	private int intervalo;
	
	
	public Trecho(String trecho, int intervalo) {
		super();
		this.trecho = trecho;
		this.intervalo = intervalo;
	}
	
	
	public String getTrecho() {
		return trecho;
	}


	public int getIntervalo() {
		return intervalo;
	}

	@Override
    public String toString() {
        return "Trecho: " + getTrecho() +  "| Intervalo estimado: " + getIntervalo() + " minutos";
	}


	

	

}
