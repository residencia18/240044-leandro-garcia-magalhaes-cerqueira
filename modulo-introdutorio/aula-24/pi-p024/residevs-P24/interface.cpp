#include "interface.h"
#include <string>
#include <vector>
#include <iostream>

using namespace std;

void aguarde(void)
{
    cout << "Tecle algo para continuar...";
    cin.get();
    //getch();
    cout << endl;
}

void monta_menu(vector<string> itens, string titulo)
{
    cout << titulo << endl;
    for (auto item : itens)
    {
        cout << item << endl;
    }
};

int obter_opcao(int qtde_opcoes)
{
    int opcao = -1;
    while ((opcao < 1) || (opcao > qtde_opcoes))
    {
        cout << "Opcao: ";
        cin >> opcao;
        if ((opcao<1) || (opcao > qtde_opcoes))
        {
            cout << "Opcao invalida! Voce deve digitar um numero entre 1 e " << qtde_opcoes<< endl;
            aguarde();
        }
    }
    return opcao;
}