#include <iostream>
#include <cstdlib>
#include <ctime>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    // Inicializa a semente do gerador de números aleatórios
    srand(static_cast<unsigned>(time(nullptr)));

    // Cria uma lista de 10 strings aleatórias
    vector<string> strings;
    for (int i = 0; i < 10; i++) {
        string str;
        for (int j = 0; j < 10; j++) {
            char letra = 'a' + rand() % ('z' - 'a' + 1);
            str += letra;
        }
        strings.push_back(str);
    }

    // Ordena os caracteres dentro de cada string em ordem alfabética
    for (string& str : strings) {
        sort(str.begin(), str.end());
    }

    // Ordena a lista de strings em ordem alfabética
    sort(strings.begin(), strings.end());

       // Transforma o primeiro caractere de cada string em maiúscula
    for (string& str : strings) {
        str[0] = toupper(str[0]);
    }

    // Imprime as strings com os caracteres em ordem alfabética
    cout << "Strings em ordem alfabética:" << endl;
    for (const string& str : strings) {
        cout << str << endl;
    }

    return 0;
}
