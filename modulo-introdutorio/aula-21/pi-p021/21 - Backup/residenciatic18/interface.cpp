#include "interface.h"

#include <iostream>
using namespace std;

void aguarde() {
    cout << "Tecle algo para continuar...";
    cin.ignore(); // Descartar qualquer caractere pendente
    cin.get();     // Esperar pelo Enter
    cout << endl;
}

int monta_menu(vector<string> itens, string titulo) {
    int op;
    cout << titulo << endl;
    for (auto item : itens) {
        cout << item << endl;
    }
    cout << "Opção: ";
    cin >> op;
    return op;
}
