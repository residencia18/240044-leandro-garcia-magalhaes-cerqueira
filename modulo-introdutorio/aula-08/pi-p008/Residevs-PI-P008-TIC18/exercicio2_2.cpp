#include <iostream>
#include <cstdlib> // para a função rand()
#include <iomanip> // para std::setprecision
#include <ctime>   // para inicializar a semente rand()
using namespace std;

int main() {
    // Inicializa a semente para geração de números aleatórios
    srand(time(0));

    // Declaração do array para armazenar as temperaturas
    double temperaturas[250];

    // Preenche o array com temperaturas aleatórias entre 10 e 40 graus
    for (int i = 0; i < 250; i++) {
        double temperaturaAleatoria = 10.0 + (rand() / (RAND_MAX + 1.0)) * (40.0 - 10.0);
        temperaturas[i] = temperaturaAleatoria;
    }

   // Inicializa as variáveis para as temperaturas máxima e mínima
    double temperaturaMaxima = temperaturas[0];
    double temperaturaMinima = temperaturas[0];
    double somaTemperaturas = 0.0;

    // Calcula a temperatura máxima, mínima e a soma das temperaturas
    for (int i = 0; i < 250; i++) {
        if (temperaturas[i] > temperaturaMaxima) {
            temperaturaMaxima = temperaturas[i];
        }
        if (temperaturas[i] < temperaturaMinima) {
            temperaturaMinima = temperaturas[i];
        }
        somaTemperaturas += temperaturas[i];
    }

    // Calcula a temperatura média
    double temperaturaMedia = somaTemperaturas / 250;

    // Atualiza as temperaturas de acordo com o modelo de predição
    for (int i = 0; i < 250; i++) {
        if (temperaturas[i] > temperaturaMedia) {
            temperaturas[i] += 1.0;
        } else {
            temperaturas[i] -= 2.0;
        }
    }

    // Exibe os resultados
    cout << fixed << setprecision(1);
    cout << "Temperatura máxima: " << temperaturaMaxima << " graus" << endl;
    cout << "Temperatura mínima: " << temperaturaMinima << " graus" << endl;
    cout << "Temperatura média: " << temperaturaMedia << " graus" << endl;

    // Exibe as temperaturas atualizadas
    cout << "Temperaturas atualizadas:" << endl;
    for (int i = 0; i < 250; i++) {
        cout << "Estação " << i + 1 << ": " << temperaturas[i] << " graus" << endl;
    }

    return 0;
}
