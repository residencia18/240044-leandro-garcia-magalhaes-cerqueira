#include "include/ListaDatas.h"

#include <climits>
#include <cctype>

void ListaDatas::imprimir()
{
	// systemClear();
	cout << "Datas inseridas em ordem cronologica:" << endl;

	for (Data data : lista)
	{
		cout << data.toString() << "\t";
	}

	cout << endl
		 << endl;
}

void ListaDatas::entradaDeDados()
{
	int qtdItens = 0;
	int contador = 0;
	bool dataValida = true;
	string entrada, dia, mes, ano;

	// systemClear();
	cout << "Informe a quantidade de itens na lista: ";
	cin >> qtdItens;

	if (qtdItens > 0)
	{
		do
		{
			// systemClear();
			cout << "Inserindo Datas na lista:" << endl;

			cout << "\nInforme uma data no formato dd/mm/aaaa: ";

			cout << "\nData " << contador + 1 << ": ";
			cin >> entrada;

			if (!Data::ehFormatoValido(entrada))
			{
				dataValida = false;
				cout << "\nFormato de Data inválido.\n";
				systemPause();
			}
			else
			{
				dia = entrada.substr(0, 2);
				mes = entrada.substr(3, 2);
				ano = entrada.substr(6, 4);

				Data data(stoi(dia), stoi(mes), stoi(ano));

				if (Data::ehDataValida(data))
				{
					lista.push_back(data);
					contador++;
					dataValida = true;
				}
				else
				{
					dataValida = false;
					cout << "\nData inválida.\n";
					systemPause();
				}
			}
		} while (contador < qtdItens || !dataValida);

		lista = Data::ordenaDatasCrescente(lista);
	}
}

void ListaDatas::mostraMenor()
{
	cout << "Menor data: " << lista[0].toString() << endl;
}

void ListaDatas::mostraMediana()
{
	lista = Data::ordenaDatasCrescente(lista);
	Data mediana;
	int tamanho = lista.size();

	if (tamanho % 2 == 0)
	{
		int index = ((tamanho / 2 - 1) + (tamanho / 2)) / 2;
		mediana = lista[index];
	}
	else
	{
		mediana = lista[tamanho / 2];
	}

	cout << "Mediana: " << mediana.toString() << endl;
}

void ListaDatas::mostraMaior()
{
	int index = lista.size();
	cout << "Maior data: " << lista[index - 1].toString() << endl;
}

void ListaDatas::listarEmOrdem()
{
	lista = Data::ordenaDatasCrescente(lista);
	int tamanho = lista.size();
	cout << "Lista em ordem crescente: " << endl;

	for (int i = 0; i < tamanho; i++)
	{
		cout << lista[i].toString() << endl;
	}
}

void ListaDatas::listarNPrimeiros()
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
		cout << lista[i].toString() << endl;
	}
}