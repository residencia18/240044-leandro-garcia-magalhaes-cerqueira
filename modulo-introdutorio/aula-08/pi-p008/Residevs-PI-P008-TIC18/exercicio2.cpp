#include <iostream>
#include <cstdlib>
#include <ctime>

using namespace std;

int main() {

    //a.
    std:: cout << "a):" << std:: endl;
    srand(time(0)); // Inicializa a semente do gerador de números aleatórios com o tempo atual

    const int tamanhoArray = 100;
    int array[tamanhoArray];
    int frequencia[20] = {0}; // Inicializa um array de frequência com 20 posições, todas com valor 0
//b.
    std:: cout << "b):" << std:: endl;
    // Preenche o array com números aleatórios entre 1 e 20
    for (int i = 0; i < tamanhoArray; i++) {
        array[i] = 1 + rand() % 20;
        frequencia[array[i] - 1]++; // Atualiza a frequência do número gerado
    }
//c.
    std:: cout << "c):" << std:: endl;
    // Encontra o(s) número(s) mais frequente(s)
    int maxFrequencia = 0;
    for (int i = 0; i < 20; i++) {
        if (frequencia[i] > maxFrequencia) {
            maxFrequencia = frequencia[i];
        }
    }

    // Imprime os números mais frequentes
    cout << "Número(s) mais frequente(s): ";
    for (int i = 0; i < 20; i++) {
        if (frequencia[i] == maxFrequencia) {
            cout << i + 1 << " ";
        }
    }
    cout << "aparecem " << maxFrequencia << " vezes." << endl;

    return 0;
}
