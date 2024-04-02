#include "emprestimo.h"

// Construtor para empréstimo com vários livros
Emprestimo::Emprestimo(std::string nome, std::string data, std::vector<Livro> livros)
{
    this->nome = nome;
    this->data = data;
    this->registro = livros;
}

// Construtor para empréstimo com um único livro
Emprestimo::Emprestimo(std::string nome, std::string data, Livro novolivro)
{
    this->nome = nome;
    this->data = data;
    this->registro.push_back(novolivro);
}

std::string Emprestimo::getData()
{
    return data;
}

std::vector<Livro> Emprestimo::getRegistro()
{
    return registro;
}

void Emprestimo::setRegistro(Livro novolivro)
{
    registro.push_back(novolivro);
}

Emprestimo::~Emprestimo()
{
    // Destrutor
}
