#ifndef FUNCOES_UTEIS_H
#define FUNCOES_UTEIS_H

#include <cctype>
#include <iostream>
#include <string>
#include <vector>
#include <climits>
#include <cctype>


using namespace std;

template <typename T>
void getValorMaximo(vector<T> elementos, T &maximo);

template <typename T>
void getValorMinimo(vector<T> elementos, T &minimo);

bool isTextoValido(std::string _nome);
bool isPar(int _numero);
bool isImpar(int _numero);
bool isNumeroValido(int numero);

void systemClear();
void systemPause();
void systemPauseAndClear();

#endif
