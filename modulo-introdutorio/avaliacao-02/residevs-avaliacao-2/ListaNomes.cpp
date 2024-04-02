#include "include/ListaNomes.h"

void ListaNomes::entradaDeDados()
{
    string elemento;
    int qtde;
    bool flag;

    do
    {
        // solicitar quantidade de elementos
        cout << "Quantos elementos vão existir na lista? ";
        cin >> qtde;

        // verificar se o número fornecido é válido, isto é, acima de 0
        flag = isNumeroValido(qtde);

        if (!flag)
        {
            cout << "Aviso: Entre com um número a partir de 1." << endl;
        }
    } while (!flag);

    // Adicionar cin.ignore() para descartar o caractere de nova linha
    cin.ignore(numeric_limits<streamsize>::max(), '\n');

    // solicitar cada elemento
    for (int i = 0; i < qtde; i++)
    {
        cout << "Nome " + to_string(i + 1) + ": ";
        // cin.ignore();
        getline(cin, elemento);
        //-colocar validacao

        lista.push_back(elemento);
    }
}

void ListaNomes::mostraMenor()
{
    int tamanho = lista.size();
    string menor = lista[0];

    for (int i = 0; i < tamanho; i++)
    {
        if (lista[i] < menor)
        {
            menor = lista[i];
        }
    }

    cout << "O primeiro nome alfabeticamente é: " << menor << endl;
}

void ListaNomes::mostraMediana()
{
    ordenarLista();
    int tamanho = lista.size();

    if (tamanho % 2 == 0)
    {
        int meio1 = tamanho / 2 - 1;
        // int meio2 = tamanho / 2;
        string mediana = lista[meio1];

        cout << "A mediana da lista de nomes é: " << mediana << endl;
    }
    else
    {
        int meio = tamanho / 2;
        string mediana = lista[meio];

        cout << "A mediana da lista de nomes é: " << mediana << endl;
    }
}

void ListaNomes::mostraMaior()
{
    int tamanho = lista.size();
    string maior = lista[0];

    for (int i = 0; i < tamanho; i++)
    {
        if (lista[i] > maior)
        {
            maior = lista[i];
        }
    }

    cout << "O último nome alfabeticamente é: " << maior << endl;
}

// Ordenar lista de nome
void ListaNomes::ordenarLista()
{
    int tamanho = lista.size();

    for (int i = 1; i < tamanho; i++)
    {
        for (int j = 0; j < tamanho - 1; j++)
        {
            if (lista[j].compare(lista[j + 1]) > 0)
            {
                string temp;
                temp = lista[j];
                lista[j] = lista[j + 1];
                lista[j + 1] = temp;
            }
        }
    }
}

void ListaNomes::listarEmOrdem()
{
    ordenarLista();
    int tamanho = lista.size();

    cout << "Lista em ordem alfabetica: " << endl;

    for (int i = 0; i < tamanho; i++)
    {
        cout << lista[i] << endl;
    }
}

void ListaNomes::listarNPrimeiros()
{
    int tamanho = lista.size();
    int n = 0;

    do
    {
        cout << "Deseja ver os dados até qual índice? (entre 0 - " << tamanho << "):";
        cin >> n;
    } while (n < 0 || n > tamanho);

    cout << "Lista dos " << n << " primeiros: " << endl;
    for (int i = 0; i < n; i++)
    {
        cout << lista[i] << endl;
    }
}
