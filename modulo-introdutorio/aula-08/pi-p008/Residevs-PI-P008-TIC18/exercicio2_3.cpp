#include <iostream>
#include <cstdlib>
#include <iomanip>
#include <ctime>

using namespace std;

int main() {
    // Inicializa o gerador de números aleatórios com o tempo atual
    srand(time(0));

    const int numAlunos = 15;
    float notasAvaliacao1[numAlunos];
    float notasAvaliacao2[numAlunos];
    string desempenho[numAlunos];
    float mediaNotas[numAlunos];

    // Preencha as notas das duas avaliações e calcule o desempenho e a média
    for (int i = 0; i < numAlunos; i++) {
        notasAvaliacao1[i] = ((float)rand() / RAND_MAX) * 10;
        notasAvaliacao2[i] = ((float)rand() / RAND_MAX) * 10;
        mediaNotas[i] = (notasAvaliacao1[i] + notasAvaliacao2[i]) / 2;

        if (notasAvaliacao2[i] > notasAvaliacao1[i]) {
            desempenho[i] = "Melhorou";
        } else if (notasAvaliacao2[i] < notasAvaliacao1[i]) {
            desempenho[i] = "Piorou";
        } else {
            desempenho[i] = "Manteve a nota";
        }
    }

    // Imprime as notas e o desempenho de cada aluno
    cout << fixed << setprecision(1);
    cout << "Notas e Desempenho dos Alunos:" << endl;
    for (int i = 0; i < numAlunos; i++) {
        cout << "Aluno " << (i + 1) << ": Avaliação 1 = " << notasAvaliacao1[i] << ", Avaliação 2 = " << notasAvaliacao2[i] << ", Desempenho = " << desempenho[i] << endl;
    }

    // Imprime a média de cada aluno nas duas avaliações
    cout << "\nMédia das Notas dos Alunos:" << endl;
    for (int i = 0; i < numAlunos; i++) {
        cout << "Aluno " << (i + 1) << ": Média = " << mediaNotas[i] << endl;
    }

    return 0;
}