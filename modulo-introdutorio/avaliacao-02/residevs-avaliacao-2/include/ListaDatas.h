#ifndef LISTADATAS_H
#define LISTADATAS_H

#include <iostream>
#include <string>
#include <vector>
#include "Lista.h"
#include "Data.h"// Inclua o cabeçalho Data.h em vez de usar "using namespace std;"

using namespace std;
class ListaDatas : public Lista
{
    vector<Data> lista;

public:

    void entradaDeDados() override;
    void mostraMenor() override;
    void mostraMediana() override;
    void mostraMaior() override;
    virtual void listarNPrimeiros() override;

    void listarEmOrdem() override;
    void imprimir();
};

#endif
