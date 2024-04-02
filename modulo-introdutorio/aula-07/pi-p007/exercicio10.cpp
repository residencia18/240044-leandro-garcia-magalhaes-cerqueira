#include <iostream>

using namespace std;


int main(){

int digitos[10];
int num, operacoes = 0, aux,i;

cout << "Insira um número para verificarmos se ele é armstrong (narcisista) ou não: ";
cin >> num;

aux = num;


// Laço de repetição que separa os digitos inseridos pelo usuário de forma individual em um array.
for(i=0; aux > 0; i++){
    digitos[i] = aux/10;
}



// Laço de repetição que contabiliza quantos digitos possui o número inserido pelo usuário.
 while (num > 0) {
    num = num/10;
    operacoes = operacoes + 1;
 }
    cout << operacoes << endl;

// pow(base,expoente)






    return 0;

}
