#include "usuario.h"
Usuario::Usuario() {}

Usuario::Usuario(string username, string nome){
    this->username = username;
    this->nome = nome;
}
Usuario::Usuario(string username, string nome, Usuario seguir){
    this->username = username;
    this->nome = nome;
    this->seguindo.push_back(seguir);
}
void Usuario::setUsername(string username){
    this->username = username;
}


void Usuario::setNome(string nome)
{
    this->nome = nome;
}

bool Usuario:: operator==( Usuario &other){
        return this->getUsername() == other.getUsername(); // Assuming getUsername() returns a unique identifier.
}

void Usuario::setSeguidor(Usuario novo_seguidor){
    this->seguidores.push_back(novo_seguidor);
}
void Usuario::setSeguir(Usuario novo_usuario){
    this->seguindo.push_back(novo_usuario);
}

string Usuario:: getNome(){
    return nome;
}
string Usuario:: getUsername(){
    return username;
}
vector<Usuario> Usuario:: getSeguindo(){
    return seguindo;
}

Usuario::~Usuario() {}
