#include <iostream>

void maxmin(int vetor[], int n, int &maximo, int &minimo) {
    if (n <= 0) {
        std::cout << "O vetor está vazio." << std::endl;
        return;
    }

    maximo = vetor[0]; // Inicializa maximo com o primeiro elemento do vetor
    minimo = vetor[0]; // Inicializa minimo com o primeiro elemento do vetor

    for (int i = 1; i < n; i++) {
        if (vetor[i] > maximo) {
            maximo = vetor[i]; // Atualiza maximo se encontrar um elemento maior
        }
        if (vetor[i] < minimo) {
            minimo = vetor[i]; // Atualiza minimo se encontrar um elemento menor
        }
    }
}

int main() {
    int vetor[] = {10, 26, 8, 2, 15, 3, 7};
    int tamanho = sizeof(vetor) / sizeof(vetor[0]);
    int maximo, minimo;

    maxmin(vetor, tamanho, maximo, minimo);

    std::cout << "Máximo: " << maximo << std::endl;
    std::cout << "Mínimo: " << minimo << std::endl;

    return 0;
}
