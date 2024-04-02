package transporte;

import java.util.Date;

public class Embarque {
    private Passageiro passageiro;
    private PontoDeParada pontoDeEmbarque;
    private Date dataHoraEmbarque;

    public Embarque(Passageiro passageiro, PontoDeParada pontoDeEmbarque) {
        this.passageiro = passageiro;
        this.pontoDeEmbarque = pontoDeEmbarque;
        this.dataHoraEmbarque = new Date(); // Obtém a data e hora atuais
    }

	public Passageiro getPassageiro() {
		return passageiro;
	}

	public PontoDeParada getPontoDeEmbarque() {
		return pontoDeEmbarque;
	}

	public Date getDataHoraEmbarque() {
		return dataHoraEmbarque;
	}

}

