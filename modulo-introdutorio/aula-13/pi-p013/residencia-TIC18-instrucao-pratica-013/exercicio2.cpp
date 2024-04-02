#include <iostream>
#include <string>

using namespace std;

struct Empregado {
    string nome;
    string sobrenome;
    string dataNascimento; // Alteração para ler a data de nascimento como uma string
    string RG;
    int anoAdmissao;
    double salario;
};

void Reajusta_dez_porcento(Empregado vetor[], int numEmpregados) {
    for (int i = 0; i < numEmpregados; i++) {
        vetor[i].salario *= 1.10; // Aumenta o salário em 10%
    }
}

int main() {
    Empregado vetorEmpregados[50];
    int numEmpregados;

    cout << "Digite o número de empregados: ";
    cin >> numEmpregados;

    for (int i = 0; i < numEmpregados; i++) {
        cout << "Empregado " << i + 1 << ":" << endl;
        cout << "Nome: ";
        cin.ignore(); // Limpa o buffer do teclado antes de ler o nome
        getline(cin, vetorEmpregados[i].nome);
        cout << "Sobrenome: ";
        getline(cin, vetorEmpregados[i].sobrenome);
        cout << "Data de Nascimento (DD/MM/AAAA): ";
        getline(cin, vetorEmpregados[i].dataNascimento); // Lê a data de nascimento como uma string
        cout << "RG: ";
        cin >> vetorEmpregados[i].RG;
        cout << "Ano de Admissao: ";
        cin >> vetorEmpregados[i].anoAdmissao;
        cout << "Salario: ";
        cin >> vetorEmpregados[i].salario;
    }

    Reajusta_dez_porcento(vetorEmpregados, numEmpregados);

    cout << "\nDados dos empregados após o reajuste de 10%:\n";
    for (int i = 0; i < numEmpregados; i++) {
        cout << "Empregado " << i + 1 << ":" << endl;
        cout << "Nome: " << vetorEmpregados[i].nome << " " << vetorEmpregados[i].sobrenome << endl;
        cout << "Data de Nascimento: " << vetorEmpregados[i].dataNascimento << endl;
        cout << "RG: " << vetorEmpregados[i].RG << endl;
        cout << "Ano de Admissao: " << vetorEmpregados[i].anoAdmissao << endl;
        cout << "Salario: " << vetorEmpregados[i].salario << endl;
    }

    return 0;
}
