#include <iostream>

using namespace std;

int main(){

int num, contador;

cout << "Insira um número: ";
cin >> num;

cout << "Os divisores de " << num <<": " << endl;

int numAbsoluto = abs(num); //A função "abs() tem como objetivo obter o valor absoluto do número inserido, ou seja, ela nega o sinal do número."

for (contador = 1; contador <= numAbsoluto; contador++){
    if(numAbsoluto % contador == 0){
        cout << contador << endl; 
    }

}

return 0;
}