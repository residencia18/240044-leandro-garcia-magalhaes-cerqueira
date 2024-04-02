#include "include/ListaSalarios.h"

void ListaSalarios::entradaDeDados()
{
    float elemento;
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
        cout << "Elemento " + to_string(i + 1) + ": ";
        cin >> elemento;
        //-colocar validacao

        lista.push_back(elemento);
    }
}

void ListaSalarios::mostraMenor()
{
    float menor;
    getValorMinimo(lista, menor);

    cout << "O menor dos salarios é: " << menor << endl;
}

void ListaSalarios::mostraMediana()
{
    int divisao = (lista.size() / 2);
    float mediana;

    if (isPar(divisao))
    {
        mediana = (lista[divisao] + lista[divisao - 1]) / 2;
    }
    else
    {
        mediana = lista[divisao];
    }

    cout << "A mediana da lista de salários é: " << mediana << endl;
}

void ListaSalarios::mostraMaior()
{
    float maior;
    getValorMaximo(lista, maior);

    cout << "O maior dos salarios é: " << maior << endl;
}

void ListaSalarios::ordenarLista()
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

void ListaSalarios:: listarEmOrdem()
{
    ordenarLista();
    int tamanho = lista.size();
    cout << "Lista em ordem crescente: " << endl;

    for (int i = 0; i < tamanho; i++)
    {
        cout << lista[i] << endl;
    }
}

void ListaSalarios::listarNPrimeiros()
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