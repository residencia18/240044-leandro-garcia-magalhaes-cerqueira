#ifndef DATA_H
#define DATA_H

#include <iostream>
#include <string>
#include <vector>
#include "funcoes_uteis.h"

using namespace std;

class Data{
private:
	int dia, mes, ano;

public:

	Data();

	Data(int _dia, int _mes, int _ano);

	string toString();

	static bool ehAnoBisexto(int ano);

	static int compara(Data d1, Data d2);

	static vector<Data> ordenaDatasCrescente(vector<Data> listaDatas);

	static bool ehFormatoValido(string data);

	static bool ehDataValida(Data data);

};


#endif