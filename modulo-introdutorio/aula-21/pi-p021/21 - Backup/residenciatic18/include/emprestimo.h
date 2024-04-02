#pragma once

#include <string>
#include <vector>
#include "livro.h" // Incluímos o cabeçalho da classe Livro

using namespace std; // Adicionado o using namespace std;

class Emprestimo
{
private:
    string nome;
    string data;
    vector<Livro> registro;

public:
    Emprestimo(string nome, string data, vector<Livro> livros);
    Emprestimo(string nome, string data, Livro novolivro);
    string getData();
    vector<Livro> getRegistro();
    void setRegistro(Livro novolivro);
    ~Emprestimo();
};
