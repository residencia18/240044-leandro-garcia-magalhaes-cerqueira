#ifndef CTRL_H
#define CTRL_H

#include "ctrlPacote.h"
#include "pacoteTurismo.h"

using namespace std;

class ctrl_pacote
{
    public:
        static Evento criarEvento();
        static PacoteTurismo criarPacoteTurismo(vector<Evento>& eventos);
        static Cliente criarCliente();
        static void venderPacote(Cliente& cliente, PacoteTurismo pacote);
    
};

#endif