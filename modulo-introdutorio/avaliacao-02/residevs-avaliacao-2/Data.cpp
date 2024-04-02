#include "Data.h"

Data::Data(void) {}

Data::Data(int _dia, int _mes, int _ano)
{
	dia = _dia;
	mes = _mes;
	ano = _ano;
}

string Data::toString()
{
	string ret = "";
	string _dia = dia < 10 ? "0" + to_string(dia) : to_string(dia);
	string _mes = mes < 10 ? "0" + to_string(mes) : to_string(mes);

	ret.append(_dia);
	ret.append("/");
	ret.append(_mes);
	ret.append("/");
	ret.append(to_string(ano));
	return ret;
}

bool Data::ehDataValida(Data data)
{
	bool ehValido = true;

	if (data.dia < 1 || data.dia > 31 || data.mes < 1 || data.mes > 12)
	{
		return false;
	}
	else
	{
		if (data.mes < 8)
		{
			if (data.mes % 2 == 0 && data.dia > 30) // Meses pares de JAN a JUL só vão até dia 30
			{
				ehValido = false;
			}

			if (data.mes == 2)
			{
				if ((!ehAnoBisexto(data.ano) && data.dia > 28) || data.dia > 29) // FEV vai somente até 28 nos anos não bisextos
				{
					ehValido = false;
				}
			}
		}
		else if (data.mes % 2 == 1 && data.dia > 30) // Meses ímpares de AGO a DEZ só vão até dia 30
		{
			ehValido = false;
		}
	}

	return ehValido;
}

bool Data::ehFormatoValido(string data)
{
	string dia, mes, ano, barra1, barra2;
	string somenteDigitos;

	if (data.size() != 10)
	{
		return false;
	}

	dia = data.substr(0, 2);
	barra1 = data.substr(2, 1);
	mes = data.substr(3, 2);
	barra2 = data.substr(5, 1);
	ano = data.substr(6, 4);

	somenteDigitos = dia + mes + ano;
	int tamanho = somenteDigitos.size();

	for (int i = 0; i < tamanho; i++)
	{
		if (!isdigit(somenteDigitos[i]))
		{
			return false;
		}
	}

	if (barra1.compare("/") != 0 || barra2.compare("/") != 0)
	{
		return false;
	}

	return true;
}

vector<Data> Data::ordenaDatasCrescente(vector<Data> listaDatas)
{
	int tamanho = listaDatas.size();
	Data aux;

	for (int i = 0; i < tamanho; i++)
	{
		for (int j = i + 1; j < tamanho; j++)
		{
			if (compara(listaDatas[i], listaDatas[j]) == 1)
			{
				aux = listaDatas[i];
				listaDatas[i] = listaDatas[j];
				listaDatas[j] = aux;
			}
		}
	}

	return listaDatas;
}

int Data::compara(Data d1, Data d2){

	if (d1.dia == d2.dia && d1.mes == d2.mes && d1.ano == d2.ano)
	{
		return 0;
	}

	if (d1.ano < d2.ano)
	{
		return -1;
	}
	else if (d1.ano == d2.ano)
	{
		if (d1.mes < d2.mes)
		{
			return -1;
		}
		else if (d1.mes == d2.mes)
		{
			if (d1.dia < d2.dia)
			{
				return -1;
			}
		}
	}

	return 1;
}

bool Data:: ehAnoBisexto(int ano){
	if (ano % 100 > 0 && ano % 4 == 0)
	{
		return true;
	}
	return false;
}
