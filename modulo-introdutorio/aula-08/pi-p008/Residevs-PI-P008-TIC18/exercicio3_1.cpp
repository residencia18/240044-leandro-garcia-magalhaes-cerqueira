#include <iostream>
#include <cstdlib>
#include <ctime>
#include <string>
#include <algorithm>

using namespace std;

int main() {
    // Inicializa a semente do gerador de números aleatórios
    srand(static_cast<unsigned>(time(nullptr)));

    // Gera duas strings aleatórias de 10 caracteres em letras minúsculas
    string string1, string2;
    for (int i = 0; i < 10; i++) {
        char letra1 = 'a' + rand() % ('z' - 'a' + 1);
        char letra2 = 'a' + rand() % ('z' - 'a' + 1);
        string1 += letra1;
        string2 += letra2;
    }


    // Ordena os caracteres dentro de cada string em ordem alfabética
    sort(string1.begin(), string1.end());
    sort(string2.begin(), string2.end());

    // Transforma o primeiro caractere de cada string em maiúscula
    string1[0] = toupper(string1[0]);
    string2[0] = toupper(string2[0]);

    // Imprime as strings com os caracteres em ordem alfabética
    cout << "Caracteres das strings em ordem alfabética:" << endl;
    cout << string1 << endl;
    cout << string2 << endl;

    return 0;
}
