#pragma once

#include <string>
using namespace std;

class Livro
{
private:
    string titulo;
    string autor;
    int copias;

public:
    Livro(void);
    Livro(string titulo, string autor, int copias);
    string getTitulo();
    void setTitulo(string titulo);
    string getAutor();
    void setAutor(string autor);
    int getCopias();
    void setCopias(int copias);
    void exibirInformacoes();
    ~Livro();
};
