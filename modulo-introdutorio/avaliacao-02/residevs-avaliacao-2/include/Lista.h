#ifndef LISTA_H
#define LISTA_H

class Lista 
{
	public:
	virtual void entradaDeDados() =0;
	virtual void mostraMediana() =0;
	virtual void mostraMenor() =0;
	virtual void mostraMaior() =0;
	virtual void listarEmOrdem() = 0;
	virtual void listarNPrimeiros() =0;
};
#endif