#include "include/ListaIdades.h"

void ListaIdades::entradaDeDados()
{
    int elemento;
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

    // solicitar cada elemento
    for (int i = 0; i < qtde; i++)
    {
        cout << "Data " + to_string(i + 1) + ": ";
        cin >> elemento;
        //-colocar validacao

        lista.push_back(elemento);
    }
}

void ListaIdades::mostraMediana()
{
    ordenarLista();
    int tamanho = lista.size();
    float mediana;

    if (isPar(tamanho))
    {
        int indiceMeio1 = (tamanho / 2) - 1;
        int indiceMeio2 = tamanho / 2;
        mediana = (lista[indiceMeio1] + lista[indiceMeio2]) / 2.0; // Média dos dois elementos do meio
    }
    else
    {
        mediana = lista[tamanho / 2];
    }

    cout << "A mediana da lista de idades é: " << mediana << endl;
}

void ListaIdades::mostraMenor()
{
    int menor;
    getValorMinimo(lista, menor);

    cout << "O menor das idades é: " << menor << endl;
}

void ListaIdades::mostraMaior()
{
    int maior;
    getValorMaximo(lista, maior);

    cout << "O maior das idades é: " << maior << endl;
}

// Ordenar lista de idade
void ListaIdades::ordenarLista()
{
    int tamanho = lista.size();
    for (int i = 1; i < tamanho; i++)
    {
        for (int j = 0; j < tamanho - 1; j++)
        {
            if (lista[j] > lista[j + 1])
            {
                int temp;
                temp = lista[j];
                lista[j] = lista[j + 1];
                lista[j + 1] = temp;
            }
        }
    }
}

void ListaIdades::listarEmOrdem()
{
    ordenarLista();
    int tamanho = lista.size();
    cout << "Lista em ordem crescente: " << endl;

    for (int i = 0; i < tamanho; i++)
    {
        cout << lista[i] << endl;
    }
}

void ListaIdades::listarNPrimeiros()
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