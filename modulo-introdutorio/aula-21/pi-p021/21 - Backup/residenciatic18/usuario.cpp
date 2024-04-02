#include "usuario.h"
using namespace std;
 Usuario::Usuario(void) {
    nome = "";
}


Usuario::Usuario(string nome) {
    this->nome = nome;
}


Usuario::Usuario(string nome, vector<Emprestimo> registro) {
    this->nome = nome;
    this->emprestimos = registro;
}

Usuario::Usuario(string nome, Emprestimo registro) {
    this->nome = nome;
    this->emprestimos.push_back(registro);
}

string Usuario::getNome() {
    return nome;
}

void Usuario::setNome(string nome) {
    this->nome = nome;
}

vector<Emprestimo> Usuario::getEmprestimos() {
    return emprestimos;
}

void Usuario::setEmprestimos(Emprestimo novoEmprestimo) {
    emprestimos.push_back(novoEmprestimo);
}

Usuario::~Usuario() {
    // Destrutor
}
