#pragma once

#include <string>
#include <vector>
#include "emprestimo.h" // Incluímos o cabeçalho da classe Emprestimo

using namespace std; // Adicionado o using namespace std;

class Usuario
{
private:
    string nome;
    vector<Emprestimo> emprestimos;

public:
  
    Usuario();
    Usuario(string nome);
    Usuario(string nome, Emprestimo novoEmprestimo);
    Usuario(string nome, vector<Emprestimo> registro);
    string getNome();
    void setNome(string nome);
    vector<Emprestimo> getEmprestimos();
    void setEmprestimos(Emprestimo novoEmprestimo);
    ~Usuario();
};
