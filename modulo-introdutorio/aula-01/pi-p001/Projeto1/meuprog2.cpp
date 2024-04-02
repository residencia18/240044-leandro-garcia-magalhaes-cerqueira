#include <iostream>
#include <stdlib.h>
#include <string>

using namespace std;

int main (){

int A, B, soma, subtracao, multiplicacao, divisao, resto;

std:: cout << "Insira um numero inteiro:";
std:: cin >> A;

std:: cout << "Agora insira outro numero inteiro:";
std:: cin >> B;

soma = A + B;
subtracao = A - B;
multiplicacao = A * B;
divisao = A / B;
resto = A % B;

std:: cout << "Soma: " << soma << std::endl;
std:: cout << "Subtracao: " << subtracao << std::endl;
std:: cout << "Multiplicacao: " << multiplicacao << std::endl;
std:: cout << "Divisao: " << divisao << std::endl;
std:: cout << "Resto da divisao: " << resto << std::endl;


    return 0;
}