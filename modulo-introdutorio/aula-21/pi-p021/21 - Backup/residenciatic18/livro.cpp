#include "livro.h"
#include <iostream>

Livro::Livro(void) {
    titulo = "";
    autor = "";
    copias = 0;
}

Livro::Livro(std::string titulo, std::string autor, int copias) {
    this->titulo = titulo;
    this->autor = autor;
    this->copias = copias;
}

string Livro::getTitulo() {
    return titulo;
}

void Livro::setTitulo(std::string titulo) {
    this->titulo = titulo;
}

std::string Livro::getAutor() {
    return autor;
}

void Livro::setAutor(std::string autor) {
    this->autor = autor;
}

int Livro::getCopias() {
    return copias;
}

void Livro::setCopias(int copias) {
    this->copias += copias;
}

void Livro::exibirInformacoes() {
    std::cout << "Título: " << titulo << std::endl;
    std::cout << "Autor: " << autor << std::endl;
    std::cout << "Número de cópias: " << copias << std::endl;
}

Livro::~Livro() {
    // Destrutor
}
