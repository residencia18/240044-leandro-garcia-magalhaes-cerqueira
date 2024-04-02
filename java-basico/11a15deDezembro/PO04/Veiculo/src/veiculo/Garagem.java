package veiculo;

import java.util.ArrayList;

public class Garagem {
	
	ArrayList<Veiculo> veiculos;
	private boolean tomadaEletrica;
	
	public Garagem() {	
		this.veiculos = new ArrayList<>();
	}
	
	public Garagem(boolean tomadaEletrica) {
        this.tomadaEletrica = tomadaEletrica;
        this.veiculos = new ArrayList<>();
    }

	
	public boolean isTomadaEletrica() {
		return tomadaEletrica;
	}

	
	
	
	public void estacionar(Veiculo veiculo) {
		veiculos.add(veiculo);
	}
	
	public void retirar(Veiculo veiculo) {
		veiculos.remove(veiculo);
	}

}
