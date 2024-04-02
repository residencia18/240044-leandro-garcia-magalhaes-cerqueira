#include <iostream>
#include <vector>
#include <cstdlib>
#include <ctime>

using namespace std;

int main() {
    // Seed para gerar números aleatórios
    srand(time(NULL));

    // Tamanho da imagem
    const int largura = 640;
    const int altura = 480;

    // Crie uma matriz para representar a imagem em tons de cinza
    vector<vector<int>> imagem(altura, vector<int>(largura));

    // Preencha a matriz com valores de intensidade aleatórios entre 0 e 255
    for (int i = 0; i < altura; ++i) {
        for (int j = 0; j < largura; ++j) {
            imagem[i][j] = rand() % 256; // Correção aqui
        }
    }

    // Crie um vetor para armazenar o histograma das intensidades
    vector<int> histograma(256, 0);

    // Calcule o histograma da imagem
    for (int i = 0; i < altura; ++i) {
        for (int j = 0; j < largura; ++j) {
            histograma[imagem[i][j]]++;
        }
    }

    // Imprima o histograma
    for (int i = 0; i < 256; ++i) {
        cout << "Intensidade " << i << ": " << histograma[i] << " pixels" << endl;
    }

    return 0;
}
