#ifndef LISTASALARIOS_H
#define LISTASALARIOS_H

#include "Lista.h"

#include <iostream>
#include <string>
#include <vector>

#include "funcoes_uteis.h"

using namespace std;

class ListaSalarios : Lista
{
    vector<float> lista;

public:
    void entradaDeDados() override;
    void mostraMenor() override;
    void mostraMediana() override;
    void mostraMaior() override;
     //Ordenar lista de nome
    void listarEmOrdem() override;
    virtual void listarNPrimeiros()  override;
    void ordenarLista();
   

};



#endif 