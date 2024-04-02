#include "twitter.h"

Twitter::Twitter(/* args */) {}

Twitter::Twitter(Usuario autor, string conteudo, string data_criacao)
{
    this->autor = autor;
    this->conteudo = conteudo;
    this->data_criacao = data_criacao;
}

Usuario Twitter::getAutor()
{
    return autor;
}

string Twitter::getConteudo()
{
    return conteudo;
}

string Twitter:: getData_criacao()
{
    return data_criacao;
}

Twitter::~Twitter() {}