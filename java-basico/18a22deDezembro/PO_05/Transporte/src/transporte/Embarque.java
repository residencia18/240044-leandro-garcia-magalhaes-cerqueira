package transporte;

import java.util.Date;

public class Embarque {
    private Passageiro passageiro;
    private PontoDeParada pontoDeEmbarque;
    private Passageiro tipoCartao;
    private Date dataHoraEmbarque;

    public Embarque(Passageiro passageiro, PontoDeParada pontoDeEmbarque, Passageiro tipoCartao) {
        this.passageiro = passageiro;
        this.pontoDeEmbarque = pontoDeEmbarque;
        this.tipoCartao = tipoCartao;
        this.dataHoraEmbarque = new Date(); // Obtém a data e hora atuais
    }

	public Passageiro getPassageiro() {
		return passageiro;
	}

	public PontoDeParada getPontoDeEmbarque() {
		return pontoDeEmbarque;
	}

	public Passageiro getTipoCartao() {
		return tipoCartao;
	}

	public Date getDataHoraEmbarque() {
		return dataHoraEmbarque;
	}

    
}

