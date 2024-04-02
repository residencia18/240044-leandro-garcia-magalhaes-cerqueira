#ifndef LISTAIDADES_H
#define LISTAIDADES_H

#include <iostream>
#include <string>
#include <vector>
#include <climits>
#include <cctype>

#include "Lista.h"
#include "funcoes_uteis.h"
using namespace std;

class ListaIdades : Lista
{
    vector<int> lista;

public:

    void entradaDeDados() override;
    void mostraMenor() override;
    void mostraMediana() override;
    void mostraMaior() override;
    void ordenarLista();
    void listarEmOrdem() override;
    virtual void listarNPrimeiros() override;
};



#endif 