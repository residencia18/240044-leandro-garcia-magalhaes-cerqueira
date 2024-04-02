#ifndef INTERFACE_H
#define INTERFACE_H

#include <string>
#include <vector>
#include <iostream>

using namespace std;

void aguarde(void);
void monta_menu(vector<string> itens, string titulo);
int obter_opcao(int qtde_opcoes);

#endif