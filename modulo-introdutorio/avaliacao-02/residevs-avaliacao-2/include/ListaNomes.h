#ifndef LISTANOMES_H
#define LISTANOMES_H

#include <iostream>
#include <string>
#include <vector>
#include <climits>
#include <cctype>
#include <limits>

#include "Lista.h"
#include "funcoes_uteis.h"

using namespace std;

class ListaNomes : Lista
{
    vector<string> lista;

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