#pragma once
#include "usuario.h"
#include <iostream>
#include <vector>
using namespace std;

class Twitter
{
private:
    Usuario autor;
    string conteudo;
    string data_criacao;

    // imprementar os metodos de data corretos

public:
    Twitter(/* args */);
    Twitter(Usuario autor, string conteudo, string data_criacao);
    void setAutor(Usuario autor);

    Usuario getAutor();
    string getConteudo();
    string getData_criacao();
    ~Twitter();
};